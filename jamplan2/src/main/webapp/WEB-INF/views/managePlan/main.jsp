<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"crossorigin="anonymous"></script>
	<spring:url value="/resources/planMain/js/planMain.js" var="mainPageJs" />
	<spring:url value="/resources/planMain/css/planMain.css" var="mainPageCss" />
	<script src="${mainPageJs}"></script>
	<link href="${mainPageCss}" rel="stylesheet" />
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>JAM</title>
</head>


<%
	String id = (String)session.getAttribute("id");
	session.setAttribute("planNo", 1);
%>


	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>

<body>
<body style="height: 1500px">

	<h1>planMainPage</h1>
	<div>
		<label>Plan Main</label>
	</div>
	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top" id ="navCenter">
		<table>
			<tr>
				<td class = "link" value = "calendarajax.mp" style="color:white">calendar</td>
				<td class = "link" value = "mapajax.mp" style="color:white">map</td>
				<td class = "link" value = "plantableajax.mp" style="color:white">plantable</td>
				<td class = "link" value = "viewallajax.mp" style="color:white">view all</td>
			</tr>
		</table>
	</nav>
	
	<!-- Side navigation left -->
	<div class="sidenavL"></div>
	
	<!-- Side navigation right-->
	<div class="sidenavR"></div>

	<div class = "content"></div>

</body>
</html>
