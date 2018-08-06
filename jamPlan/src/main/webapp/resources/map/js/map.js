var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var labelIndex = 0;

// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var MarkersArray = [];
var Coordinates= [];
var travelPathArray = [];
var map;
var marker;
var markerImage;
      
function initMap() {
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
    var input = document.getElementById('searchInput');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);			//지도or위성

    var infowindow = new google.maps.InfoWindow();   
    var geocoder = new google.maps.Geocoder;

    var autocomplete = new google.maps.places.Autocomplete(input);			//자동 입력 기능
    autocomplete.bindTo('bounds', map);
        
    markerImage = new google.maps.MarkerImage(
        "http://simpleicon.com/wp-content/uploads/map-marker-13.png",
        new google.maps.Size(35,35),		//size
        new google.maps.Point(0, 0),	//origin
        new google.maps.Point(17,34),	//anchor
        new google.maps.Size(35, 35)		//scale
    );
    
     marker = new google.maps.Marker({
        icon: markerImage,
    	map: map,
    	
        anchorPoint: new google.maps.Point(0,-29)
    });
    
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
					
					// Add the marker at the clicked location, and add the next-available label
					// from the array of alphabetical characters.
					marker = new google.maps.Marker({
			        	icon: markerImage,
			            label: labels[labelIndex++ % labels.length],
			            map: map,
			            position: place.geometry.location
			          });
					
                  var geo_latlng = results[0].geometry.location;                 
                  root(marker,geo_latlng);
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
        
        infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        infowindow.open(map, marker); 
        
    });   
   
    google.maps.event.addListener(map, 'click', function(mouseEvent) {
    	var latlng = mouseEvent.latLng;   
        //var service = new google.maps.places.PlacesService(map);
        
         infowindow.close();
         marker = new google.maps.Marker({
        	icon: markerImage,
        	draggable:true,
            label: labels[labelIndex++ % labels.length],
            map: map,
            position: latlng
          });
         
         root(marker,latlng);
     	 attachMessage(marker, latlng);     //선을 그리기 위해 좌표를 넣는다.

    });                                                                                         
    
    function root(marker,latlng){
        Coordinates.push(latlng);		   //마커 담기
        MarkersArray.push(marker);         //array에 담은 위도,경도 데이타를 가지고 동선 그리기
        flightPath();
        //alert(MarkersArray);
    }
        
    function attachMessage(marker, latlng) {    	
    	var lat_lng = {lat: latlng.lat(), lng: latlng.lng()};
    	 
		geocoder.geocode({'latLng': lat_lng}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK)  {
				if (results[0]){
                  /*infowindow.setContent(place.name + '<br>' + results[0].formatted_address  + 
                		  '<div>' + '<button>pick</button>' + '</div>');
					infowindow.setContent("<div>" +  results[0].formatted_address +
						  "<br><input type='submit' value='Pick' onclick='BarFind()'><div id='bar'></div></div>");*/
					
					infowindow.setContent(
						"<div><table>" +
							"<tr><td colspan='3'>place.name</td><tr>" +
							"<tr><td colspan='3'>" + results[0].formatted_address + "</td><tr>" +
							"<tr><td id='bar'>selectMember</td><td><input type='submit' value='Pick' onclick='BarFind()'></td>" +
							"<td><input type='button' value='Cancel' onclick='BarCancel(yu)'></td></tr>" +
						"</table></div>");					
					infowindow.open(map,marker);
                 
                     marker.addListener('click', function() {
                    	 infowindow.open(map,this);
					});
	    		} else {
	    			alert("No results found");
	    		}
	    	}else{
	    		infowindow.setContent("lat: " + latlng.lat() + " lng: " + latlng.lng());
	    		infowindow.open(map, marker);
	    	}
		});
    }
    
  //동선그리기
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
    }
}   

	function BarFind(){
	  document.getElementById('bar').innerHTML = "selectMember" + "<br>" + "yu" + "<br>" + "ji";
	}
	
	function BarCancel(obj){	// obj.parentNode 를 이용하여 삭제
		 document.getElementById('bar').removeChild(obj);
	 }
	
  /* 
    var service = new google.maps.places.PlacesService(map);
    service.getDetails({
      placeId: 'ChIJN1t_tDeuEmsRUsoyG83frY4'
    }, function(place, status) {
      if (status === google.maps.places.PlacesServiceStatus.OK) {
         marker = new google.maps.Marker({
          map: map,
          position: place.geometry.location
        });
        
    google.maps.event.addListener(marker, 'click', function() {
          infowindow.setContent('<div><strong>' + place.name + '</strong><br>' +
            'Place ID: ' + place.place_id + '<br>' +
            place.formatted_address + '</div>');
          infowindow.open(map, marker);
        });
      }
    });*/
