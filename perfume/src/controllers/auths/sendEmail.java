//package controllers.auths;
//
//import java.io.IOException;
//import java.util.Random;
//
//import javax.mail.MessagingException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import daos.UserDao;
//import models.User;
//import util.StringUtil;
//
//@WebServlet("/sendEmail")
//public class sendEmail extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	public sendEmail() {
//		super();
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		RequestDispatcher rd = request.getRequestDispatcher("views/auth/singup.jsp");
//		rd.forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	        throws ServletException, IOException {
//	    // Thiết lập mã hóa ký tự cho request và response
//	    response.setContentType("text/html; charset=UTF-8");
//	    response.setCharacterEncoding("UTF-8");
//	    request.setCharacterEncoding("UTF-8");
//
//	    // Lấy dữ liệu từ form
//	    String email = request.getParameter("username");
//	    String fullname = request.getParameter("fullname");
//	    String password = request.getParameter("password");
//
//	    // Kiểm tra dữ liệu có null hoặc rỗng không
//	    if (email == null || fullname == null || password == null || email.isEmpty() || fullname.isEmpty()
//	            || password.isEmpty()) {
//	        // Gửi thông báo lỗi
//	        request.setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
//	        RequestDispatcher rd = request.getRequestDispatcher("/views/auth/singup.jsp");
//	        rd.forward(request, response);
//	        return;
//	    }
//
//	    // Sinh mã OTP
//	    Random random = new Random();
//	    int otp = 100000 + random.nextInt(900000); // Tạo mã OTP 6 chữ số
//
//	    // Gửi email OTP
//	    try {
//	        EmailSender.sendOtpEmail(email, otp); // Gửi email OTP
//	    } catch (MessagingException e) {
//	        e.printStackTrace();
//	        request.setAttribute("error", "Không thể gửi email. Vui lòng thử lại!");
//	        RequestDispatcher rd = request.getRequestDispatcher("/views/auth/singup.jsp");
//	        rd.forward(request, response);
//	        return;
//	    }
//
//	    // Lưu OTP và thông tin vào session
//	    HttpSession session = request.getSession();
//	    session.setAttribute("otp", otp);
//	    session.setAttribute("email", email);
//	    session.setAttribute("fullname", fullname);
//	    session.setAttribute("password", password);
//
//	    // Chuyển tiếp đến trang xác nhận email
//	    RequestDispatcher rd = request.getRequestDispatcher("/views/auth/sendEmail.jsp");
//	    rd.forward(request, response);
//	}
//
//}
