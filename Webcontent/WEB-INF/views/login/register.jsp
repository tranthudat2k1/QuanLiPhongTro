<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/" />
<link href="<c:url value='/resources/assets/bootstrap.min.css' />"
	rel="stylesheet" />
<link href="https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&amp;display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/loggin.css" />

<title>Login</title>
</head>
<body>
	<div class="container login-container">
		<div class="col-push-3 col-md-6 login-form-2" style="position: relative;">
			<a
				style="position: absolute; top: 20px; left: 20px; display: block; width: 40px; height: 40px;color:white"
				href="login/index.htm"><i class='bx bx-arrow-back' style="font-size: 24px"></i>
			</a>
			<h3>Đăng Ký</h3>

			<form:form action="login/register.htm" class="login__form"
				method="POST" modelAttribute="user">
				<form:input path="MACT" style="display:none" />
				<div class="form-group">
					<form:input type="text" class="form-control" placeholder="Nhập họ "
						value="" path="HO" />
				</div>
				<div class="form-group">
					<form:input type="text" class="form-control"
						placeholder="Nhập tên " value="" path="TEN" />
				</div>
				<div class="form-group">
					<form:input type="text" class="form-control"
						placeholder="Số điện thoại " value="" path="SDT" />
				</div>
				<div class="form-group">
					<form:input type="text" class="form-control	"
						placeholder="Gmail" value="" path="EMAIL" />
				</div>

				<div class="form-group" style="display:none">
					<form:input type="text" class="form-control" placeholder="Username"
						value="" path="TAIKHOAN" />
				</div>
				<div class="form-group">
					<form:input type="password" class="form-control"
						placeholder="Password " value="" path="PASSWORD" />
				</div>

				<div class="form-group">
					<input type="password" class="form-control"
						placeholder="Confirm Your Password " value="" name="pwcf" />
				</div>

				<div class="form-group" style="margin-top: 30px">
					<button type="submit"
						style="width: 100%; display: block; text-align: center"
						class="btnSubmit">Đăng Ký</button>
				</div>


			</form:form>
		</div>

	</div>
	<script src="<c:url value ='/resources/js/main.js'/>"></script>
</body>
</html>