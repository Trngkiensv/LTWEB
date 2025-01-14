package controllers.auths;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;
import util.StringUtil;
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang đổi mật khẩu
        request.getRequestDispatcher("/views/auth/changePassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfor");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Kiểm tra mật khẩu hiện tại
        UserDao userDao = new UserDao();
        String encryptedCurrentPassword = StringUtil.md5(currentPassword);
        if (!user.getPassword().equals(encryptedCurrentPassword)) {
            session.setAttribute("error", "Mật khẩu hiện tại không đúng!");
            response.sendRedirect(request.getContextPath() + "/changePassword");
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!newPassword.equals(confirmPassword)) {
            session.setAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
            response.sendRedirect(request.getContextPath() + "/changePassword");
            return;
        }

        // Cập nhật mật khẩu mới
        String encryptedNewPassword = StringUtil.md5(newPassword);
        user.setPassword(encryptedNewPassword);
        int isUpdated = userDao.edit(user);

        if (isUpdated > 0) {
            // Xóa session và chuyển hướng đến trang đăng nhập
            session.invalidate(); // Hủy session hiện tại
            response.sendRedirect(request.getContextPath() + "/login?msg=PASSWORD_CHANGED");
        } else {
            session.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            response.sendRedirect(request.getContextPath() + "/changePassword");
        }
    }
}

