<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<%
		if(session.getAttribute("checkID") == null) {
			response.sendRedirect("./mainPage.jsp");
		};
	%>
	
	<%-- <% 
	String userId = (String)request.getParameter("id");
	System.out.println("id:" + userId);
	%> --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>스케쥴테스트</h3>
</body>
</html>