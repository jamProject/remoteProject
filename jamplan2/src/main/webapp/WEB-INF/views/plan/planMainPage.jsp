<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
	String id = (String)session.getAttribute("id");
	int teamNo = 3;
	session.setAttribute("planNo", 1);
%>

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
	
	
	<style>

	/* 구글 한글폰트 불러오는 곳 
	@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
    
	 {
	   	font-family: 'Jeju Myeongjo', serif;
	   	font-weight: 600;
	  }
	 	구글 한글폰트 불러오는 부분 끝 */
	
		* {box-sizing: border-box;}
	
		body {
			background-color: #EAE6DA; /* #D1B894 */
			height: 100%;
		}
		
		.row {
			margin: auto;
		}
	
		/*>>>>>>>>>>>>>> top bar에 대한 css <<<<<<<<<<<<<<<<*/
		.topnav {
		  position: relative;
		  overflow: hidden;
		  background-color: rgb(72, 81, 103);
		  opacity: 20;
		}
		
		.topnav a {
		  float: left;
		  color: #E7E1C3;
		  text-align: center;
		  padding: 14px 16px;
		  text-decoration: none;
		  font-size: 17px;
		  margin: 0 auto;
		}
		
		.topnav a:hover {
		  background-color: #ddd;
		  color: black;
		}
		
		.topnav-centered a {
		  float: none;
		  position: absolute;
		  top: 50%;
		  left: 50%;
		  transform: translate(-50%, -50%);
		  padding: 20px;
			
		}
		
		.topnav-right {
		  float: right;
		}
		
		.topnav-right a {
			margin-top: 16px;
			margin-bottom: 16px;
			padding-top: 16px; 
		}
		/*>>>>>>>>>>>>>>> top bar에 대한 css 끝 <<<<<<<<<<<<*/
		
		
		#main-container {
			margin: 0 auto;
			margin-top: 120px;
		}
		
		#planManage {
			margin: auto;
		}
		
		.nav-tabs {
			margin: 0 auto;
		}
		/*>>>>>>>>>>>>> aside부분의 채팅창에 대한 css <<<<<<<<<<<<<<<*/

		.card {
			position: absolute;
			bottom: 0;
			float: right;
		}
		
		.card-header {
			padding-right:10px;
			padding-left: 10px;
			
		}
		/*>>>>>>>>>>>> aside부분의 채팅창에 대한 css 끝 <<<<<<<<<<<<*/

		
		/*>>>>>>>>>>>> side bar 세로 정렬 <<<<<<<<<<<<<<<<<<<<<<<*/
		.drawer-nav {
			color: rgba(0, 0, 0, .87);
			background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
							url("https://www.avenlylanetravel.com/wp-content/uploads/2015/07/public-domain-images-free-stock-photos-high-quality-resolution-downloads-public-domain-archive-17-1000x750.jpg");
			
			background-repeat: no-repeat;
			background-attachment: scroll;
		}
		
		.drawer-menu li {
			display: list-item;
			width: 260px;
		}
		
		.drawer-hamburger {
			padding-bottom: 18px;
		}
		/*>>>>>>>>>>>>>>>> 사이드바 세로 정렬 부분 끝 <<<<<<<<<<<<<*/
		
		
		/*>>>>>>>>>>>>>>>> 채팅창에 대한 css <<<<<<<<<<<<<<<<<<*/
		.open-button {
		  background-color: #BD3D3A;
		  color: white;
		  padding: 16px 20px;
		  border: none;
		  cursor: pointer;
		  
		  position: fixed;
		  bottom: 23px;
		  right: 28px;
		  width: 280px;
		}
		
		/*>>>>>>>>>>>>> Enter버튼에 대한 css <<<<<<<<<<<<<<*/
		#sendButton {
			width: 20%;
			padding-right: 10px;
			padding-left: 10px;
			margin-bottm: 0px;
		}
		
		/* The popup form - hidden by default */
		.form-popup {
		  display: none;
		  position: fixed;
		  bottom: 0;
		  right: 15px;
		  border: 3px solid #f1f1f1;
		  z-index: 9;
		}
		
		/* Add styles to the form container */
		.form-container {
		  max-width: 300px;
		  padding: 10px;
		  background-color: white;
		}
		
		/* Full-width input fields */
		.form-container input[type=text] {
		  width: 70%%;
		  padding: 15px;
		  margin: 7px 0 15px 0;
		  border: none;
		  background: #f1f1f1;
		}
		
		/* When the inputs get focus, do something */
		.form-container input[type=text]:focus {
		  background-color: #ddd;
		  outline: none;
		}
		
		/* Set a style for the submit/login button */
		.form-container .btn {
		  background-color: #4CAF50;
		  color: white;
		  padding: 16px 20px;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		  
		  opacity: 0.8;
		}
		
		/* Add a red background color to the cancel button */
		.form-container .cancel {
		  background-color: #B93A32;
		}
		
		/* Add some hover effects to buttons */
		.form-container .btn:hover, .open-button:hover {
		  opacity: 1;
		}
		/*>>>>>>>>>>>>>>> 메시지창을 위한 css 끝 <<<<<<<<<<<<<<<*/
		
		
	</style>
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
				$('.drawer').drawer();
				
				/* var trigEnter = document.getElementById('inputText')
				trigEnter.addEventListener('keyup', function(event) {
					event.preventDefault();
					if(event.keyCode === 13) {
						document.getElementById('sendButton').click();
						var input = document.getElementById('inputText').value;
						w.send(id + "/" + input);
					}
				}); */
				
				document.getElementById("sendButton").onclick = function() {
					var input = document.getElementById('inputText').value;
					w.send(id + "/" + input);
				}
				
		});
		
		// websocket 부분에 대한 스크립트
		var log = function (s) {
			// 이 부분에 메시지 형식 넣어야함.
			document.getElementById("exampleFormControlTextarea3").textContent += (s + "\n");
		}
		
		var id = '<%=id%>';
		var teamNo = '<%=teamNo%>';
		
		w = new WebSocket("ws://localhost:8800/jamplan/planMainChat?id="+id + "&teamNo=" + teamNo);
		// 서버에서 handshaking이 성공적으로 끝나면 자동으로 호출되는 메서드
		w.onopen = function () {
			alert("WebSocket Connected!");
		}
		w.onmessage = function(e) {
			log(e.data.toString());
		}
		w.onclose = function(e) {
			log("WebSocket closed!!");
		}
		w.onerror = function(e) {
			log("WebSocket error!!");
		}
		// websocket 부분에 대한 스크립트 끝
		
		// chat창에 대한 스크립트
		function openForm() {
		    document.getElementById("myForm").style.display = "block";
		}

		function closeForm() {
		    document.getElementById("myForm").style.display = "none";
		}
		
		
		// chat창에 대한 스크립트 끝
		
		
		
		
		//planTable : 불러오기
		function planTableGo() {
			
			
			$.ajax({
				url : '/jamplan2/planTable.plan',
				type : 'POST',
				dataType: "json",
		        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				<%-- data : { 나중에 planNo에대한 데이터 넘겨줘야함.
					"userId" : '<%=userId%>',
					"planNo" : $('#planNo').val(),
					"likeYn" : updateStatus
				}, --%>
				success:function(data){
					$('#planput').empty();
					$('#put').empty();
					
					var put = '';
						put += '<br><br>';
						put += '<a href="javascript:savePlanTable();"><button class="btn btn-primary pull-right" >저장</button></a>';
						$('#put').append(put);
						
					$.each(data, function(index, item){
						var planput = '';
						planput += '<table class="table">';
						planput += '<thead>';
						planput += '<th>날짜 </th>';
						planput += '<th>장소</th>';
						planput += '<th>일정</th>';
						planput += '</thead>';
						planput += '<tbody>';
						planput += '<tr>';
						planput += '<td>' + item.calendar + '</td>';
						planput += '<td>' + item.map + '</td>';
						planput += '<td><textarea class="form-control" id="memo" placeholder="여행계획을 작성해보세요!" rows="5" cols="30" >' + item.memo + '</textarea></td>';
						planput += '</tr>';
						planput += '</tbody>';
						planput += '</table>';
						
						console.log("planput" + planput);
						$('#planput').append(planput);
					});
				},
				error:function() {
					alert('ajax통신실패!!!');
				}
			});
		}
		
		// savePlanTable : 저장
		function savePlanTable(){
			$.ajax({
				url : '/jamplan2/savePlanTable.plan',
				type : 'POST',
				dataType: "json",
		        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		        data : {
		        	"memo" : $('#memo').val()

		        	}, 
		        success:function() {
			        	alert("저장성공!!!");
		        	},
		        	error:function() {
		        		alert('ajax통신실패!!!');
		        	}
		        });
		}
		
		
		
	</script>
</head>
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
						<li><a href="#calendar" data-toggle="tab" class="nav-link active">
								Calendar</a></li>
						<li><a href="#map" data-toggle="tab" class="nav-link">
								Map</a></li>
						<li><a href="javascript:planTableGo();" class="nav-link">
								PlanTable</a></li>
						<li><a href="viewAll" data-toggle="tab" class="nav-link">
								View all</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane container active" id="calendar"></div>
						<div class="tab-pane container fade" id="map"></div>
						<div class="tab-pane container fade" id="planTable"></div>
						<div class="tab-pane container fade" id="viewAll"></div>
					</div>
					
				<!-- DB에 저장하기 -->
				<div id="put"></div>
				
				
				<!-- palnTable 뿌려주기 -->	
				<br><br>
				<div class="container" id="planput">
					
				</div>
			
				
					
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
	<script>
  			
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
  		</script>
</body>
</html>
