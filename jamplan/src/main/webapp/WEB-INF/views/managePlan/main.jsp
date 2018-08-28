<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"crossorigin="anonymous"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<spring:url value="/resources/planMain/js/planMain.js" var="mainPageJs" />
	<spring:url value="/resources/planMain/css/planMain.css" var="mainPageCss" />
	
	<script src="${mainPageJs }"></script>
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
	
		<!-- jquery & iScroll -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.2.0/iscroll.min.js"></script>
		<!-- drawer.js -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/js/drawer.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/css/drawer.min.css">
	
	<!-- Full screen video -->
	<script src="http://pupunzi.com/mb.components/mb.YTPlayer/demo/inc/jquery.mb.YTPlayer.js"></script>
	
	<title>JAM</title>

<%
	session.setAttribute("id", "thkim9198");
	session.setAttribute("teamNo", "14");
%>

	<style>
		/*구글 한글폰트 불러오는 곳*/
		@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
    
		* {
			font-family: 'Jeju Myeongjo', serif;
			font-weight: 600;
		}
/*구글 한글폰트 불러오는 부분 끝*/
	</style>
	<script>
	
	$(document).ready(function () {
		$("#sendButton").click(function() {
			var input = $('#inputText').val();
			w.send('${id}' + "/" + input);
		});
		
		$('.drawer').drawer({
			  class: {
			    nav: 'drawer-nav',
			    toggle: 'drawer-toggle',
			    overlay: 'drawer-overlay',
			    open: 'drawer-open',
			    close: 'drawer-close',
			    dropdown: 'drawer-dropdown'
			  },
			  iscroll: {
			    preventDefault: false
			  },
			  showOverlay: true
			});
		
		$('.drawer').drawer();
		
		
	})
	
	// 배경 이미지에 파스텔 효과 주는 부분
	var hue = Math.floor(Math.random() * 360);
		var pastel = 'hsl(' + hue + ', 100%, 87.5%)';
		$('body').css('background-color', pastel);
		
		
	//websocket 부분에 대한 스크립트
	var log = function (s) {
		// 이 부분에 메시지 형식 넣어야함.
		document.getElementById("chatTextarea").textContent += (s + "\n");
	}
	
	
	var splitNameList = function (s) {
		
		var nameList = s.split('/');
<<<<<<< HEAD
		console.log(nameList);
		/* nameList = nameList.substring(0, nameList.length); */
=======
		var nameArr = [];
>>>>>>> cb02e4721272d71a2d4bb3f7d11f5486c75ced4d
		
		// 해당 아이디를 가진 유저들의 정보를 불러오기 위한 ajax
		$.ajax({
			url : 'onUserList.mp',
			type : 'POST',
			data : {
				'nameList' : nameList
			},
			traditional : true,
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "json",
			success : function (data) {
				console.log(data);
				for(var name in data) {
					imageUp(name, data[name]);
				}
			},	
			error:function(){
				alert("페이지 이동 ajax실패")
			}
		});
	}
	
	//채팅방에 팀원이 접속하면 해당 팀원의 프로필 사진을 띄운다.
<<<<<<< HEAD
	var imageUp = function (e) {
		var image = document.createElement("image");
		var chat = document.getElementById('chat');
		image.setAttribute("src", "<spring:url value='/image/" + ${e.image} + "'/>");
=======
	var imageUp = function (name, imageName) {
		var profileImage = document.getElementById('profileImage');
		var image = document.createElement("img");
		
		image.setAttribute("src", "<spring:url value='/image/" + imageName + "'/>");
>>>>>>> cb02e4721272d71a2d4bb3f7d11f5486c75ced4d
		image.setAttribute("alt", 'Avatar');
		image.setAttribute("class", 'avatar');
		image.setAttribute("title", name);
		chat.appendChild(image);
	}

	var id = '${id}';
	var teamNo = '3';
	
	w = new WebSocket("ws://localhost:8800/jamplan/planMainChat?id="+id + "&teamNo=" + teamNo);
	// 서버에서 handshaking이 성공적으로 끝나면 자동으로 호출되는 메서드
	w.onopen = function () {
		console.log("WebSocket Connected!");
	}
	w.onmessage = function(e) {
		
		alert("success");
		if(e.data.toString().substring(0,8) == 'nameList') {
			splitNameList(e.data.toString());
		}else {
			log(e.data.toString());
		}
		
	}
	w.onclose = function(e) {
		alert("closed");
		console.log('닫힌다는 메시지가 창에 뜨기 전');
		log("WebSocket closed!!");
	}
	w.onerror = function(e) {
		alert("failure");
		log("WebSocket error!!");
	}
	// websocket 부분에 대한 스크립트 끝

	</script>
</head>
<body>
	<div class="drawer drawer--left">
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
		    <a href="myInfo.info?id=${id }">My Info</a>
		    <a href="#about">Search</a>
		  </div>
		</div>
	</div>
	<div class="slideshow-container">
		<!-- quotes slide 들어가는 부분 -->
	</div>
	<div id="main-container" class="container-fluid text-center">
		<div class="row h-50">
			<div class="col-md-2"></div>
			<div id="planManage" class="col-md-7">
			<!-- ul태그에 css 변경 -->
				<ul class="nav nav-tabs" id="contentTabs" role="tablist">
					<li class="nav-item"><a href="#calendar" data-toggle="tab" role="tab" class="nav-link active">
							Calendar</a></li>
					<li class="nav-item"><a href="#map" data-toggle="tab" role="tab" class="nav-link">
							Map</a></li>
					<li class="nav-item"><a href="#planTable" data-toggle="tab" role="tab" class="nav-link">
							PlanTable</a></li>
					<li class="nav-item"><a href="viewAll" data-toggle="tab" role="tab" class="nav-link">
							View all</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane container active" value = "calendarajax.mp" id="calendar"><h1>hello</h1></div>
					<div class="tab-pane container fade" value = "mapajax.mp" id="map"></div>
					<div class="tab-pane container fade" value = "plantableajax.mp" id="planTable"></div>
					<div class="tab-pane container fade" value = "viewallajax.mp" id="viewAll"></div>
				</div>
			</div>
			<div id="chat" class="col-md-3">
				<!-- 아바타 이미지 들어가는 곳 -->
				<div id="profileImage"></div>
				<button class="open-button" onclick="openForm()">Chat</button>
				<div class="form-popup" id="myForm">
					<div class="form-container">
				   <!--  	<label for="exampleFormControlTextarea3">Rounded corners</label> -->
    					<textarea class="form-control" id="chatTextarea" readonly rows="10"></textarea>
				    	<input id="inputText" type="text" placeholder="대화를 해보세요" required>
					
					   	<button id="sendButton" class="btn">Enter</button>
				    	<button type="submit" id="cancelButton" class="btn cancel" onclick="closeForm()">Chat close</button>
				  </div>
				</div>
					
			</div>
		</div>
	</div>
	<footer>
	</footer>
</body>
</html>
