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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Document</title>
</head>
<body>
<!--네비게이션-->

		<nav class="navbar fixed-top navbar-expand-sm bg-success navbar-dark">
        <a href="" class="navbar-brand">
            <i class="far fa-calendar-alt"></i>&nbsp;&nbsp;jamplan
        </a>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!--메뉴 모음-->
        <div class="collapse navbar-collapse justify-content-end" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="nav-item active"><a class="nav-link" href="#login">login</a></li>
                <li class="nav-item"><a class="nav-link" href="#login">join</a></li>
                <li class="nav-item"><a class="nav-link" href="#search">Search</a></li>
            </ul>
        </div>
        </nav>
        <!--Carousel-->
			    	<div class="carousel-inner"  id="login">
            <div class="carousel-item active">
                <img src="/jamplan/resources/mainPage/img/dog_carousel.jpg" alt="cat">
                <div class="carousel-caption">
                    <h1>강아지</h1>
 					<p>귀여운 강아지가 재미있게 놉니다.</p>
                </div>
            </div>
            <div class="carousel-item">
                    <img src="/jamplan/resources/mainPage/img/dog_carousel.jpg" alt="cat">
                    <div class="carousel-caption">
                        <h1>강아지</h1>
                        <p>귀여운 강아지가 재미있게 놉니다.</p>
                    </div>
            </div>
            <div class="carousel-item">
                    <img src="/jamplan/resources/mainPage/img/dog_cat_carousel.jpg" alt="cat">
                    <div class="carousel-caption">
                        <h1>고양이</h1>
                        <p>고양이와 강아지는 친구입니다.</p>
                    </div>
            </div>
        </div>  
		<div class="container1">
			<div class="row">
				<div class="left col-sm-12">
					
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
     			<!-- 모달버튼 -->
           				<div class="btn-group-vertical">
                			<button class="loginBut btn btn-success" data-toggle="modal" data-target="#myModalLogin">login</button>
                			<button class="joinBut btn btn-success" data-toggle="modal" data-target="#myModalJoin">join</button>
                			<button class="loginsnsBut btn btn-success" data-toggle="modal" data-target="#myModalSns">sns</button>
            			</div>
        			</div>
     			</div>
   			</div>	
		</div>
	</div>
			
	   		<div class="container text-center output" id="search">
        	
        
        	<div class="row">
        	
            	<div class="col-md-4">
                	<div class="card">
                    	<img src="/jamplan/resources/mainPage/img/dog.jpg" alt="" class="card-img-top">
                    	<div class="card-body">
                        	<h3 class="card-title">눈 내린 겨울</h3>
                        	<p class="card-text">눈이 좋다. 겨울이 좋다.<br>
                            					눈길을 걸을 수 있는 겨울이 좋다.
                        </p>
                        <a href="#" class="btn btn-primary btn-block">자세히 보기</a>
                    </div>
                	</div>
            	</div>
           		<div class="col-md-4">
               		<div class="card">
                       <img src="/jamplan/resources/mainPage/img/dog.jpg" alt="" class="card-img-top">
                       <div class="card-body">
                           <h3 class="card-title">바다의 여름</h3>
                           <p class="card-text">바다가 좋다. 여름이 좋다<br>
                               				 바다를 즐길 수 있는 여름이 좋다
                           </p>
                           <a href="#" class="btn btn-danger btn-block">자세히 보기</a>
                       </div>
               		</div>
           		</div>
           		<div class="col-md-4">
               		<div class="card">
                       <img src="/jamplan/resources/mainPage/img/dog.jpg" alt="" class="card-img-top">
                       <div class="card-body">
                           <h3 class="card-title">바다의 여름</h3>
                           <p class="card-text">바다가 좋다. 여름이 좋다<br>
                               				 바다를 즐길 수 있는 여름이 좋다
                           </p>
                           <a href="#" class="btn btn-danger btn-block">자세히 보기</a>
                       </div>
               		</div>
           		</div>
        		</div>
        		
    			</div> 
    			
	  <!--모달창 login-->
				    <div id="myModalLogin" class="modal">
				        <!--모달의 크기 결정-->
				        <!--modal-sm, modal-lg-->
				        <div class="modal-dialog">
				
				            <!-- 모달의 본 컨텐츠 login-->
				            <div class="modal-content">
				                <!--헤더 , 바디, 푸터-->
				                <div class="modal-header">
				                    <h4 class="modal-title">login</h4>
				                    <button class="close" type="button" data-dismiss="modal">&times;</button>
				                </div>
				                <div class="modal-body">  
				                <form class="login" action="login.do" method="post" onsubmit="return check()">
				                    <div class="form-group col-sm-8">
                						<label for="usr">ID:</label>
               		 					<input type="text" class="form-control" id="usr" name="id">
           							</div>
            						<div class="form-group col-sm-8">
                						<label for="pwd">비밀번호:</label>
                						<input type="password" class="form-control" id="pwd" name="pass">
            						</div>
            						<div class="btn btn-lg">
				                        <button type=submit class="btn btn-primary btn-block">확인</button>

				                    </div>
				                    <div class="btn btn-lg">
				                        <button class="btn btn-danger btn-block">취소</button>
				                    </div> 
            					</form>
				                </div>
				                <!--modal-footer 에는 row를 쓸 필요가 없다 -->
				                <div class="modal-footer"> 
				                	<div class="col-sm-12">
            						<button class="btn btn-success btn-block"><a href="snsLogin.do">네이버 아이디로 로그인</a></button>
				                	</div>
				                    
				                </div>
				            </div>
				        </div>
				    </div>
				    
		 <!--모달창  join-->
				    <div id="myModalJoin" class="modal">
				        <!--모달의 크기 결정-->
				        <!--modal-sm, modal-lg-->
				        <div class="modal-dialog">
				
				            <!-- 모달의 본 컨텐츠 join-->
				            <div class="modal-content">
				                <!--헤더 , 바디, 푸터-->
				                <div class="modal-header">
				                    <h4 class="modal-title">join</h4>
				                    <button class="close" type="button" data-dismiss="modal">&times;</button>
				                </div>
				                <div class="modal-body">
				                    <p>본 구역에 내용을 삽입하세요</p>
				                    <img src="/jamplan/resources/mainPage/img/dog.jpg" alt="dog">
				                </div>
				                <!--modal-footer 에는 row를 쓸 필요가 없다 -->
				                <div class="modal-footer"> 
				                    <div class="col-md-6">
				                        <button class="btn btn-default btn-block">확인</button>
				                    </div>
				                    <div class="col-md-6">
				                        <button class="btn btn-danger btn-block">취소</button>
				                    </div> 
				                </div>
				            </div>
				        </div>
				    </div>
				    
				    <!--모달창 snslogin-->
				    <div id="myModalSns" class="modal">
				        <!--모달의 크기 결정-->
				        <!--modal-sm, modal-lg-->
				        <div class="modal-dialog">
				
				            <!-- 모달의 본 컨텐츠 sns-->
				            <div class="modal-content">
				                <!--헤더 , 바디, 푸터-->
				                <div class="modal-header">
				                    <h4 class="modal-title">sns login</h4>
				                    <button class="close" type="button" data-dismiss="modal">&times;</button>
				                </div>
				                <div class="modal-body">
				                    <p>본 구역에 내용을 삽입하세요</p>
				                    <img src="/jamplan/resources/mainPage/img/dog.jpg" alt="dog">
				                </div>
				                <!--modal-footer 에는 row를 쓸 필요가 없다 -->
				                <div class="modal-footer"> 
				                    <div class="col-md-6">
				                        <button class="btn btn-default btn-block">확인</button>
				                    </div>
				                    <div class="col-md-6">
				                        <button class="btn btn-danger btn-block">취소</button>
				                    </div> 
				                </div>
				            </div>
				        </div>
				    </div>
</body>
</html>