<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>

<%@page import="com.spring.jamplan.myroom.TeamVO" %>
<%

	if((String)request.getAttribute("id") == null) 
		response.sendRedirect("/jamplan/home.do");
	
	String id = (String)request.getAttribute("id");
	List<TeamVO> teamList = (ArrayList<TeamVO>)request.getAttribute("teamList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Walnut Planner</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
		
			$(document).ready(function () {
				function selectData() {
					var id = '${teamList.get(0).getId()}';
					$.ajax({
						url: '/jamplan/ajaxPrintTeamList.do',
						type: 'POST',
						data: {'id':  id },
						contentType: 'application/x-www-form-urlencoded;charset=utf-8',
						dataType: 'json',
						success: function (data) {
							$('#teamList').empty();
							//data에 들어있는 데이터 수만큼 반복되고 하나하나가 매번 item에 매핑된다.
							$.each(data, function(index, item) {
								var teamList = '';
								teamList += '<tr>';
								teamList += '<td>' + item.teamName + '</td>';
								teamList += '<tr>';
								
								$('#teamList').append(teamList);
							});
						},
						error:function(e) {
							alert("목록을 로드하지 못했습니다.!!" + e);
						},
						
					});
				}
				
				$('#inputForm').click(function (event) {
					var params = $('#makeTeamForm').serialize();
					alert(params);
					jQuery.ajax({
						url: '/jamplan/makeTeam.do',
						type: 'POST',
						//key-value 구조로 적어주기만 하면 된다.
						data: params,
						contentType: 'application/x-www-form-urlencoded;charset=utf-8',
						dataType: 'json',
						// retVal이 responsebody에서 오는 데이터를 받는다.
						success: function (check) {
							// 여기서 res는 retVal 안에 있는 key를 의미함.
							if(check == "SUCCESS") {
								selectData();
								//초기화
								$('#teamName').val('');
								
							}else {
								alert("Insert Fail!!");
							}
						},
						error: function() {
							alert("에러 발생!!");
						}
						
					});
					// 기본 이벤트 제거
					event.preventDefault();
				});
				
			});
		</script>
		<style>
			ul {
				list-style : none;
			}
		</style>
</head>
<body>
	<!-- 팀 목록 출력하는 공간 -->
	<h3>Team List</h3>
	<div id="teamList">
		<table>
			<c:forEach var="team" items="${teamList}">
			<tr>
				<li>${team.getTeamName() }</li>
			</tr>
			</c:forEach>
		</table>
	</div>

	<div class="container">
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal">+ Team</button>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">팀 만들기</h4>
					</div>
					<div class="modal-body">
					
					<!-- 팀 만들기 -->
						<form id="makeTeamForm" name="makeTeamForm" method="post">
							<fieldset>
								
								<ul>
									<li>
										<input type="hidden" name="id" value="${teamList.get(0).getId() }" />
										<label for="teamName">팀명</label>
										<input type="text" name="teamName" id="teamName"/>
										<input type="button" value="중복 확인" id="validationCheck" onclick="validationCheck()" />
									</li>
								</ul>
							</fieldset>
							<input type="button" value="추가" id="inputForm" />
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>
	<div></div>
	<div></div>
</body>
</html>