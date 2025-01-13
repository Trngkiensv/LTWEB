package controllers.publics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controllers.auths.DigitalSignatureUtil;
import daos.CatPerfumeDao;
import daos.ItemsDao;
import daos.OrderDao;
import daos.UserDao;
import models.CatPerfume;
import models.Item;
import models.Order;
import models.Perfume;
import models.User;

@MultipartConfig
public class PublicsOrderControllers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PublicsOrderControllers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("/views/public/order.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        // DAOs
        OrderDao orderDao = new OrderDao();
        ItemsDao itemDao = new ItemsDao();

        // Lấy thông tin từ form
        int userID = Integer.parseInt(request.getParameter("userID"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("adress");
        String note = request.getParameter("note");
        // Xử lý file tải lên (chữ ký và khóa công khai)
        Part signaturePart = request.getPart("signatureFile");
        Part publicKeyPart = request.getPart("publicKeyFile");

        // Đường dẫn lưu chữ ký và khóa công khai
        String uploadDir = getServletContext().getRealPath("/uploads");
        File signatureFile = new File(uploadDir + File.separator + "signature_" + userID + ".txt");
        File publicKeyFile = new File(uploadDir + File.separator + "publicKey_" + userID + ".txt");
        System.out.println("Upload directory: " + uploadDir);
        System.out.println("Signature file path: " + signatureFile.getAbsolutePath());
        System.out.println("Public key file path: " + publicKeyFile.getAbsolutePath());


        // Tạo thư mục nếu chưa tồn tại
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // Lưu chữ ký
        try (FileOutputStream fos = new FileOutputStream(signatureFile);
             InputStream is = signaturePart.getInputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // Lưu khóa công khai
        try (FileOutputStream fos = new FileOutputStream(publicKeyFile);
             InputStream is = publicKeyPart.getInputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // Đọc chữ ký từ file
        String signatureStr;
        try (InputStream is = new FileInputStream(signatureFile)) {
            signatureStr = new String(is.readAllBytes()).trim();
        }

        // Đọc khóa công khai từ file
        PublicKey publicKey = null;
        try (InputStream is = new FileInputStream(publicKeyFile)) {
            byte[] publicKeyBytes = is.readAllBytes();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/order?msg=INVALID_PUBLIC_KEY");
            return;
        }

        // Lấy giá trị orderID để hash
        String dataToVerify = String.valueOf(orderDao.findAllOrderID().get(orderDao.findAllOrderID().size() - 1) + 1);

        // Xác minh chữ ký bằng cách so sánh giá trị hash
        if (!verifyHash(dataToVerify, signatureStr, publicKey)) {
            response.sendRedirect(request.getContextPath() + "/order?msg=SIGNATURE_INVALID");
            return;
        }

        // Thêm thông tin đơn hàng vào cơ sở dữ liệu
        Order order = new Order(new User(userID), phone, email, address, note);
        int addOrder = orderDao.order(order);

        if (addOrder > 0) {
            // Lấy sản phẩm từ session
            HttpSession session = request.getSession();
            if (session.getAttribute("order") != null) {
                Order orderItem = (Order) session.getAttribute("order");
                List<Item> ListItem = (List<Item>) orderItem.getItem();

                if (ListItem != null && ListItem.size() > 0) {
                    int addItem = 0;
                    for (Item item : ListItem) {
                        int quantity = item.getQuantity();
                        long price = item.getPrice();
                        int per_id = item.getProduct().getId();
                        int order_id = addOrder;

                        Item items = new Item(new Perfume(per_id), quantity, price, new Order(order_id));
                        addItem = itemDao.add(items);
                    }

                    if (addItem > 0) {
                        response.sendRedirect(request.getContextPath() + "/order?msg=SUCCESS");
                        return;
                    } else {
                        System.out.println("Lỗi thêm sản phẩm vào đơn hàng.");
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            System.out.println("Lỗi thêm đơn hàng.");
            response.sendRedirect(request.getContextPath() + "/order?msg=FAILED");
        }
    }

    private boolean verifyHash(String data, String signatureStr, PublicKey publicKey) {
        try {
            // Decode chữ ký từ Base64
            byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);

            // Sử dụng thuật toán SHA256withRSA để xác minh
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);

            // Cập nhật dữ liệu cần xác minh (hash của OrderID)
            signature.update(data.getBytes()); // Dùng dữ liệu gốc

            // Xác minh chữ ký
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private String hashData(String data) throws NoSuchAlgorithmException {
        // Sử dụng thuật toán SHA-256 để hash nội dung
        java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}