<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"crossorigin="anonymous"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<spring:url value="/resources/myInfo/js/myInfo.js" var="myInfoJs" />
		<spring:url value="/resources/myInfo/css/myInfo.css" var="myInfoCss" />
	
		<script src="${myInfoJs }"></script>
		<link href="${myInfoCss}" rel="stylesheet" />
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/css/drawer.min.css">
		<!-- jquery & iScroll -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.2.0/iscroll.min.js"></script>
		<!-- drawer.js -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/js/drawer.min.js"></script>
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		
		<style>
		/*구글 한글폰트 불러오는 곳*/
		@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
    
		* {
			font-family: 'Jeju Myeongjo', serif;
			font-weight: 600;
		}
		/*구글 한글폰트 불러오는 부분 끝*/
		</style>
		
		<title>Jam Planner</title>
	</head>
	<body>
		<header>
	    	<!-- top bar 들어가는 부분 -->
			<div class="topnav">
			  <!-- Centered link -->
			  <div class="topnav-centered">
			    <h2><strong><a href="#home" class="active">Jam Planner</a></strong></h2>
			  </div>
			  <!-- Right-aligned links -->
			  <div class="topnav-right">
			    <a href="#search">Message</a>
			    <a href="#myInfo">My Info</a>
			    <a href="#about">Search</a>
			  </div>
			</div>
		</header>
		<section class="container-fluid">
			<div id="main-container" class="row content">
				<div class="col-sm-3 sidenav">
					<h1>사이드바</h1>
					<ul class="nav nav-pills flex-column">
				        <li class="nav-item active"><a class="nav-link" href="#section1">Main</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section2">My Room</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section3">Search</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section3">Photos</a></li>
				    </ul><br>
				    <div class="input-group">
				      	<input type="text" class="form-control" placeholder="Search Blog..">
				        <span class="input-group-btn">
				       		<button class="btn btn-primary" type="button">
				        		<i class="fas fa-search"></i>
				        	</button>
				        </span>
				    </div>
				</div>
				<div class="col-sm-9">
					
					<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item">
								<a class="nav-link active" id="info-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">
									내 정보</a></li>
							<li class="nav-item"><a class="nav-link" id="teamManage-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">
									팀 관리</a></li>	
							<div class="search-container">
							    <form action="/">
							      <input type="text" placeholder="Search ..." name="search">
							      <button type="submit">Submit</button>
							    </form>
							</div>
					</ul>
					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" value="info.mp" id="info" role="tabpanel" aria-labelledby="info-tab">
							<div style="display:flex;width:100%;">
							    <form id="infoForm" action="/" method="post">
							        <div style="width:100%;">
							            <p>FULL NAME</p>
							            <input name="name" autofocus>
							        </div>
							        <div style="width:100%;">
							            <p>IDENTIFICATION</p>
							            <input name="id">
							        </div>
							
							        <div style="width:47%;">
							            <p>EMAIL</p>
							            <input name="email">
							        </div>
							        <div style="width:47%;margin-left:6%">
							            <p>PHONE</p>
							            <input name="phone">
							        </div>
							    </form> 
							</div>
						</div>
						<div class="tab-pane fade" value="teamManage.mp" id="teamManage" role="tabpanel" aria-labelledby="teamManage-tab"></div>
					</div>
				</div>
			</div>
		</section>
		<footer class="container-fluid">
  			<p>Footer Text</p>
		</footer>
	</body>
</html>

<!-- 
<ul class="nav nav-tabs">
							<li><a href="#calendar" data-toggle="tab" class="nav-link active">
									Calendar</a></li>
							<li><a href="#map" data-toggle="tab" class="nav-link">
									Map</a></li>
							<li><a href="#planTable" data-toggle="tab" class="nav-link">
									PlanTable</a></li>
							<li><a href="viewAll" data-toggle="tab" class="nav-link">
									View all</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane container active" value = "calendarajax.mp" id="calendar"></div>
							<div class="tab-pane container fade" value = "mapajax.mp" id="map"></div>
							<div class="tab-pane container fade" value = "plantableajax.mp" id="planTable"></div>
							<div class="tab-pane container fade" value = "viewallajax.mp" id="viewAll"></div>
						</div>
 -->