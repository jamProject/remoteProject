<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.spring.jamplan.myroom.TeamVO"%>
<%@page import="com.spring.jamplan.myroom.PlanVO"%>
<%@page import="com.spring.jamplan.myroom.CalendarVO"%>

<%
	if ((String) request.getAttribute("id") == null)
		response.sendRedirect("/jamplan/home.do");

	String id = (String) request.getAttribute("id");
	List<TeamVO> teamList = (List<TeamVO>) request.getAttribute("teamList");

%>
<!DOCTYPE html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->


<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 웹페이지에 대한 설명 -->
<meta name="description"
	content="A planner that helps you make more amused plan and share your own memory">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<style>

/*구글 한글폰트 불러오는 곳*/
@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
    
* {
	font-family: 'Jeju Myeongjo', serif;
}
/*구글 한글폰트 불러오는 부분 끝*/

body {
	color: rgba(0, 0, 0, .87);
	background: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.4)),
		url("https://static1.squarespace.com/static/52da9677e4b03d314575985a/t/575d4453e707eb86c449a4b6/1465730134655/Vienna+Hotels+with+Best+Views+-+Header.jpg?format=1500w");
	background-size: 100% 100%;
	background-repeat: no-repeat;
}

.navbar {
	background-color: rgba(0, 0, 0, .7);
	opacity: 20;
}

.nav-item {
	margin-top: 20px;
	margin-bottom: 20px;
	margin-right: 10px;
	margin-left: 10px;
}

#main-container {
	margin-top: 120px;
}

.nav-item {
	margin-top: 20px;
	margin-bottom: 20px;
	margin-right: 10px;
	margin-left: 10px;
}

#main-container {
	margin-top: 120px;
}

#teamList {
	width: 80%;
	text-align: center;
	background-color: rgba(255, 255, 255, .5);
	border-radius: 5px;
	margin: 0 auto;
}

#teamList>button {
	margin: 0 auto;
	text-align: center;
	
}

/* 버튼 속성 중 display 속성에 대한 변경*/
.btn-outline-light {
	display: block;
	width: 50%;
}

/* modal창 내 input 버튼에 대한 css */
input {
	margin: 40px 25px;
	width: 200px;
	display: block;
	border: none;
	padding: 20px 0;
	border-bottom: solid 1px #1abc9c;
	background: -webkit-linear-gradient(top, rgba(255, 255, 255, 0) 96%,
		#1abc9c 4%);
	background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 96%,
		#1abc9c 4%);
	background-position: -200px 0;
	background-size: 200px 100%;
	background-repeat: no-repeat;
	color: #0e6252;
}

input:focus, input:valid {
	box-shadow: none;
	outline: none;
	background-position: 0 0;
}


input:focus::-webkit-input-placeholder, input:valid::-webkit-input-placeholder
	{
	color: #1abc9c;
	font-size: 16px;
	-webkit-transform: translateY(-20px);
	transform: translateY(-20px);
	visibility: visible !important;
}
/* modal창 내 input 버튼에 대한 css 끝*/

#searchTeamName {
	margin-top: 40px;
	margin-bottom: 40px;
	margin-right: 10px;
	margin-left: 40px;
	width: 200px;
	display: block;
	border: none;
	padding: 20px 0;
	border-bottom: solid 1px #221F2B;
	color: #221F2B;
}

#searchTeamName:focus::-webkit-input-placeholder, #searchTeamName:valid::-webkit-input-placeholder
	{
	color: #221F2B;
	font-size: 16px;
	font-weight: 900;
	visibility: visible !important;
}


#searchButtonParent {
	margin: 40px 0px;
}

#searchButton {
	height: 65px;
	bottom: 0;
	text-color: rgb(54,51,119);
	background-color: rgba(0,0,0, .0);
}

.media {
	background-color: rgba(255, 255, 255, .3);
	border-radius: 5px;
	border: none;
}

ul {
	list-style: none;
	display: inline-block;
}

<!-- 메시지창을 위한 css -->
.panel {
	margin-bottom: 0px;
}

.top-bar {
	background: #666;
	color: white;
	padding: 10px;
	position: relative;
	overflow: hidden;
	padding-right: 5px;
}

.box-position {
	position: fixed;
	bottom: 0;
	width: 100%;
}
<!-- 메시지창을 위한 css 끝-->

