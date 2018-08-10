<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <!--한글 인코딩 -->

<!DOCTYPE html>
<html lang="en">

<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src = "http://code.jquery.com/jquery-3.2.1.min.js"></script>
  
  <spring:url value="/resources/mainPage/js/mainPageJs.js" var="mainPageJs" />
  <spring:url value="/resources/mainPage/css/mainPageCss.css" var="mainPageCss" />
  
  <script src="https://code.jquery.com/jquery-3.3.1.js"
	  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		  crossorigin="anonymous"></script>
  <script src="${mainPageJs}"></script>
  <link href="${mainPageCss}" rel="stylesheet" />
 
</head>
<body>
	<h1>mainPage </h1>
	<div class="container1">
		<div class="row">
		<div class="left">
			<div class="col-sm-4">
			<h1>jam plan</h1>
			</div>
			<div class="col-sm-4">
			</div>
		</div>
		<div class="right">
			<div class="col-sm-4">							
				<form class="login" action="login.main" method="post" onsubmit="return check()">
					<input value="id" type = "text" name = "id">
					<input value="pass" type = "password" name = "pass">
					<h3><button type = "submit" value="로그인">로그인</button></h3>
				</form>
				<form class="join" action="join.main" method="post">
				
					<input value="email" type = "text" name = "email">
					<input value="id" type = "text" name = "id">
						<input type="button" value="중복확인" onclick="idCheck()">
					
					<input value="pass" type = "password" name = "pass">
					<input type = "submit" value="회원가입">
				
				</form>						
			</div>
			</div>
		</div>
	</div>
	<div class="container2">
		<div class="row">
			<div class="col-sm-8">
				<h3>페이지 이름입니다.</h3>
				<p>페이지 소개글입니다.</p>
			</div>	
			<div class="col-sm-4">
            	<div class="rightMenu">
     			<div class="subrightMenu">
       				<div class="Menu">
         				<h3><button class=loginBut>login</button></h3>
            			<h3><button class=joinBut>join</button></h3>
            			<h3><button class=loginsnsBut>Login/join with naver</button></h3>
       				</div>
     			</div>
   				</div>	
			</div>
		</div>
	</div> 
	<div class="container3">
		<div class="row">
			<div class="col-sm-4">
				<h3>1</h3>
				<p>일정</p>
			</div>
			<div class="col-sm-4">
				<h3>2</h3>
				<p>일정</p>
			</div>	
			<div class="col-sm-4">
				<h3>3</h3>
				<p>일정</p>
			</div>
		</div>
	</div>

</body>
</html>