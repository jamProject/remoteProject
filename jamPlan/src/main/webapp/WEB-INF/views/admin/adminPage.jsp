<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, com.spring.jamplan.admin.*,com.spring.jamplan.model.*" %>
<%
List<UserVO> userList = (ArrayList<UserVO>)request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
</script>
<spring:url value="/resources/admin/css/semantic.min.css" var="semanticMinCss" />
<spring:url value="/resources/admin/js/semantic.min.js" var="semanticMinJs" />
<link href="${semanticMinCss}" rel="stylesheet" />
<script src="${semanticMinJs}"></script>
<title>adminPage</title>
<style>
 .admin-title{
 	padding: 20px 20px 20px;
   }
  
  .search-group{
  	margin-left: 50px;
  	
  }
 table{
 	width: 60%;
 	margin-left: 310px;
 	margin-top: 110px; 
 	border: 2px solid #C8C8C8;
 	border-collapse: collapse;
 	table-layout:auto;	
	
 }
 th, td {
    border: 1px solid #C8C8C8;
  }
 
 .ui dropdown{
	margin-left: 150px;
 	padding: 20px 40px 20px;
 }
 
 .ui icon input{
 	margin-left: 100px;
 }
</style>
</head>
<body>
<h1 class="admin-title">회원 관리 페이지</h1>
<div class="ui secondary pointing menu">
  <a class="active item" href="/getUserList.admin">
      회원찾기
  </a>
  <a class="item">
     팀찾기
  </a>
  <a class="item">
     일정찾기
  </a>
</div>
<!--<div class="search-group">
 <select class="ui dropdown">
  <option value="0">아이디</option>
  <option value="1">팀</option>
  <option value="2">동행</option>
</select> -->
<div class="ui icon input">
  <input type="text" placeholder="아이디 검색">
  <!-- <i class="search icon"></i> -->
</div>
<button class="ui button">검색</button>

<table >
  <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>이메일</th>
      <th>성별</th>
      <th>SNS주소</th>
      <th>가입일</th>
      <th>회원정보+</th>
      <th>회원삭제</th>
      <th>메세지발송</th>
    </tr>
  </thead>
  <tbody>
	<%
		for (int i=0; i<userList.size(); i++)
		{
			UserVO user = userList.get(i);
	%> 
			<tr>
		      	<td><%=user.getId() %></td>
		      	<td><%=user.getName() %></td>
		      	<td><%=user.getEmail() %></td>
		      	<td><%=user.getGender() %></td>
		      	<td><%=user.getSnsLink() %></td>
		      	<td><%=user.getSignDate() %></td>
		      	<td><button class="ui button">보기</button></td>
		      	<td><button class="ui button">삭제</button></td>
		      	<td><button class="ui button">메시지</button></td>
   			</tr>
   	<%
		}
   	%>		
   	
  </tbody>
   <tfoot>
    <tr><th colspan="9">
      <div class="ui right floated pagination menu">
        <a class="icon item">
          <i class="left chevron icon"></i>
        </a>
        <a class="item">1</a>
        <a class="item">2</a>
        <a class="item">3</a>
        <a class="item">4</a>
        <a class="icon item">
          <i class="right chevron icon"></i>
        </a>
      </div>
    </th>
  </tr></tfoot>
</table>
</body>
</html>