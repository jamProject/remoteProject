<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.spring.jamplan.myroom.TeamVO"%>
<%@page import="com.spring.jamplan.myroom.PlanVO"%>

<%
	if ((String) request.getAttribute("id") == null)
		response.sendRedirect("/jamplan/home.do");

	String id = (String) request.getAttribute("id");
	List<TeamVO> teamList = (ArrayList<TeamVO>) request.getAttribute("teamList");
	List<PlanVO> planList = (ArrayList<PlanVO>) request.getAttribute("planList");
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
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="A planner that helps you make more amused plan and share your own memory">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Walnut Planner</title>

<!-- Google material design -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.teal-red.min.css">

<!-- mdl-custom~은 내가 직접 만든 식별자임. -->

<!-- jQuery ajax -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
.mdl-layout__header {
	min-height: 80px;
}

.mdl-layout__header {
	background-color: rgba(0, 150, 136, 0.5);
}

.mdl-layout__header-row {
	margin: 10px;
}

.mdl-custom-material-icons {
	margin: 10px;
}

ul {
	list-style: none;
}

a {
	text-decoration: none;
	color: black;
}

#mdl-custom-teamList-header {
	display: block;
	margin: auto;
}

#mdl-custom-teamList-header {
	text-align: center;
}

.mdl-button {
	font-weight: normal;
}

#demo-menu-lower-left {
	width: 100%;
	margin: 0px 0px;
}

#demo-list-item {
	display: none;
}

.mdl-custom-plus-button {
	display: block;
	margin: auto;
}

.mdl-dialog {
	width: 450px;
	height: 297px;
}

.mdl-dialog__title {
	line-height: 54px;
}

.demo-card-wide.mdl-card {
	width: 512px;
}

.demo-card-wide>.mdl-card__title {
	color: #fff;
	height: 176px;
	background: url('resources/images/vienna.jpg') center/cover;
}

.mdl-card__title-text {
	font-weight: 500;
}

