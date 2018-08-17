<%@page import="com.spring.jamplan.model.CalendarVO"%>
<<<<<<< HEAD
<%-- <%@page import="org.springframework.boot.autoconfigure.web.ServerProperties.Session"%> --%>
=======

>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import ="com.spring.jamplan.model.PlanVO" %>
<!DOCTYPE html>

<%@ page session="false"%>
<html>
<head>
<title>JAM Plan</title>

		  
<spring:url value="/resources/calendar/js/calendarJs.js" var="calJs" />
<spring:url value="/resources/calendar/css/calendarCss.css" var="calCss" />

<%
	/*session 객체 접근 불가 시 리퀘스트에서 session 객체 생성  */
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
<<<<<<< HEAD
	int role = (int)session.getAttribute("role");
=======
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
%>	

<script src="https://code.jquery.com/jquery-3.3.1.js"
	  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		  crossorigin="anonymous"></script>
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
<<<<<<< HEAD
	<div>

	<%
		if(role==0 || role ==1){
	%>		
		<form>
			<input type = "button" id = "save" value="저장하기" />
		</form>
	<% 
		}
	%>
	
	</div>
=======
	
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
	<div>
	   <script type="text/javascript">
	   </script>
	</div>
	
	<div id="calendarDiv"></div>
	<div id="calendar"></div>
</div>

</body>
</html>