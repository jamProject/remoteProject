<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<spring:url value="/resources/mainPage/js/mainPageJs.js" var="mainPageJs" />
  	<spring:url value="/resources/mainPage/css/mainPageCss.css" var="mainPageCss" />
	<script src="${mainPageJs}"></script>
 	<link href="${mainPageCss}" rel="stylesheet" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Document</title>
</head>
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
<body style="height:1500px">
	<h1>planMainPage</h1>

	<div>
		<table id="planMainNav">
			<tr>
				<td>
					id : <%=id %> planNo : <%=planNo %>
				</td>
				<%-- <td>${userVO.getId()}</td> --%>
				
				<label>Plan Main</label>
			</tr>
		</table>
	</div>
	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
  <a class="navbar-brand" href="calendar.manageplan">calendar</a>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="map.manageplan">map</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="plantable.manageplan">plan</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="viewall.manageplan">view all</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="upload.manageplan">file upload</a>
  </ul>
</nav>
	
</body>
</html>