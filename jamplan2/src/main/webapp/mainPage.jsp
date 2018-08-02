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
		<input type = "text" name = "planNo">
		<input type = "text" name = "teamNo">
		<input type = "submit" value="서버전송">
	</form>
	
	<a href = "main.manageplan">planMain</a>
	<a href = "calendar.manageplan?planNo=1&teamNo=1">calendar</a>
	<a href = "map.manageplan">map</a>
	<a href = "plantable.manageplan">plan</a>
	<a href = "viewall.manageplan">view all</a>
</body>
</html>