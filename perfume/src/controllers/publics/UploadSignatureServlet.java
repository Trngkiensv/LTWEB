package controllers.publics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload-signature")
@MultipartConfig
public class UploadSignatureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Đường dẫn lưu chữ ký và khóa công khai
        String signatureUploadPath = getServletContext().getRealPath("/uploads/signatures");
        String publicKeyUploadPath = getServletContext().getRealPath("/uploads/publickeys");

        // Tạo thư mục nếu chưa tồn tại
        File signatureDir = new File(signatureUploadPath);
        File publicKeyDir = new File(publicKeyUploadPath);
        if (!signatureDir.exists()) signatureDir.mkdirs();
        if (!publicKeyDir.exists()) publicKeyDir.mkdirs();

        try {
            // Lấy file chữ ký từ form
            Part signaturePart = request.getPart("signatureFile");
            String signatureFileName = signaturePart.getSubmittedFileName();
            File signatureFile = new File(signatureUploadPath + File.separator + signatureFileName);
            try (FileOutputStream fos = new FileOutputStream(signatureFile);
                 InputStream is = signaturePart.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            // Lấy file khóa công khai từ form
            Part publicKeyPart = request.getPart("publicKeyFile");
            String publicKeyFileName = publicKeyPart.getSubmittedFileName();
            File publicKeyFile = new File(publicKeyUploadPath + File.separator + publicKeyFileName);
            try (FileOutputStream fos = new FileOutputStream(publicKeyFile);
                 InputStream is = publicKeyPart.getInputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            // Thông báo thành công
            response.sendRedirect(request.getContextPath() + "/order?msg=UPLOAD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/order?msg=UPLOAD_FAILED");
        }
    }
}
