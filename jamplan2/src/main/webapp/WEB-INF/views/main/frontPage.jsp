<!--한글 인코딩 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	String pagefile=request.getParameter("page");
	if(pagefile==null){pagefile="leftTopButton";}
%>
<!DOCTYPE html>

<html>

<head>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<title>Insert title here</title>

<style>

* {
    box-sizing: border-box;
}

 .navbar {
      padding-top: 15px;
      padding-bottom: 15px;
      border: 0;
      border-radius: 0;
      margin-bottom: 0;
      font-size: 15px;
      letter-spacing: 5px;
      background-color : white;
		 }
  .container-fluid {
      padding-top: 70px;
      padding-bottom: 70px;
  }
  
    .bg-3 { 
      background-color: #ffffff; /* White */
      color: #555555;
  }

.column-left {
    float: left;
    width: 70%;
    padding: 10px;
    height: 500px; /* Should be removed. Only for demonstration */
}
.column-right {
 	float: right;
    width: 30%;
    padding: 10px;
    height: 500px; /* Should be removed. Only for demonstration */
}
.container-fluid{
	float: center;
    padding: 10px;
    height: 400px;
}
</style>

</head>
<body>

<!-- navbar -->
<nav class="navbar navbar-default">
	<div class="container">
	<div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
                       
      </button>
       <a class="navbar-brand" href="#">Jam Plan</a>
        </div>
 		<div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
     	<jsp:include page="leftTopMenu.jsp"/>
      </ul>
    </div>
  </div>
</nav>

<div class="row">
  <div class="column-left" style="background-color:#abc;">
  	<h1 class="margin">JAM PLAN</h1>
</div>
  <div class="column-right" style="background-color: white;">
		<div class="row">
		</div>
    	<jsp:include page='<%=pagefile+".jsp"%>'/>
  </div>
</div>
 <div class="container-fluid bg-3 text-center">    
  <h3 class="margin">추천여행일정</h3><br>
  <div class="row">
    <div class="col-sm-4">
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
      <img src="2014032702818_0.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
      <img src="2014032702818_0.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
    <div class="col-sm-4"> 
     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
      <img src="2014032702818_0.jpg" class="img-responsive margin" style="width:100%" alt="Image">
    </div>
  </div>
</div>
</body>
</html>