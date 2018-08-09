// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var markersArray = [];
var coordinates= [];
var travelPathArray = [];
var map;
var marker;
var up_lng;
var up_lat
var up_latlng;

function initMap() {
	
	//up_lat = "37.5562989";
	//up_lng = "126.9220863";
	//up_latlng = new google.maps.LatLng(up_lat, up_lng);

    map = new google.maps.Map(document.getElementById('map'), {			/*지도생성, 지도를 표시할 div아이디가'map' */
    	center: {lat: 37.566, lng: 126.977},			/* 지도의 중심좌표 */
    	zoom: 18,
    	/*styles:[{
            featureType:"poi",
            elementType:"labels",
            stylers:[{
                visibility:"on"
                //clickable: "true"
            }]  	
        }]*/
    });
    
   // coordinates.push(up_latlng);        

	// 마커의 수만큼 반복하여 coordinates에 push된 위치 정보값을 계산 후 
	// 맵의 zoom level과 center를 맵에 적용
   // bounds = new google.maps.LatLngBounds();
    
   /* for (var i=0; i < 2; i++) {
    	bounds.extend(coordinates[i]);
	}
	map.fitBounds(bounds);*/
	
	//addMarker(up_latlng);
	//addMarker(car_latlng);
	 
 	var input = document.getElementById('searchInput');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);			//지도or위성

    var infowindow = new google.maps.InfoWindow();   
    var geocoder = new google.maps.Geocoder;

    var autocomplete = new google.maps.places.Autocomplete(input);			//자동 입력 기능
    autocomplete.bindTo('bounds', map);
        
    var markerImage = new google.maps.Marker({
    	 path: 'M30.6,15.737c0-8.075-6.55-14.6-14.6-14.6c-8.075,0-14.601,6.55-14.601,14.6c0,4.149,1.726,7.875,4.5,10.524c1.8,1.801,4.175,4.301,5.025,5.625c1.75,2.726,5,11.976,5,11.976s3.325-9.25,5.1-11.976c0.825-1.274,3.05-3.6,4.825-5.399C28.774,23.813,30.6,20.012,30.6,15.737z',
         fillColor: "C8C8C8",
         fillOpacity: 0.8,
         strokeColor: "FFFA82",
         strokeWeight: 1,
         anchor: new google.maps.Point(18,34),
         labelOrigin: { x: 16, y: 16 }
    });
     /*marker = new google.maps.Marker({
        icon: markerImage,
    	map: map,
        anchorPoint: new google.maps.Point(0,-29)
    });*/
    
    autocomplete.addListener('place_changed', function() {
        infowindow.close();
        //marker.setVisible(false);
        var place = autocomplete.getPlace();
        
        if (!place.place_id) {
            return;
          }
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
               
        geocoder.geocode({'location':place.geometry.location}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK)  {
				if (results[0]){
					 marker = new google.maps.Marker({
			        	 icon: markerImage,
			             label: {
			                 text: '+1' || '●',
			                 color: "C9C5C9"
			             },
			        	draggable:true,
			            map: map,
			            position: place.geometry.location
			          });
					marker.setMap(map);
                  //var geo_latlng = results[0].geometry.location;                 
                  //root(marker,geo_latlng);
				}
			}else{
				alert('지오코딩 좌표 오류');
			}	
		});
        
        var address = '';
        if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }
        
       // var contentString = '<div><strong id="placename" value = ' + place.name + '>' + place.name + '</strong><br>' + address;
        infowindow.setContent('<div><strong id="placename" value = ' + place.name + '>' + place.name + '</strong><br>' + address);
        infowindow.open(map, this); 
        
        marker.addListener('click', function (event){
        	infowindow.open(this, marker); 
        });
       // popInfoWindow(map,marker,contentString);
        
        
        
       // document.getElementsByClassName('haha').innerHTML = place.name;
       // document.getElementsById('haha').innerHTML = place.name;
      //$(".ui button:first-child").append(place.name);
        
        $(".ui button:nth-child(1)").append(place.name);
        
        //console.log($("#placename").val());
        //console.log(place.name);
        
	});   
    
    
    google.maps.event.addListener(map, 'click', function(mouseEvent) {
    	 infowindow.close();
    	var latlng = mouseEvent.latLng;    
    	
        marker = new google.maps.Marker({
        	 icon: markerImage,
             label: {
                 text: '+1' || '●',
                 color: "C9C5C9"
             },
        	draggable:true,
            map: map,
            position: latlng
          });
        // root(marker,latlng);
        // addMarker(latlng);     //선을 그리기 위해 좌표를 넣는다.
         popInfoWindow(marker, latlng);
    });                                                                                  

	/*function addMarker(latlng) {
		marker = new google.maps.Marker({
			position: latlng,
			map: map
		});
		
	   	markersArray.push(marker);
	   	
	    //지도에 출력된 마커를 클릭했을 경우 이벤트(infoWindow 출력)
	   
	    });
	}*/
}

    function popInfoWindow(marker, latlng) {    	
    	var lat_lng = {lat: latlng.lat(), lng: latlng.lng()};
    	var geocoder = new google.maps.Geocoder;
    	var infowindow = new google.maps.InfoWindow();   

    	geocoder.geocode({'latLng': lat_lng}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK)  {
				if (results[0]){					
					infowindow.setContent(
						"<div><table>" +
							"<tr><td colspan='3'>place.name</td><tr>" +
							"<tr><td colspan='3'>" + results[0].formatted_address + "</td><tr>" +
							"<tr><td id='bar'>selectMember</td><td><input type='submit' value='Pick' onclick='BarFind()'></td>" +
							"<td><input type='button' value='Cancel' onclick='BarCancel(yu)'></td></tr>" +
						"</table></div>");					
					infowindow.open(map,marker);
                 
	    		} else {
	    			alert("No results found");
	    		}
	    	}else{
	    		infowindow.setContent("lat: " + latlng.lat() + " lng: " + latlng.lng());
	    		infowindow.open(map, marker);
	    	}
		});
    	
    	marker.addListener('click', function (event){    		
    		infowindow.open(this, marker); 
        });
	}
    

    
    /*동선그리기
    function root(marker,latlng){
        Coordinates.push(latlng);		   //마커 담기
        MarkersArray.push(marker);         //array에 담은 위도,경도 데이타를 가지고 동선 그리기
        flightPath();
        //alert(MarkersArray);
    }
    

   function flightPath(){
    for (i in travelPathArray){
    	travelPathArray[i].setMap(null);
    }
    var flightPath = new google.maps.Polyline({
    	path: Coordinates,
	    strokeColor: "#FF0000",
	    strokeOpacity: 0.3,
	    strokeWeight: 2
	});
    flightPath.setMap(map);
    travelPathArray.push(flightPath);
    }*/  

	function BarFind(){
	  document.getElementById('bar').innerHTML = "selectMember" + "<br>" + "yu" + "<br>" + "ji";
	}
	
	function BarCancel(obj){	// obj.parentNode 를 이용하여 삭제
		 document.getElementById('bar').removeChild(obj);
	 }
