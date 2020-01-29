<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login -Form</h1>
	<%=request.getAttribute("msg")%>
	<form action="Controller" method="post">
		<input type="hidden" name="id" value="login_form"> 
		Email : <input type="text" name="email" value="<%=request.getAttribute("email")%>"><br>
		Password : <input type="password" name="password"value="<%=request.getAttribute("password")%>"><br> <br>
		<input type="submit" value="login"><br> <br> 
		<a href="Controller?id=sign_up">Sign_up_Form</a>
	</form>
</body>
</html>