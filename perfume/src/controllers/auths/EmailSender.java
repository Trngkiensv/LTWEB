package controllers.auths;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public void sendTestEmail(String recipient) {
        // Email và mật khẩu tài khoản gửi
        final String senderEmail = "21130409@st.hcmuaf.edu.vn"; // Thay bằng email của bạn
        final String senderPassword = "fazzypugaskpjvrd";    // Thay bằng mật khẩu của bạn

        // Cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Xác thực tài khoản gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session); // Sử dụng MimeMessage thay vì Message
            message.setFrom(new InternetAddress(senderEmail)); // Địa chỉ gửi
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipient)); // Địa chỉ nhận
            message.setSubject("Test Email");
            message.setText("Đây là email test gửi từ JavaMail API.");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendOtpEmail(String email, String otp) throws MessagingException{
        // Email và mật khẩu của tài khoản gửi
        final String senderEmail = "21130409@st.hcmuaf.edu.vn"; // Thay bằng email của bạn
        final String senderPassword = "fazzypugaskpjvrd"; // Thay bằng mật khẩu ứng dụng của bạn

        // Cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Xác thực tài khoản gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Cấu trúc thư gửi OTP
            String subject = "Xác nhận OTP của bạn";
            String content = "Xin chào,\n\n"
                    + "Cảm ơn bạn đã đăng ký tài khoản.\n"
                    + "Mã OTP của bạn là: " + otp + "\n"
                    + "Vui lòng nhập mã này để hoàn tất quá trình đăng ký.\n\n"
                    + "Trân trọng,\n"
                    + "Đội ngũ hỗ trợ.";

            // Tạo email
            Message message = new MimeMessage(session); // Sử dụng MimeMessage
            message.setFrom(new InternetAddress(senderEmail)); // Địa chỉ gửi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // Địa chỉ nhận
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);

            System.out.println("Email OTP đã được gửi đến: " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi gửi email!");
        }
    }

    public static void main(String[] args) throws MessagingException {
        // Thay email người nhận
        String recipient = "kinphm556@gmail.com"; // Thay bằng email của bạn hoặc email test
        sendOtpEmail(recipient, "1236666"); // Gửi email OTP
    }
}