footer {
	background-color: rgba(0, 0, 0, .5);
}
</style>
<script>
    
    	<%-- // 메시지를 실시간으로 처리하기 위한 웹소켓 개통 부분
    	var domain = "ws://localhost:8800/jamplan/jamplanWebSocket";
    	function sendMessage() {
    		webSocket = new WebSocket(domain);
    		
    		// 서버와 연결이 성공하면 자동으로 호출되는 메서드
    		webSocket.onopen = function (event) {
    			onOpen(event);
    		};
    		webSocket.onmessage = function (event) {
    			onMessage(event);
    		};
    		webSocket.onerror = function (event) {
    			onError(event);
    		};
    	}
    	
    	function onOpen(event) {
    		webSocket.send('<%=id%>');
    	}
    		
    	function onMessage(event) {
    		$('#countLabel').append(event.data);
    	}
    		
    	function onError(event) {
    	}
		// 메시지를 실시간으로 처리하기 위한 웹소켓 개통 부분 끝 --%>
		
		
		// 배열을 생성하고 팀 이름들을 저장한다.
		var teamNameArray = [];
		
		function ajaxGetTeamList() {
			var id = '<%=id%>';
			$('#teamList').empty();
			$.ajax({
				url : '/jamplan/ajaxPrintTeamList.do',
				type : 'POST',
				data : {
					'id' : id
				},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				dataType : 'json',
				success : function(data) {
					
					//data에 들어있는 데이터 수만큼 반복되고 하나하나가 매번 item에 매핑된다.
					$.each(data,function(index, item) {
							var teamList = '';
							teamList 
									+= '<button class="btn btn-outline-light text-dark border-0 teamList" id="myTeam'
									+ index + '"' 
									+ ' data-toggle="collapse" data-target="#myPlan' 
									+ index 
									+ '">'
									+ item.teamName
									+ '</button>'
									+ '<div id="myPlan' + index + '"' + ' class="collapse">'
									+ 'plan'
									+ '</div>';
							$('#teamList').append(teamList);
							
							// 팀명을 배열에 담고 Add plan 버튼에서의 테이블 생성에 이용한다.
							teamNameArray[index] = item.teamName;
					});
				},
				error : function(e) {
					alert("unload " + e);
				},
			});
		}
		
		
		
		function validationCheck() {
			var params = $('#teamName').val();
			alert(params);
			jQuery.ajax({
				url : '/jamplan/validationCheck.do',
				type : 'GET',
				data : {
					teamName: params
				},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				dataType : 'text',
				// check가 responsebody에서 오는 데이터를 받는다.
				success : function(check) {
					alert(check);
					if (check == 'SUCCESS') {
						$('#validationCheck').val('check');
						$('#validationCheck').css('color', 'powderblue');
						
					} else {
						$('#teamName').val('');
						$('#inputForm').attr('disabled', 'true');
						
						if ($('#teamName').focus()) {
							alert("focus in");
							$('#validationCheck').enable();
						}else {
							alert("focus out");
						}
						
					}
					
				},
				error : function() {
					alert("error!!");
				}
			});
			
		}
		
		/*메세지 팝업창을 위한 스크립트*/
		$(document).on('click', '.panel-heading span.icon_minim', function(e) {
			var $this = $(this);
			if (!$this.hasClass('panel-collapse')) {
				$this.parents('.panel').find('.panel-body').slideUp();
				$this.addClass('panel-collapse');
				$this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
			} else {
				$this.parents('.panel').find('.panel-body').slideDown();
				$this.removeClass('panel-collapse');
				$this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
			}
		});
		/*메세지 팝업창을 위한 스크립트 끝*/
		
		$(document).ready(function() {
					// 페이지가 로드되면 바로 받은 메시지를 보여준다.
					/* sendMessage(); */
					
		
					//로드 되면 업데이트 사항 바로 보여준다.
					
			
					//로드 되면 출력한다.
					ajaxGetTeamList();
					
					$('#addPlan').click(function() {
						
					})
					
					
					// 팀명 클릭했을 경우 팀명을 보내고 해당 팀의 멤버들을 불러와야한다.
					
					<%-- $('.teamList').click(function () {
						var params = $(this);
						alert(params);
						
						jQuery.ajax({
							url : '/jamplan/ajaxPrintPlanList.do',
							type : 'GET',
							data : {
								id: <%=id %>,
								teamName: params
							},
							dataType : 'json',
							contentType : 'application/x-www-form-urlencoded;charset=utf-8',
							// check가 responsebody에서 오는 데이터를 받는다.
							success : function(data) {
								alert(data);
								if(data == null) {
									var nullPlan = '';
									nullPlan += '<h4>일정을 만들어볼까요?</h4>';
									$(this).append(nullPlan);
								}else {
									$.each(data,function(index, item) {
										var planList = '';
										planList 
												+= '<button class="btn btn-outline-light text-dark border-0" id="myTeam'
												+ index + '"' 
												+ ' data-toggle="collapse" data-target="#myPlan' 
												+ index 
												+ '">'
												+ item.teamName
												+ '</button>'
												+ '<div class="collapse" id="myPlan' 
												+ index
												+ '">'
												+ 'hello'
												+ '</div>';
										$(this).append(planList);
										
								});
									
								}
							},
							error : function() {
								alert("일정 목록을 불러올 수 없습니다.");
							}
						});
						// 기본 이벤트 제거
						event.preventDefault();
					}) --%>
					
			
					$('#inputForm').click(function(event) {
						var params = $('#makeTeamForm').serialize();
						alert(params);
						jQuery.ajax({
							url : '/jamplan/makeTeam.do',
							type : 'POST',
							data : params,
							contentType : 'application/x-www-form-urlencoded;charset=utf-8',
							dataType : 'json',
							// check이 responsebody에서 오는 데이터를 받는다.
							success : function(check) {
								if (check == "SUCCESS") {
									ajaxGetTeamList();
									//초기화
									$('#teamName').val('');
								} else {
									alert("Insert Fail!!");
								}
							},
							error : function() {
								alert("에러 발생!!");
							}
						});
						// 기본 이벤트 제거
						event.preventDefault();
						});
			
					
				// 팀 이름에 대해 유효성 체크하는 부분
				$('#validationCheck').click(function () {
					validationCheck();
				})
				
				
				// Add plan 버튼에 대한 클릭 이벤트 부분
				$('#addPlan').click(function () {
					$('#planSpace').empty();
					console.log('테이블 생성하는 부분까지는 들어옴');
					var html = '<table id="teamNameTable" class="table table-hover text-center">'
							 + '<thead><tr><th>No.</th><th>가입된 팀</th>'
							 + '</tr></thead><tbody>';
					console.log('테이블 생성하기 직전');
					for(var index=0; index<teamNameArray.length; index++) {
						html += '<tr><td>'
							 + (index+1)
							 + '</td><td>'
							 + teamNameArray[index]
							 + '</td></tr>';
					}
					
					html += '</tbody></table>';
					console.log('테이블 생성 태그는 모두 완성');
					$('#planSpace').append(html);
					console.log('append했지만 과연??!!');
				})
				
				
				// Add plan 모달 창에서 테이블 내의 각 팀명 클릭 시, 팀명 값을 가져온다.
				$('#teamNameTable tr').click(function () {
					
					var teamNameTable = $('#teamNameTable').val();
					var str = '';		
					var tr = $(this);
					var td = tr.children();
					
					// 값을 잘 받아왔는지 확인
					console.log('td: ' + td);
					str = '' + td + '';
					
					$('#teamNameByTable').val(str);
					alert($('#teamNameByTable').val());
				})
			
			
				// 업데이트된 사항이 있는지 5초마다 체크한다.
				var interval = setInterval(function () {
					var id = '<%=id %>';
					$('#updateSpace').empty();
			        $.ajax({
			            url: '/jamplan/updateCheck.do',
			            type: 'GET',
			            data: {
			            	id: id
			            },
			            dataType: 'json',
			            contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			            success: function (data) {
			            				            	
			            	$.each(data, function(index, item) {
				                var update = '';
				                update  += '<div class="media border p-3">'
				                		+ '<img src="http://alumnes.org/wp-content/uploads/2017/06/fa-user-circle-o-c0a2bd7a.png"' 
		  								+ 'alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
		  								+ '<div class="media-body">'
		  								+ '<h4>' + item.planName + '</h4>'
		  								+ '<p>일정에 변화가 있어요. 확인해 보세요.</p>'
		  								+ '</div>'
		  								+ '</div>'
		  								+ '</br></br>';
		  						$('#updateSpace').append(update);
			            	})
			            },
			            error: function () {
			            	alert("Error");
			            }
			        });
				
				}, 30000)
				
				
				// 팀검색을 시도할 경우 업데이트 사항 보여주기를 멈추고 비슷한 이름들의 팀을 나열해서 보여준다.
				$('#searchButton').click(function () {
					// updateSpace에 나오던 업데이트 작업을 중단한다.
					clearInterval(interval);
				
					var teamName = $('#searchTeamName').val();
					
					// 테이블 형태로 바꿔주기 위해 기존에 있던 업데이트 사항들을 지워준다.
					$('#updateSpace').empty();
					
					var tableHead = '<table class="table table-hover">'
                					+ '<thead><tr>' 
									+ '<th scope="col">팀 이름 검색결과</th>'
									+ '</thead></tr><tbody>';
					
					var tableTail = '</tbody></table>';
					
					console.log('ajax전까지는 왔다.');
					$.ajax({
			            url: '/jamplan/searchTeam.do',
			            type: 'GET',
			            data: {
			            	'teamName': teamName
			            },
			            dataType: 'json',
			            contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			            success: function (data) {
			            
			            	$.each(data, function(index, item) {
				                var update = '';
				                update  += '<tr><td>' 
				                		+ item.teamName
		  								+ '</td></tr>';
		  						$('tableHead').append(update);
			            	})
			            	$('tableTail').append(tableHead);
			            	$('#updateSpace').append(tableTail);
			            },
			            error: function () {
			            	alert("searchTeam ERROR");
			            }
			        });
				});
		})
		
	</script>
