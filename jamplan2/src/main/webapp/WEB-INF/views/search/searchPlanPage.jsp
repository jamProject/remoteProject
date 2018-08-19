<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<!DOCTYPE html>
<html>
<head>
<title>된다!!!</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- <style>
	.thumbnail a>img{
		display: block;
    	max-width: 100%;
    /* height: auto; */
	}
</style> -->


<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ /* (document).ready는 html문서가 로딩이완료되면 자동으로 실행 */
	//목록
	function selectData(){ 
		//table내부 내용을 제거(초기화), 동적으로 제거
		
		
		
		
		
		
		/*
 		$('#scdIdCheck').click(function) {
			schedulePage 뿌려줄 function기능 넣기
		} 
		*/
		
		
		/*
		//로그인한 유저인지 체크..
		 //id session check
  		$('#scdIdCheck').click(function(){
		var checkID = null;
		if(session.getAttribute("checkID") != null) {
			alert("로그인 성공!");
		}
		else {
			
		}
		}); */

		
		
		$.ajax({ //jquery에서 ajax호출할때 사용하는 방식 / jQuery.ajax=$.ajax : jquery 표현하는방식 2가지 
			url:'/jamplan2/getPlanList.search',
			type:'POST',
			dataType:"json",
			contentType:'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){ // 응답(getPeopleJSON.do)이 존재하면 함수 실행된다. ,  getPeopleJSON.do에서 실행한 값이 data에 저장된다
				$.each(data, function(index, item){ // data 는 PeopleVO의 list들이 저장되어 item에 값(항목들)이 주어진다. / each는 data가 가지고있는 값만큼 반복수행하게해준다.(각각의데이터접근시)
					var output = '';
					output += '<div class="col-sm-4">';
					output += '<div class="thumbnail">';
					output += '<div><a href = "schedule.search?planNo='+ item.planNo +'"><img src="/jamplan2/image/' + item.image + '" style="width:400px; height:400px;"  />' + '</a></div>';
					output += '<span>' + item.planDate + '</span>' + '&nbsp' + '&nbsp';
					output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
					output += '<button class="btn goodCount">' + item.goodCount + '</button>' + '&nbsp' + '&nbsp';
					output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp' + '&nbsp';
					output += '</div>';
					output += '</div>';
					console.log("output:" + output);
					$('#output').append(output);
				});
			},
			error:function(){
				alert("ajax통신 실패!!!");
			}
		});
	}
	
	
	
	
	
	
	//이미지클릭시 스케쥬럴로click 시 planName 정보를 받아서 전달할수있게, 이미지를눌렀을때 보내는것, 나중에 코딩해보기.
	$('#이미지_클릭').click(function(event){
		var params = $("#이미지_폼").serialize(); // serialize - 직렬화(문자열) 시켜주는것 ex) id=O&name=O&.... 식으로 (키&밸류) 5가지던 100가지던 문자열형태로로..
		jQuery.ajax({
			url : '/jamplan2/이미지컨트롤러',
			type : 'POST',
			data : params,
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : "json",
			success : function (retVal) { // retVal 에 PeopleController-/insertPerson.do의 return retVal값이 담긴다
				if (retVal.res == "OK") {
					//데이터 성공일때 이벤트 작성
					selectData();
					//스케쥴러뿌려줄양식
					//코딩
				}
				else
					{
						alert("Insert Fail!!!");
					}
			},
			error:function(){
				alert("ajax통신 실패!!!");
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});

	
	 $('.li1').click(function(){
			
			var name = $(this).html();
			console.log(name);
			$('.dropdown-toggle').html(name);
			
			if (name == '날짜') {
				$('#dateCheck').css('display', 'block');
			}
			else {
				$('#dateCheck').css('display', 'none');
			}
		});
	 
	
	
	
	 var tagEvent = '';
	 $('.tag').click(function (){ 
         
    	var params = {};
    	//var name = $(this).html();
    	var tagEvent = $('.dropdown-toggle').html();
    	alert(tagEvent);
    	switch(tagEvent) {
    		case '날짜' :
    			params = {'planDate' : $('.form-control').val()};
    			console.log("날짜");
    			break;
    		case '제목' :
    			params = {'planName' : $('.form-control').val()};
    			console.log("제목");
    			alert(params);
    			break;
           //날짜값 자르는 변수하나 만들어줘서 넘기자.   
     
      
      
	    
    			
    	}
    	  alert("3");
         $.ajax({
        	 url:'/jamplan2/planSearch.search',
            type:'POST',
            dataType: "json",
            contentType : 'application/x-www-form-urlencoded; charset=utf-8',
            data : params,
            	/* {
            	'planDate' : $('.form-control').val()
            	//위의 코딩은 예제로 일정네임으로 검색할수있게해준것, 조회수,추천순,최신순,날짜순 출력하수있게 하기
            }, */
            
         	success:function(data) {
         

           $('#output').empty();
            /* alert(data); */
        	 $.each(data, function(index, item){ 
        		 /* alert(item.image); */
					var output = '';
					output += '<div class="col-sm-4">';
					output += '<div class="thumbnail">';
					output += '<div><a href = "schedule.search?planNo='+ item.planNo +'"><img src="/jamplan2/image/' + item.image + '" style="width:400px; height:400px;"  />' + '</a></div>';
					output += '<span>' + item.planDate + '</span>' + '&nbsp' + '&nbsp';
					output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
					output += '<button class="btn goodCount">' + item.goodCount + '</button>' + '&nbsp' + '&nbsp';
					output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp' + '&nbsp';
					output += '</div>';
					output += '</div>';
					console.log("output:" + output);
					$('#output').append(output);
				});
			},
			error:function(){
				alert("ajax통신 실패!!!");
			}
		});         
      

	 }); 
	
      
      
      var clickevent = '';
      $('.clk').click(function(event){
    	  
         var params = {};
          var clickevent = $(this).attr('id');
          alert(clickevent);
            switch(clickevent) {
              case 'newDateClick':
            	  params = {'planDate' : 'a'};
            	  alert(params);
                  break;
              case 'readCountClick':
            	   params = {'readCount' : 1};
                  break;
              case 'goodCountClick':
            	   params = {'goodCount' : 2};
                  break;
              case 'dateClick':
            	   params = {'planName' : 'b'};
            	   alert(params);
                  break;
            }
  		
  		jQuery.ajax({
  			url : '/jamplan2/clickSearch.search',
  			type : 'POST',
  			data : params,
  			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
  			dataType : "json",
  			success : function (data) { // retVal 에 PeopleController-/insertPerson.do의 return retVal값이 담긴다
  				
  				$('#output').empty();
  	            //alert(data);
  	        	 $.each(data, function(index, item){ 
  	        		 //alert(item.planName);
  						var output = '';
  						output += '<div class="col-sm-4">';
  						output += '<div class="thumbnail">';
  						output += '<div><a href = "schedule.search?planNo='+ item.planNo +'"><img src="/jamplan2/image/' + item.image + '" style="width:400px; height:400px;"  />' + '</a></div>';
  						output += '<span>' + item.planDate + '</span>' + '&nbsp' + '&nbsp';
  						output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
  						output += '<button class="btn goodCount">' + item.goodCount + '</button>' + '&nbsp' + '&nbsp';
  						output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp' + '&nbsp';
  						output += '</div>';
  						output += '</div>';
  						console.log("output:" + output);
  						$('#output').append(output);
  					});
  				},
  				error:function(){
  					alert("ajax통신 실패!!!");
  				}
  			}); 
  	});
      
      
  	

      
      
  	
  	selectData();
  }); 
    
  	
  
  
  



	/*       
	 selectData();
	
	 }); */
</script>







<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JAM plan</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="#">일정 찾기</a></li>
					<li><a href="#">동행 찾기</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span
							class="glyphicon glyphicon-unchecked"></span>My Room</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Sign Up</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- <nav class="navbar fixed-top navbar-expand-sm bg-warning navbar-dark">
		<nav class="container">
			<a href="" class="navbar-brand"> 
				<img src="/search/image/sswiss2.jpg" alt="">
			</a>
			<button class="navbar-toggler">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse justify-content-end" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="nav-item"><a class="nav-link" href="#myroom">My Room</a></li>
					<li class="nav-item"><a class="nav-link" href="#signup">Sign Up</a></li>
					<li class="nav-item"><a class="nav-link" href="#login">Login</a></li>
				</ul>
			</div>
		</nav>
   	</nav> -->
	
	<div class="carousel-inner" role="listbox">
		<img src="/jamplan2/resources/search/image/swiss7.jpg" alt="photo" width="1520" height="400" />
		<div class="carousel-caption">
			<h3>일정 찾기!</h3>
			<p>다른사람들의 일정을 파헤쳐보자!!</p>
		</div>
	</div>

	<br>
	<br>
	<br>
	<br>

	<div style="width: 400px;" class="center-block clearfix">
		<div class="container">
			<div class="col-sm-4">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="원하시는 일정을 검색해보세요!" /> <span class="input-group-btn">

						<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
							선택 <span class="caret"></span>
						</button>

						<div class="dropdown-menu">
							<li><a class="li1">제목</a></li>
							<li><a class="li1">날짜</a></li>
						</div> <!-- <button class="btn btn-default tag" type="button" id="planDateTag" >
							<span class="glyphicon glyphicon-search"></span>
						</button> -->

						<button class="btn btn-default tag" type="button">
							<span class="glyphicon glyphicon-search"></span>
						</button>


						<div class="input-group date"  id="dateCheck" style=" display : none;" >
							<!-- <input type="text" class="form-control"> -->
							<span class="input-group-addon"><input type="date"></span>
						</div>



					</span>
				</div>
			</div>
		</div>
	</div>

	<br>
	<br>

	<div class="col-sm-5">
		<div class="btn-group">
			<button type="button" class="btn btn-primary clk" id="newDateClick">최신순</button>
			<button type="button" class="btn btn-primary clk" id="readCountClick">조회순</button>
			<button type="button" class="btn btn-primary clk" id="goodCountClick">추천순</button>
			<button type="button" class="btn btn-primary clk" id="dateClick">제목</button>
		</div>
	</div>

	<br>
	<br>
	<br>
	<br>

	<div id="output"></div>

	<!-- <div class="row text-center">
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss2.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Fri. 27 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss3.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Sat. 28 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss6.jpg" alt="matterhorn" width="400" height="300">
				<p>
					<strong>matterhorn</strong>
				</p>
				<p>Sun. 29 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
	</div>
	<div class="row text-center">
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss2.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Fri. 27 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss3.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Sat. 28 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss6.jpg" alt="matterhorn" width="400" height="300">
				<p>
					<strong>matterhorn</strong>
				</p>
				<p>Sun. 29 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
	</div>
	<div class="row text-center">
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss2.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Fri. 27 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss3.jpg" alt="brienz" width="400" height="300">
				<p>
					<strong>brienz</strong>
				</p>
				<p>Sat. 28 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="thumbnail">
				<img src="/jamplan2/resources/search/image/swiss6.jpg" alt="matterhorn" width="400" height="300">
				<p>
					<strong>matterhorn</strong>
				</p>
				<p>Sun. 29 November 2015</p>
				<button class="btn">like</button>
			</div>
		</div>
	</div> -->

	<!-- Footer -->
	
	<footer class="text-center">
		<div class="col-md-12">
		<a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TOP"> <span class="glyphicon glyphicon-chevron-up"></span>
		</a><br> <br>
		<p>
			JAM Theme Made By <a href="https://www.jamplan.com" data-toggle="tooltip" title="Visit w3schools">www.jamplan.com</a>
		</p>
		</div>
	</footer>
	

</body>
</html>