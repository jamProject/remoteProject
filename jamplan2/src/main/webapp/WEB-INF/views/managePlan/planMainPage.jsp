<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String id = (String)session.getAttribute("id");
	session.setAttribute("planNo", 1);
	int planNo = (int)session.getAttribute("planNo");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "http://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<body>
	<h1>planMainPage</h1>

	<div>
		<table id="planMainNav">
			<tr>
				<td>
					id : <%=id %> planNo : <%=planNo %>
				</td>
				<%-- <td>${userVO.getId()}</td> --%>
				
				<label>Plan Main</label>
				
				<td><a href="calendar.mp">calendar</a></td>
				<td><a href="map.mp">map</a></td>
				<td><a href="plantable.mp">plan</a></td>
				<td><a href="viewall.mp">view all</a></td>
			</tr>
		</table>
	</div>
</body>
</html>