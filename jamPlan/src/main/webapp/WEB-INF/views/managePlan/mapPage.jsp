<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script language="JavaScript" src="./map/js/map.js" charset="UTF-8"></script>
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
</script>

<spring:url value="/resources/map/css/semantic.min.css" var="semanticMinCss" />
<spring:url value="/resources/map/css/map.css" var="mapCss" />
<spring:url value="/resources/map/js/semantic.min.js" var="semanticMinJs" />
<spring:url value="/resources/map/js/map.js" var="mapJs" />
 
<link href="${semanticMinCss}" rel="stylesheet" />
<link href="${mapCss}" rel="stylesheet" />
<script src="${semanticMinJs}"></script>
<script src="${mapJs}"></script>

<script
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXzorrjSQ61PxTjiyMHOydxJOq0iEOcaI&callback=initMap&libraries=drawing,places" async defer>
</script>
<!-- <script
 src="https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyB-zbRuhOB5viFlwhkw3TmDnC0XIWcq0HI" >
</script> -->

<style> 
.maptotal{
position:relative;
left: 405px;
top: 112px;} 
</style>
</head>
<body>

<div class="maptotal">
<select class="ui dropdown">
  <option value="0">1월 13일</option>
  <option value="1">1월 14일</option>
  <option value="2">1월 15일</option>
</select>

<div class="ui"></div>
<div class="content">
<input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" />
<div id="main-aside">
<h3> &nbsp;&nbsp;** Top10 ** </h3>
<div class="ui vertical buttons">  			
  <button class="ui button"></button>
  <button class="ui button">2Place</button>
  <button class="ui button">3Place</button>
  <button class="ui button">4Place</button>
  <button class="ui button">5Place</button>
  <button class="ui button">6Place</button>
  <button class="ui button">7Photos</button>
  <button class="ui button">8Photos</button>
  <button class="ui button">9Photos</button>
  <button class="ui button">10Photos</button>
</div>
</div>

<aside  id = "map"> </aside>
</div>
</div>

<!-- 
 <ul  id = "geoData" >
    <li> 전체 주소 : <span  id = "location"> </span > </li >
….
</ul > 
 -->

</body>
</html>