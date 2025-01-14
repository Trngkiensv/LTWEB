package controllers.auths;

import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;
import util.StringUtil;

public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("views/auth/singup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		UserDao userDao = new UserDao();
//		
//		String fullname = request.getParameter("fullname");
//		String username = request.getParameter("username");
//		String password = StringUtil.md5(request.getParameter("password"));
//		
//		User user = new User(username,fullname, password);
//		int singup = userDao.signup(user);
//		if(singup > 0) {
//			 response.sendRedirect(request.getContextPath()+"/login?msg=SUCCESS");
//			 return;
//		}else {
//			response.sendRedirect(request.getContextPath()+"/signup?msg=ERROR");
//			return;
//		}
		
		
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    request.setCharacterEncoding("UTF-8");

	    // Lấy dữ liệu từ form
	    String fullname = request.getParameter("fullname");
	    String username = request.getParameter("username");
	    String password = StringUtil.md5(request.getParameter("password")); // Mã hóa mật khẩu

	    // Kiểm tra dữ liệu hợp lệ
	    if (fullname == null || username == null || password == null || fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {
	        request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
	        RequestDispatcher rd = request.getRequestDispatcher("/views/auth/signup.jsp");
	        rd.forward(request, response);
	        return;
	    }
	    Random random = new Random();
	    int otp1 = 100000 + random.nextInt(900000);
	    String otp = Integer.toString(otp1);// Tạo mã OTP 6 chữ số

	    // Gửi email OTP
//	    try {
//	        EmailSender.sendOtpEmail(username, otp); // Gửi email OTP
//	    } catch (MessagingException e) {
//	        e.printStackTrace();
//	        request.setAttribute("error", "Không thể gửi email. Vui lòng thử lại!");
//	        RequestDispatcher rd = request.getRequestDispatcher("/views/auth/signup.jsp");
//	        rd.forward(request, response);
//	        return;
//	    }
	    EmailSender emailSender = new EmailSender();
	    try {
			emailSender.sendOtpEmail(username, otp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    // Lưu thông tin vào session
	    HttpSession session = request.getSession();
	    session.setAttribute("fullname", fullname);
	    session.setAttribute("username", username);
	    session.setAttribute("password", password);
	    session.setAttribute("otp", otp);

	    // Chuyển hướng đến trang gửi email xác nhận
	    RequestDispatcher rd = request.getRequestDispatcher("/views/auth/sendEmail.jsp");
	    rd.forward(request, response);
	}

}
