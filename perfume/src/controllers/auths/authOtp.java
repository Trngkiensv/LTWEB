package controllers.auths;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;

@WebServlet("/authOtp")
public class authOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public authOtp() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set character encoding
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Retrieve OTP and email from form input
		String enteredOtp = request.getParameter("otp");
		String email = request.getParameter("email");

		// Validate input
		if (email == null || enteredOtp == null || email.isEmpty() || enteredOtp.isEmpty()) {
			request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/auth/sendEmail.jsp");
			rd.forward(request, response);
			return;
		}

		// Get the session and retrieve stored OTP and email
		HttpSession session = request.getSession();
		String storedOtp = (String) session.getAttribute("otp");
		String storedEmail = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String fullname = (String) session.getAttribute("fullname");

		// Verify the OTP and email
		if (storedOtp != null && storedEmail != null && storedOtp.equalsIgnoreCase(enteredOtp)
				&& storedEmail.equals(email)) {
			// OTP is valid
			User user = new User(email, fullname, password);
			UserDao userDao = new UserDao();

			try {
				int signup = userDao.signup(user);
				if (signup > 0) {
					// Đăng ký thành công, xóa session OTP
					session.removeAttribute("otp");
					session.removeAttribute("username");
					session.removeAttribute("fullname");
					session.removeAttribute("password");

					response.sendRedirect(request.getContextPath() + "/login?msg=SUCCESS");
				} else {
					request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại!");
					RequestDispatcher rd = request.getRequestDispatcher("/views/auth/signup.jsp");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "Đã xảy ra lỗi trong quá trình đăng ký. Vui lòng thử lại sau!");
				RequestDispatcher rd = request.getRequestDispatcher("/views/auth/signup.jsp");
				rd.forward(request, response);
			}
		} else {
			// OTP or email mismatch
			request.setAttribute("error", "Mã OTP không chính xác hoặc đã hết hạn!");
			RequestDispatcher rd = request.getRequestDispatcher("/views/auth/sendEmail.jsp");
			rd.forward(request, response);
		}
	}
}
