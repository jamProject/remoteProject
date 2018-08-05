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
	
	<form action="calendar.manageplan">
		<input value="id" type = "text" name = "id">
		<input value="password" type = "text" name = "password">
		<input type = "submit" value="서버전송">
	</form>
	
	<a href = "main.manageplan">planMain</a>
</body>
</html>