<title>Walnut Planner</title>
</head>
<body>
	<header>
		<nav class="navbar fixed-top navbar-expand-sm navbar-dark">
			<a href="#" class="navbar-brand"> Walnut Planner </a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="collapsibleNavbar">
				<ul class="nav navbar-nav">
					<li class="nav-item active"><button type="button" class="btn btn-primary">Message&nbsp;<span id="countLabel" class="label label-primary"></span></button></li>
					<li class="nav-item active"><a><i
							class="material-icons nav-link">account_circle</i></a></li>
					<li class="nav-item active"><a><i
							class="material-icons nav-link">search</i></a></li>
				</ul>
			</div>
		</nav>
	</header>
	<section>
		<div id="main-container" class="container-fluid text-center">
			<div class="row">
				<div id="teamListSpace" class="col-md-3">
					<h4><strong>팀 리스트</strong></h4>
					
					<div id="teamList"></div>
					<div>
					</br>
						<!-- teamList 자리 -->
						<button type="button"
							class="btn btn-outline-primary btn-sm btn-rounded" data-toggle="modal"
							data-target=".myModal"><strong>+TEAM</strong></button>
						<!-- 외부에서 가져온 모달 예제 -->
						<div class="modal fade myModal" id="fullHeightModal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-full-height" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title w-100" id="myModalLabel">
											팀을 만들어보세요</strong></h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form id="makeTeamForm" name="makeTeamForm" method="get">
											<input type="hidden" name="id" id="id" value="<%=id%>">
											<div class="alert alert-info">
												<input name="teamName" id="teamName"
													placeholder="팀이름을 정해보세요" type="text">
												<button id="validationCheck" type="button"
													class="btn btn-xs btn-secondary">중복 확인</button>
											</div>
											<div class="row modal-footer justify-content-center">
												<button type="button" id="inputForm" class="btn btn-primary">추가</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 외부에서 가져온 모달 예제 끝 -->
						<!-- 일정 만들기위한 모달 -->
						<button id="addPlan" type="button"
							class="btn btn-outline-primary btn-sm btn-rounded" data-toggle="modal"
							data-target=".addPlan"><strong>+PLAN</strong></button>
						<div class="modal fade addPlan" id="fullHeightModal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-full-height" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title w-100" id="myModalLabel">일정을 추가해보세요</h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div id="addPlanTable" class="modal-body">
										<div id="planSpace"></div>
										<form id="makePlanForm" name="makePlanForm" method="get">
											<input type="hidden" name="id" id="id" value="<%=id%>">
											<input type="hidden" name="teamName" id="teamNameByTable" value=''>
											<div class="alert alert-info">
												<input name="planName" id="planName"
													placeholder="일정 이름을 정해보세요" type="text">
											</div>
											<div class="row modal-footer justify-content-center">
												<button type="button" id="inputForm" class="btn btn-primary">추가</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 일정 만들기위한 모달 종료-->
					</div>
				</div>
				<div class="col-md-6">
					<div id="teamSearch">
						<div class="span12">
							<form name="searchForm" id="custom-search-form" class="form-search form-horizontal pull-right">
								<div class="input-append span12 row">
									<input type="text" id="searchTeamName" name="teamName" class="search-query" placeholder="어떤 팀을 찾으세요?">
									<div id="searchButtonParent">
									<button id="searchButton" type="button" class="btn">
										<i class="icon-search"><strong>search</strong></i>
									</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div id="updateSpace">
						<div class="media p-3">
							<img
								src="http://alumnes.org/wp-content/uploads/2017/06/fa-user-circle-o-c0a2bd7a.png"
								alt="John Doe" class="mr-3 mt-3 rounded-circle"
								style="width: 60px;">
							<div class="media-body">
								<h4>planName</h4>
								<p>일정에 변화가 있어요. 확인해 보세요.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<aside class="col-md-3">
		
	</aside>
	<!-- Footer -->
	<footer class="text-center">
		<hr class="featurette-divider">
		<a class="up-arrow" href="#myPage" data-toggle="tooltip"
			title="TO TOP">
			<div>
				<ul>
					<li><a href="#">About developers</a></li>
					<li><a href="#">About Walnut</a></li>
					<li><a href="#">About here</a></li>
				</ul>
			</div>
		</a> <br>
		<br>
		<p></p>
	</footer>
</body>
</html>
