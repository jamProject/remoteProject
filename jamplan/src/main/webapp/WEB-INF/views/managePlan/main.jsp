<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- <!DOCTYPE html>
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
</head> --%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<spring:url value="/resources/planMain/js/planMain.js" var="mainPageJs" />
	<spring:url value="/resources/planMain/css/planMain.css" var="mainPageCss" />
	
	<script src="${mainPageJs}"></script>
	<link href="${mainPageCss}" rel="stylesheet" />
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/css/drawer.min.css">
		<!-- jquery & iScroll -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.2.0/iscroll.min.js"></script>
		<!-- drawer.js -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/js/drawer.min.js"></script>
	
	<title>JAM</title>

<%
	String id = (String)session.getAttribute("id");
%>


	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>

<body>
<!-- <body style="height: 1500px">

	<h1>planMainPage</h1>
	<div>
		<label>Plan Main</label>
	</div>
	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top" id ="navCenter">
		<table>
			<tr>
				<td class = "linkMp" value = "calendarajax.mp" style="color:white">calendar</td>
				<td class = "linkMp" value = "mapajax.mp" style="color:white">map</td>
				<td class = "linkMp" value = "plantableajax.mp" style="color:white">plantable</td>
				<td class = "linkMp" value = "viewallajax.mp" style="color:white">view all</td>
			</tr>
		</table>
	</nav>
	
	Side navigation left
	<div class="sidenavL"></div>
	
	Side navigation right
	<div class="sidenavR"></div>

	<div class = "content"></div>
 -->
 <body class="drawer drawer--left">
	<header>
		<div role="banner">
			<!-- side bar 삽입 -->
			<button type="button" class="drawer-toggle drawer-hamburger">
	      		<span class="sr-only">toggle navigation</span>
	      		<span class="drawer-hamburger-icon"></span>
	    	</button>
	   		<!-- side bar에 내용을 넣을거라면 여기에!!! -->
	    	<nav class="drawer-nav" role="navigation">
	     		<ul class="drawer-menu"> 
	        		<li><h2><a class="drawer-brand" href="#">Jam Planner</a></h2></li>
	       			<li><a class="drawer-menu-item" href="#">Nav1</a></li>
	        		<li><a class="drawer-menu-item" href="#">Nav2</a></li>
	      		</ul>
	    	</nav>
    	</div>
    	<!-- top bar 들어가는 부분 -->
		<div class="topnav">
		  <!-- Centered link -->
		  <div class="topnav-centered">
		    <h2><strong><a href="#home" class="active">Jam Planner</a></strong></h2>
		  </div>
		  <!-- Right-aligned links -->
		  <div class="topnav-right">
		    <a href="#search">Message</a>
		    <a href="#about">Search</a>
		  </div>
		</div>
	</header>
	<section>
		<div id="main-container" class="container-fluid text-center">
			<div class="row">
				<div class="col-md-2"><h1>사이드바</h1></div>
				<div id="planManage" class="col-md-7">
					<ul class="nav nav-tabs">
					<!-- 	<li><a href="#calendar" data-toggle="tab" class="nav-link active">Calendar</a></li>
						<li><a href="#map" data-toggle="tab" class="nav-link">Map</a></li>
						<li><a href="#planTable" data-toggle="tab" class="nav-link">PlanTable</a></li>
						<li><a href="viewAll" data-toggle="tab" class="nav-link">View all</a></li> -->
						<li><a value="calendarajax.mp" data-toggle="tab" class="nav-link active moveLink">Calendar</a></li>
						<li><a value="mapajax.mp" data-toggle="tab" class="nav-link moveLink">Map</a></li>
						<li><a value="plantableajax.mp" data-toggle="tab" class="nav-link moveLink">PlanTable</a></li>
						<li><a value="viewallajax.mp" data-toggle="tab" class="nav-link moveLink">View all</a></li>
					</ul>
					<div class="tab-content divContent">
					<!-- 	<div class="tab-pane container active" value = "calendarajax.mp" id="calendarContent"></div>
						<div class="tab-pane container fade" value = "mapajax.mp" id="map"></div>
						<div class="tab-pane container fade" value = "plantableajax.mp" id="planTable"></div>
						<div class="tab-pane container fade" value = "viewallajax.mp" id="viewAll"></div> -->
					</div>
					<!-- <div class = "content"></div> -->
				</div>
				<div class="col-md-3">
					<h1>채팅창</h1>
					<button class="open-button" onclick="openForm()">Chat</button>
					<div class="form-popup" id="myForm">
						<div class="form-container">
					   <!--  	<label for="exampleFormControlTextarea3">Rounded corners</label> -->
    						<textarea class="form-control" id="exampleFormControlTextarea3" readonly rows="10"></textarea>
					    	<input id="inputText" type="text" placeholder="대화를 해보세요" required>
					
					    	<button id="sendButton" class="btn">Enter</button>
					    	<button type="submit" id="cancelButton" class="btn cancel" onclick="closeForm()">Chat close</button>
					  </div>
					</div>
					
				</div>
			</div>
		</div>
	</section>
	<footer>
	</footer>
</body>
</html>
