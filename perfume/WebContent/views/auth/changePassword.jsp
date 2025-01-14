<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi mật khẩu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS liên kết -->
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/templates/public/auth/images/icons/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/css/util.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/public/auth/css/main.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="<%=request.getContextPath()%>/templates/public/auth/images/img-01.png" alt="IMG">
            </div>
			<%
                    String message = (String) session.getAttribute("message");
                    String error = (String) session.getAttribute("error");
                    if (message != null) {
                %>
                    <div class="alert alert-success" role="alert">
                        <%=message%>
                    </div>
                <%
                    session.removeAttribute("message");
                    }
                    if (error != null) {
                %>
                    <div class="alert alert-danger" role="alert">
                        <%=error%>
                    </div>
                <%
                    session.removeAttribute("error");
                    }
                %>
            <form class="login100-form validate-form" method="post" action="<%=request.getContextPath()%>/changePassword">
                <span class="login100-form-title"> Đổi mật khẩu </span>

                <!-- Hiển thị thông báo -->
                

                <!-- Mật khẩu hiện tại -->
                <div class="wrap-input100 validate-input" data-validate="Vui lòng nhập mật khẩu hiện tại">
                    <input class="input100" type="password" name="currentPassword" placeholder="Mật khẩu hiện tại" required>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                        <i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                </div>

                <!-- Mật khẩu mới -->
                <div class="wrap-input100 validate-input" data-validate="Vui lòng nhập mật khẩu mới">
                    <input class="input100" type="password" name="newPassword" placeholder="Mật khẩu mới" required>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                        <i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                </div>

                <!-- Xác nhận mật khẩu mới -->
                <div class="wrap-input100 validate-input" data-validate="Vui lòng xác nhận mật khẩu mới">
                    <input class="input100" type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu mới" required>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                        <i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                </div>

                <!-- Nút đổi mật khẩu -->
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">Đổi mật khẩu</button>
                </div>

                <!-- Quay lại trang chủ -->
                <div class="text-center p-t-12">
                    <a class="txt2" href="<%=request.getContextPath()%>/home">Quay lại trang chủ</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- JS liên kết -->
<script src="<%=request.getContextPath()%>/templates/public/auth/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/public/auth/vendor/bootstrap/js/popper.js"></script>
<script src="<%=request.getContextPath()%>/templates/public/auth/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/public/auth/vendor/select2/select2.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/public/auth/vendor/tilt/tilt.jquery.min.js"></script>
<script>
    $('.js-tilt').tilt({
        scale : 1.1
    });
</script>
<script src="<%=request.getContextPath()%>/templates/public/auth/js/main.js"></script>
</body>
</html>
