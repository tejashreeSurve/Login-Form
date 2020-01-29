<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Sign_up Form</h1>
	<form action="Controller" method="post">
		<input type="hidden" name="id" value="sign_up_form">
		<%=request.getAttribute("msg")%><br><br>
		Name : <input type="text" name="name" value="<%=request.getAttribute("name")%>"><br><br> 
		Email-id : <input type="text" name="email" value="<%=request.getAttribute("email")%>"><br><br> 
		Password : <input type="password" name="password" value="<%=request.getAttribute("password")%>"><br><br>
		<input type="submit" value="Sign_up">
	</form>
</body>
</html>