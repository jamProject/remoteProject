<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<%@ page session="false"%>
<html>
<head>
<title>JAM Plan</title>
<spring:url value="/resources/calendar/js/calendarJs.js" var="calJs" />
<spring:url value="/resources/calendar/css/calendarCss.css" var="calCss" />

<script src="${calJs}"></script>
<link href="${calCss}" rel="stylesheet" />
</head>

<body>
<div id ="divBox">
	<h1 id = "calendarHead">달력</h1>

	<div id = "selectTableDiv">
		<table id ="selectTable">
			<tr>
				<td>
					<div id="nowYear"></div>
				</td>
				<td>
					<div id="nowMonth"></div>
				</td>
			</tr>
		</table>
	</div>
	
	
	<div id="calendarDiv"></div>
	<div id="calendar"></div>
</div>

</body>
</html>