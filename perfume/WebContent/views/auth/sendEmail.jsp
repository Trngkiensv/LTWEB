<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/templates/public/auth/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/css/util.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/public/auth/css/main.css">
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img
						src="<%=request.getContextPath()%>/templates/public/auth/images/img-01.png"
						alt="IMG">
				</div>
				<%
				// Lấy thông báo lỗi hoặc thành công từ request hoặc session
				String error = (String) request.getAttribute("error");
				String message = (String) request.getAttribute("message");
				%>
				<%
				if (error != null) {
				%>
				<div class="alert alert-danger" role="alert">
					<%=error%>
				</div>
				<%
				} else if (message != null) {
				%>
				<div class="alert alert-success" role="alert">
					<%=message%>
				</div>
				<%
				}
				%>
				<form class="login100-form validate-form" method="post"
					action="<%=request.getContextPath()%>/authOtp">
					<span class="login100-form-title"> Xác nhận Email </span>

					<!-- Email Input -->
					<div class="wrap-input100 validate-input"
						data-validate="Vui lòng nhập email hợp lệ">
						<input id="email" class="input100" type="email" name="email"
							value="<%=session.getAttribute("username") != null ? session.getAttribute("username") : ""%>"
							placeholder="Nhập email của bạn" readonly> <span
							class="focus-input100"></span> <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<!-- Button to Allow Editing -->
					<div class="text-center">
						<button type="button" id="editEmail"
							class="btn btn-link text-primary">Đổi email</button>
					</div>

					<!-- OTP Input -->
					<div class="wrap-input100 validate-input"
						data-validate="Vui lòng nhập mã xác nhận">
						<input class="input100" type="text" name="otp"
							placeholder="Nhập mã xác nhận" required> <span
							class="focus-input100"></span> <span class="symbol-input100">
							<i class="fa fa-key" aria-hidden="true"></i>
						</span>
					</div>

					<!-- Confirm Email Button -->
					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">Xác nhận
							Email</button>
					</div>

					<!-- Resend OTP Button -->
					<div class="text-center p-t-12">
						<span class="txt1">Không nhận được mã?</span>
						<!-- Button gửi lại mã -->
						<form id="resendOtpForm" method="post"
							action=""
							style="display: inline;">
							<input type="hidden" name="email"
								value="<%=session.getAttribute("username")%>">
							<button type="submit" id="resendOtpButton"
								class="btn btn-link text-primary">Gửi lại mã</button>
						</form>
					</div>

				</form>
			</div>
		</div>
	</div>
	<script>
		document.getElementById("editEmail").addEventListener("click",
				function() {
					// Enable email input for editing
					const emailInput = document.getElementById("email");
					emailInput.removeAttribute("readonly");
					emailInput.focus();
				});
	</script>