.demo-card-wide>.mdl-card__menu {
	color: #fff;
}
</style>
<script>

	function ajaxGetTeamList() {
		var id = '${teamList.get(0).getId()}';
		$.ajax({
			url : '/jamplan/ajaxPrintTeamList.do',
			type : 'POST',
			data : {
				'id' : id
			},
			contentType : 'application/x-www-form-urlencoded;charset=utf-8',
			dataType : 'json',
			async : false,
			success : function(data) {
				$('#teamList').empty();
				//data에 들어있는 데이터 수만큼 반복되고 하나하나가 매번 item에 매핑된다.
				$.each(data,function(index, item) {
						var teamList = '';
						alert("성공하기는 했음");
						console.log(data);
						teamList += '<button id="demo-menu-lower-left" class="mdl-button mdl-js-button mdl-button--icon" data-toggle="collapse" data-target="list">'
								+ item
								+ '</button>';
								+ '<div id="list" class="collapse">'
								+ '</div>'
						
						$('#teamList').append(teamList);
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
				if (check == "SUCCESS") {
					$('span input').val('check');
					$('span input').css('color', 'powderblue');
					
				} else {
					$('#teamName').val('');
					$('#inputForm').attr('disabled', 'true');
					
					if ($('#teamName').focus()) {
						alert("focus in");
						$('span input').attr('disabled', 'false');
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
	
	
	$(document).ready(function() {
		
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
			
			
			
			//로드 되면 출력한다.
			ajaxGetTeamList();
				
			
			$('#demo-menu-lower-left').click(function (event) {
				$('#demo-list-item').css('display', 'block');
			})
			
			
			// dialog창 열고 닫는 부분
			var dialog = document.querySelector('dialog');
			var showDialogButton = document.querySelector('#show-dialog');
			if (!dialog.showModal) {
				dialogPolyfill.registerDialog(dialog);
			}
			showDialogButton.addEventListener('click', function() {
				dialog.showModal();
			});
			dialog.querySelector('.close').addEventListener(
					'click', function() {
						dialog.close();
					});
			
			
			// 실시간 업데이트 체크
			// 
			var timeOutId = 0;
			setInterval(function () {
				
			        $.ajax({
			            url: '/jamplan/updateCheck.do',
			            success: function (response) {
			                if (response == 'True') {
			                    
			                } else {	//Fail check?
			                    timeOutId = setInterval(ajaxFn, 5000);	//타이머 재설정
			                    console.log("call");	//타이머 작동하는지 체크
			                  
			                }
			            },
			            error: function () {
			            	
			            },
			            dataType: 'json',
			            complete: ajaxFn,
			            timeout: 10000
			        });
				
			}, 5000)
			
			$('.demo-card-wide').click(function (e) {
				$(this).attr('')
			})
		});
</script>

</head>
<body>
	<div
		class="demo-layout mdl-layout mdl-layout--fixed-header mdl-js-layout mdl-color--grey-100">
		<header
			class="demo-header mdl-layout__header mdl-layout__header--scroll mdl-color--teal-green-70 mdl-color-text--grey-800">
			<div class="mdl-layout__header-row">
				<span class="mdl-layout-title"><strong>Walnut
						Planner</strong></span>
				<div class="mdl-layout-spacer"></div>
				<button class="mdl-button mdl-js-button">
					<i class="material-icons">email</i>
				</button>
				<button class="mdl-button mdl-js-button">
					<i class="material-icons">account_circle</i>
				</button>
				<div
					class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
					<label class="mdl-button mdl-js-button mdl-button--icon"
						for="search"> <i class="material-icons">search</i>
					</label>
					<div class="mdl-textfield__expandable-holder">
						<input class="mdl-textfield__input" type="text" id="search">
						<label class="mdl-textfield__label" for="search">Enter
							your query...</label>
					</div>
				</div>
			</div>
		</header>
		<div class="demo-ribbon"></div>
		<main class="demo-main mdl-layout__content">
		<div class="demo-container mdl-grid">
			<nav class="mdl-cell mdl-cell--3-col">
				<div class="container">
					<h4 id="mdl-custom-teamList-header">Team list</h4>
				</div>
				<div>
					<div id="teamList">
					//teamList 들어가는 부분
						//일정 목록 불러오는 부분
						<div>
							<ul id="demo-list-item mdl-list"
								class="mdl-menu mdl-menu--bottom-left mdl-js-menu mdl-js-ripple-effect"
								for="demo-menu-lower-left">
								<li class="mdl-menu__item">hello</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="container">
					<button id="show-dialog" type="button"
						class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
						+ TEAM</button>
					<dialog class="mdl-dialog">
					<h4 class="mdl-dialog__title">Team</h4>
					<form id="makeTeamForm" name="makeTeamForm" method="post">
						<fieldset>
							<div
								class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
								<input type="hidden" name="id"
									value="${teamList.get(0).getId() }" /> <input
									class="mdl-textfield__input" type="text" id="teamName"
									name="teamName" /> <label class="mdl-textfield__label"
									for="teamName">Set your team name</label>
							</div>
							<span><input type="button" class="mdl-button" value="valid" onclick="validationCheck();"/></span>
						</fieldset>
						<div class="mdl-dialog__actions">
							<button type="button" class="mdl-button close">close</button>
							<input type="button" value="add" class="mdl-button" id="inputForm" />
						</div>
					</form>

					</dialog>
					<script>
						
					</script>
				</div>
			</nav>
			<section class="row">
				<!-- Wide card with share menu button -->
				<div
					class="demo-card-wide mdl-card mdl-shadow--2dp mdl-cell mdl-cell--6-col">
					<div class="mdl-card__title">
						<h2 class="mdl-card__title-text">Plan</h2>
					</div>
					<div class="mdl-card__supporting-text">Your friend has just
						posted...</div>
					<div class="mdl-card__actions mdl-card--border">
						<a href="/jamplan/"
							class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
							who posted </a>
					</div>
					<div class="mdl-card__menu">
						<button
							class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
							<i class="material-icons">share</i>
						</button>
					</div>
				</div>
			</section>
			<aside class="mdl-cell mdl-cell--6-col">
				<article>
					<ul>
						<!-- This is where D-DAY should be placed. -->
					</ul>
				</article>
			</aside>
		</div>
		<footer class="demo-footer mdl-mini-footer">
			<div class="mdl-mini-footer--left-section">
				<ul class="mdl-mini-footer--link-list">
					<li><a href="#">About developers</a></li>
					<li><a href="#">About Walnut</a></li>
					<li><a href="#">About here</a></li>
				</ul>
			</div>
		</footer> </main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
