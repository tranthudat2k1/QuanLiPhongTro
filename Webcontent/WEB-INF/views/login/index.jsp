<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<c:url value ='/resources/css/login.css' />" />

<title>Login</title>
</head>
<body>
	<div class="login__app">
		<div class="login__main">
			<h2 class="login__header">Đăng nhập vào hệ thống</h2>
			<form action="login.htm" class="login__form" method="POST">
				<div class="login__input">
					<label class="login__lable" for="user">Username</label> <input
						type="text" class="login__field" name="user" />
				</div>
				<div class="login__input">
					<label class="login__lable" for="password">Password</label> <input
						type="password" class="login__field" name="password" />
				</div>
				<button class="login__button" type="submit">Login</button>
			</form>
		</div>
	</div>
</body>
</html>