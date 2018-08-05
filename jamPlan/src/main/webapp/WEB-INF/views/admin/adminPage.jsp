<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<spring:url value="/resources/admin/css/thems/default/assets/fonts/icon.*" var="semanticFontsCss" />
<spring:url value="/resources/admin/js/semantic.min.js" var="semanticMinJs" />

<link href="${semanticMinCss}" rel="stylesheet" />
<link href="${semanticFontsCss}" rel="stylesheet" />
<script src="${semanticMinJs}"></script>
<title>adminPage</title>
<style>
 .admin-title{
 	padding: 20px 20px 20px;
    border-bottom: 2px solid #C8C8C8;
   }
   
 table{
 	width: 75%;
 	margin-left: 150px;
 	margin-top: 100px; 
 	border: 2px solid #CCCCCC;
 	border-collapse: collapse;
 }
 th, td {
    border: 1px solid #CCCCCC;
  }
 
 .ui dropdown{
 	padding: 20px 40px 20px;
 }
</style>
</head>
<body>
<h1 class="admin-title">회원 관리</h1>
<select class="ui dropdown">
  <option value="0">일반회원</option>
  <option value="1">관리자</option>
</select>
<select class="ui dropdown">
  <option value="0">아이디</option>
  <option value="1">팀</option>
  <option value="2">동행</option>
</select>
<table >
  <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>이메일</th>
      <th>성별</th>
      <th>SNS주소</th>
      <th>회원삭제</th>
      <th>메세지발송</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>John</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
    <tr>
      <td>Jamie</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
    <tr>
      <td>Jill</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
    <tr>
      <td>John</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
    <tr>
      <td>Jamie</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
    <tr>
      <td>Jill</td>
      <td>존이다</td>
      <td>Johnhi@haha.com</td>
      <td>남자</td>
      <td>Johnhi@haha.com2</td>
      <td><button class="ui button">삭제</button></td>
      <td><button class="ui button">메시지</button></td>
    </tr>
  </tbody>
   <tfoot>
    <tr><th colspan="7">
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