<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--한글 인코딩 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>mainPage </h1>
	
	<form action="login.main" method="post">
		<input value="id" type = "text" name = "id">
		<input value="pass" type = "password" name = "pass">
		<input type = "submit" value="로그인">
	</form>
	
	<a href="main.jsp">planMain</a>
	<a href = "main.search">mainPage</a>
	<a href = "plan.search">planPage</a>
	<a href = "formFile.search">formFile</a>
	<a href = "view.search">view</a>
	<a href = "schedule.search">schedule</a>
	<a href = "login.search">login</a>
</body>
</html>