<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-zbRuhOB5viFlwhkw3TmDnC0XIWcq0HI&callback=initMap&libraries=drawing,places" async defer>
</script>



<style>  
</style>
</head>
<body>
<input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" />
<div class="content">
<aside id="main-aside">
<h3> &nbsp;&nbsp;** Top10 ** </h5>
<div class="ui vertical buttons">  			
  <button class="ui button">1Place </button>
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
</aside>
<aside  id = "map"> </aside>
</div>

<!-- 
 <ul  id = "geoData" >
    <li> 전체 주소 : <span  id = "location"> </span > </li >
….
</ul > 
 -->

</body>
</html>