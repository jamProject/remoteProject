<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
</script>

<spring:url value="/resources/map/css/semantic.min.css" var="semanticMinCss" />
<spring:url value="/resources/map/css/map.css" var="mapCss" />
<spring:url value="/resources/map/js/semantic.min.js" var="semanticMinJs" />
<spring:url value="/resources/map/js/map2.js" var="mapJs"/>
 
<link href="${semanticMinCss}" rel="stylesheet" />
<link href="${mapCss}" rel="stylesheet" />
<script src="${semanticMinJs}"></script>
<script src="${mapJs}"></script>
<%
String id = request.getParameter("id");
%>
<script
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXzorrjSQ61PxTjiyMHOydxJOq0iEOcaI&callback=initMap&libraries=drawing,places" async defer>
</script>

<style> 
.maptotal{
position:relative;
left: 405px;
top: 112px;
} 

/* #infowindow-content {
      display: none;
}
#map #infowindow-content {
        display: inline;
 } */

</style>
</head>
<body>
<h1>왜안되냐</h1>
<input type="hidden" id="memberid" value=<%=id %> /> 
<input type="text" id="pickCount" value=""/>
<input type="text" id="lat" value=""/>
<input type="text" id="lng" value=""/>

<input id="confirm" type="button" value="확정" />
<input id="reset" type="button" value="취소" />
<div class="maptotal">
<!-- <select class="ui dropdown">
  <option value="0">1월 13일</option>
  <option value="1">1월 14일</option>
  <option value="2">1월 15일</option>
</select> -->


<div class="ui"></div>
<div class="content">
<input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" />
<div id="main-aside">
<h3> &nbsp;&nbsp;** Top10 ** </h3>
<div class="ui vertical buttons">  			
  <button class="ui button">1place</button>
  <button class="ui button">2place</button>
  <button class="ui button">3place</button>
  <button class="ui button">4place</button>
  <button class="ui button">5place</button>
  <button class="ui button">6place</button>
  <button class="ui button">7place</button>
  <button class="ui button">8place</button>
  <button class="ui button">9place</button>
  <button class="ui button">10place</button>
</div>
</div>

<aside  id = "map"> </aside>
<div id="infoContent" style="display:none">
<table >					
	<tr><th align=left id="placeName"></th></tr>	
	<tr><td id="address"></td></tr>
	<tr>
		<td>selectMember</td>
	    <td><input id ="pickBtn" type="button" value="Pick"></td>
	    <td><input id="cancelBtn" type="button" value="Cancel"></td>
	</tr>
</table>
<table id="output">

</table>
</div>

</div>
</div>

</body>
</html>