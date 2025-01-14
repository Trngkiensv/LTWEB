package controllers.auths;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resendOtp")
public class resendOtp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Email không hợp lệ!");
            return;
        }

        // Generate and send OTP
        int otp1 = new Random().nextInt(999999);
        String otp = Integer.toString(otp1);
        try {
			EmailSender.sendOtpEmail(email, otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Respond to the client
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("OTP sent successfully!");
    }
}
