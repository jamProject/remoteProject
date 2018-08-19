<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
	<%
		if(session.getAttribute("checkID") == null) {
			response.sendRedirect("./mainPage.jsp");
		};
		
		String userId = (String)session.getAttribute("checkID");

	%>
	
	
	
	
	
	
	<%-- <% 
	String userId = (String)request.getParameter("id");
	System.out.println("id:" + userId);
	%> --%>
<html>
<head>
<title>Insert title here</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	$('#planNo').val(${planNo});
	likeGet();
});



function likeGet() {
	/* 유저가 하트를 체크했는지 안했는지 체크해주는 코드 써주기 : if~ */
	
	$.ajax({
		url : '/jamplan2/heartCheck.search',
		type : 'POST',
		dataType: "json",
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : {
			"userId" : '<%=userId%>',
			"planNo" : $('#planNo').val()
		},
		success:function(data){
			/* alert(data.likeYn); */
			var likeput = '';
			
			if('N' == data.likeYn){
				likeput = '<h1><a href="javascript:likeFunc();"><i class="far fa-heart" id="noneHeart" value="N" style= "color : #E75450;" ></i></a></h1>';
				$('#likeStatus').val('N');
			}
			else{
				likeput = '<h1><a href="javascript:likeFunc();"><i class="fas fa-heart " id="fullHeart" style= "color : #E75450;"  ></i></a></h1>';
				$('#likeStatus').val('Y');
			}
			

				console.log("likeput:" + likeput);
				$('#likeput').html(likeput);
		},
		error:function(){
			alert("ajax통신 실패!!!");
		}
	});
	
	
	
}



function likeFunc() {
	/* 유저가 하트를 체크했는지 안했는지 체크해주는 코드 써주기 : if~ */
	/* alert($('#likeStatus').val()); */
	var likeStatus = $('#likeStatus').val();
	var updateStatus ='';
	if(likeStatus=='Y'){
		updateStatus='N'
	}else{
		updateStatus='Y'
		
	}
	
	$.ajax({
		url : '/jamplan2/likeUpdate.search',
		type : 'POST',
		dataType: "json",
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : {
			"userId" : '<%=userId%>',
			"planNo" : $('#planNo').val(),
			"likeYn" : updateStatus
		},
		success:function(data){
			/* alert(data.likeYn); */
			var likeput = '';
			
			if('N' == data.likeYn){
				likeput = '<h1><a href="javascript:likeFunc();"><i class="far fa-heart" style= "color : #E75450;" ></i></a></h1>';
				$('#likeStatus').val('N');
			}
			else{
				likeput = '<h1><a href="javascript:likeFunc();"><i class="fas fa-heart " id="fullHeart" style= "color : #E75450;"  ></i></a></h1>';
				$('#likeStatus').val('Y');
			}
			

				console.log("likeput:" + likeput);
				$('#likeput').html(likeput);
		},
		error:function(){
			alert("ajax통신 실패!!!");
		}
	});
	
	
	
}




</script>

</head>
<body>
	<!-- <style>
		#text1 { color : #E75450; }
	</style> -->
	<style>
	button {
		opacity: 0;
		
		}
	</style>
	
	<h3>스케쥴테스트</h3>
	<div id="likeput"></div>
	
	<input type="text" id="likeStatus" hidden="hidden">
	<input type="text" id="planNo" hidden="hidden">
	
</body>
</html>