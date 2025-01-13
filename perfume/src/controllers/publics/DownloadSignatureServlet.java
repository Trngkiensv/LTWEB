package controllers.publics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.OrderDao;

@WebServlet("/download-signature")
public class DownloadSignatureServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderDao orderDao = new OrderDao();
        int orderID = orderDao.findAllOrderID().getLast() + 1;
     // Đường dẫn tạo file chữ ký
        String filePath = getServletContext().getRealPath("/files/signature-order-" + orderID + ".txt");
        File file = new File(filePath);

        // Ghi nội dung (chỉ OrderID) vào file
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            writer.write("OrderID: " + orderID); // Ghi OrderID vào file
//        }

        // Băm nội dung file
        String fileHash = hashFile(file);
//        System.out.println(fileHash);

        // Thêm hash vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(fileHash); // Ghi hash vào file
        }

        // Gửi file cho người dùng
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

            try (FileInputStream inStream = new FileInputStream(file);
                 OutputStream outStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không thể tạo file!");
        }

        // Xóa file tạm sau khi gửi
        file.delete();
    }
    private String hashFile(File file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
            byte[] hashBytes = digest.digest(fileBytes);

            // Chuyển hash thành chuỗi Base64
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
