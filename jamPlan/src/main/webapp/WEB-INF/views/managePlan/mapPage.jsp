<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<!-- <link rel="stylesheet" type="text/css" href="/WEB-INF/views/managePlan/semantic.min.css">-->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>  -->

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/semantic.min.css">
  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/semantic.min.js"></script>

<script
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB-zbRuhOB5viFlwhkw3TmDnC0XIWcq0HI&callback=initMap&libraries=drawing,places" async defer>
</script>

   <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map.
      #googleMap{
        height: 50%;
        width: 50%;
        position: fixed;
        left: 400px;
  		top: 200px;
  		border: 3px solid #73AD21;        
      } */
      
#map {
	float: left;
    width: 600px;
    height: 400px;
    margin: 0 10px 0 0;
	border: 3px solid #73AD21;
}
.controls {
    margin-top: 10px;
    border: 1px solid transparent;
    border-radius: 2px 0 0 2px;
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    height: 32px;
    outline: none;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}
#searchInput {
    background-color: #fff;
    font-family: Roboto;
    font-size: 15px;
    font-weight: 300;
    margin-left: 12px;
    padding: 0 11px 0 13px;
    text-overflow: ellipsis;
    width: 50%;
}
#searchInput:focus {
    border-color: #4d90fe;
}
#main-aside{
	float: right;
width: auto;
} 
.content {
	margin: 5px;
	padding: 5px;
	border: 1px solid #C8C8C8;
	position: fixed; 
    left: 400px;
  	top: 150px;
}
 </style>

</head>
 
<body>
<input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" />
<div class="content">
<aside id="main-aside">
<h3> &nbsp;&nbsp;** Top10 ** </h5>
<div class="ui vertical buttons">  
  <button class="ui button">1one </button>
  <button class="ui button">2Messages</button>
  <button class="ui button">3Events</button>
  <button class="ui button">4Photos</button>
  <button class="ui button">5Photos</button>
  <button class="ui button">6Photos</button>
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

<script>
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {			/*지도생성, 지도를 표시할 div아이디가'map' */
    	center: {lat: 37.566, lng: 126.977},			/* 지도의 중심좌표 */
      zoom: 10
    });
    var input = document.getElementById('searchInput');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);

   // var ps = new google.maps.services.Places(); 
    var infowindow = new google.maps.InfoWindow();   
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    marker.setIcon(({
        url: "http://simpleicon.com/wp-content/uploads/map-marker-13.png",
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(35, 35)
    }));
    
    autocomplete.addListener('place_changed', function() {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
        }
  
        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }
       
        marker.setPosition(place.geometry.location);
        marker.setVisible(true);
        var address = '';
        if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }
        
        infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        infowindow.open(map, marker); 
        
  //Location details
        for (var i = 0; i < place.address_components.length; i++) {
            if(place.address_components[i].types[0] == 'postal_code'){
                document.getElementById('postal_code').innerHTML = place.address_components[i].long_name;
            }
            if(place.address_components[i].types[0] == 'country'){
                document.getElementById('country').innerHTML = place.address_components[i].long_name;
            }
        }
        document.getElementById('location').innerHTML = place.formatted_address;
        document.getElementById('lat').innerHTML = place.geometry.location.lat();
        document.getElementById('lon').innerHTML = place.geometry.location.lng();
    });
    
   
    google.maps.event.addListener(map, 'click', function(mouseEvent) {
    	/* searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
            if (status === google.maps.services.Status.OK) {
                var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                
                var content = '<div class="bAddr">' +
                                '<span class="title">법정동 주소정보</span>' + 
                                detailAddr + 
                            '</div>';
            }   
    	}); */
    	 marker.setPosition(mouseEvent.latLng);
    	 marker.setMap(map);    
    	 
    	 if(infowindow.is(":visible")){
 			infowindow.close();
 		}else{  
         infowindow.open(map, marker);
   		} 
    	
      });
}
</script>
</body>
</html>