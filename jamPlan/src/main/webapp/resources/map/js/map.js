// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var markersArray = [];
var coordinates= [];
var travelPathArray = [];
var map;
var marker;
var up_lng;
var up_lat
var up_latlng;

function pickList(){
	$('#output').html("");
	
	$.ajax({
		url:'getPickList.map',
		type: 'POST',
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){
			$.each(data, function(index, item){
				//$('#output').val('');
				var output = '';
				output += '<tr>';
				output += '<td>' + item.id + '</td>';
				output += '</tr>'; 
				console.log("output:" + output);
				
				$('#output').append(output);
			});
		},
		error:function(){
			alert("ajax통신실패!!!");
	   }
	});
	event.preventDefault();
}


function onPick(){		
//idarray.push(id);		
//document.getElementById('output').innerHTML = "selectMember" + "<br>" + idarray;
console.log("252582525252");
$.ajax({
	url:'insertMember.map',
	type: 'POST',
	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	dataType : "json",
	data: { 
		"planNo":1,
		"id":"hello",
		"userColor":"black",
		"mapCount":1,
		"location":"seoul"},
	success:function(retVal){ 	
		if(retVal.res == "OK"){
			//$('#output').html(id);						
			pickList();					
		}
		else
		{
			alert("Insert Fail!!!");
		}
	},
	error:function(){
		alert("ajax통신실패!!!");
	}
});
event.preventDefault();
}

function onCancel(){// obj.parentNode 를 이용하여 삭제
//document.getElementById('output').innerHTML="selectMember" + "<br>" + idarray.pop();
$.ajax({
	url: 'deleteMember.map',
	type: 'POST',
	data: {"id":$("#memberid").val()},
	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	dataType : "json",
	success:function(retVal){
		if(retVal.res == "OK"){
			//$('#output').html("");
			pickList();
		}
		else
		{
			alert("Delete Fail!!!");
		}
	},
	error:function(){
		 alert("ajax통신실패!!!");
	}
});
event.preventDefault();
}

function initMap() {
	
	//up_lat = "37.5562989";
	//up_lng = "126.9220863";
	//up_latlng = new google.maps.LatLng(up_lat, up_lng);

    map = new google.maps.Map(document.getElementById('map'), {			/*지도생성, 지도를 표시할 div아이디가'map' */
    	center: {lat: 37.566, lng: 126.977},			/* 지도의 중심좌표 */
    	zoom: 18
    	
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
         fillColor: "ffffff",
         fillOpacity: 0.8,
         strokeColor: "FFFA82",
         strokeWeight: 1,
         anchor: new google.maps.Point(18,34),
         labelOrigin: { x: 16, y: 16 }
    });
       
    google.maps.event.addListener(map, 'click', function(mouseEvent) {
     	var origin = mouseEvent.latLng;
    });
    
    var marker = new google.maps.Marker({
   	 icon: markerImage,
        label: {
            text: '+1' || '●',
            color: "C9C5C9"
        },
   	draggable:true,
       map: map,
     });
    var placesService = new google.maps.places.PlacesService(map);
    var infowindowContent = document.getElementById('infowindow-content');
    var ClickEventHandler = function(map, origin) {
    	 this.origin = origin;
         this.map = map;
         this.placesService = new google.maps.places.PlacesService(map);
         this.infowindow = new google.maps.InfoWindow;
         this.infowindowContent = document.getElementById('infowindow-content');
         this.infowindow.setContent(this.infowindowContent);
         this.map.addListener('click', this.handleClick.bind(this));
         this.marker = new google.maps.Marker({
        	 icon: markerImage,
             label: {
                 text: '+1' || '●',
                 color: "FFFFFF"
             },
        	draggable:true,
            map: map,
          });
    }     
    
    autocomplete.addListener('place_changed', function() {
        infowindow.close();
        marker.setVisible(false);
        
        var place = autocomplete.getPlace();
        
        if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
        }
        
        if (!place.place_id) {
        	return;
        }
        else{
        	 // If the place has a geometry, then present it on a map.
            if (place.geometry.viewport) {
                map.fitBounds(place.geometry.viewport);
            } else {
                map.setCenter(place.geometry.location);
                map.setZoom(10);
            }
            
            geocoder.geocode({'location':place.geometry.location}, function(results, status) {
 			if (status == google.maps.GeocoderStatus.OK)  {
 				if (results[0]){
 					
 				  var address = '';
 		             if (place.address_components) {
 		                 address = [
 		                   (place.address_components[0] && place.address_components[0].short_name || ''),
 		                   (place.address_components[1] && place.address_components[1].short_name || ''),
 		                   (place.address_components[2] && place.address_components[2].short_name || '')
 		                 ].join(' ');
 		             }					
                   //var geo_latlng = results[0].geometry.location;                 
                   //root(marker,geo_latlng);
 				}
 			}
 			else{
 				alert('지오코딩 좌표 오류');
 			}	
        // var contentString = '<div><strong id="placename" value = ' + place.name + '>' + place.name + '</strong><br>' + address;
         infowindow.setContent('<div><strong id="placename" value = ' + place.name + '>' + place.name + '</strong><br>' + address);
 			/*infowindowContent.children['place-icon'].src = place.icon;
            infowindowContent.children['place-name'].textContent = place.name;
            infowindowContent.children['place-address'].textContent = place.formatted_address;*/
 			infowindow.open(map, marker); 
         });
            /*geocoder.geocode({'location':place.geometry.location}, function(place, status) {
               placesService.getDetails({placeId: place.place_id}, function(place, status) {
                if (status === 'OK') {
                 infowindow.setPosition(place.geometry.location);
                 infowindowContent.children['place-icon'].src = place.icon;
                 infowindowContent.children['place-name'].textContent = place.name;
                 infowindowContent.children['place-address'].textContent = place.formatted_address;
                 infowindow.open(map);
                }
               });
            });*/
        }
         marker.setPosition(place.geometry.location);
		 marker.setVisible(true);       
      /*  
        */
       // popInfoWindow(map,marker,contentString);
        
        marker.addListener('click', function(mouseEvent){
        	infowindow.open(mouseEvent.latLng, marker); 
        });
        
       // document.getElementsByClassName('haha').innerHTML = place.name;
       // document.getElementsById('haha').innerHTML = place.name;
      //$(".ui button:first-child").append(place.name);
        
        $(".ui button:nth-child(1)").append(place.name);
        
        //console.log($("#placename").val());
        //console.log(place.name);
        
	});       
   
    google.maps.event.addListener(map, 'click', function(event) {
    	infowindow.close();
    	var latlng = event.latLng;    
    	
        marker = new google.maps.Marker({
        	 icon: markerImage,
             label: {
                 text: '+1' || '●',
                 color: "FFFFFF"
             },
        	draggable:true,
            map: map,
          });
        marker.setPosition(latlng);
        // root(marker,latlng);
        // addMarker(latlng);     //선을 그리기 위해 좌표를 넣는다.
        
        if(event.placeId)
        {
        	ClickEventHandler.prototype.handleClick = function(event) {
        	console.log('You clicked on place:' + event.placeId);
        	event.stop();
            this.getPlaceInformation(event.placeId);
        	}
        }
        else{
            popInfoWindow(marker, latlng);

        }
    });                              
    
    function popInfoWindow(marker, latlng) {  
    	var lat_lng = {lat: latlng.lat(), lng: latlng.lng()};
    	//var id = $('#memberid').val();
//    	var idarray = [];
    	var id = document.getElementById("memberid").value;
    	
    	pickList();
    		
    	geocoder.geocode({'latLng': lat_lng}, function(results, status) {   		
    		
    		if (status == google.maps.GeocoderStatus.OK)  {
    			if (results[0]){					
    				infowindow.setContent(
    					"<table>" +							
    						"<tr><td colspan='3'>" + results[0].formatted_address + "</td></tr>" +
    						"<tr><td>selectMember</td>" + 
    						"<td><input id ='pickBtn' type='button' value='Pick' onclick='onPick()'></td>" +
    						"<td><input id='cancelBtn' type='button' value='Cancel' onclick='onCancel()'></td></tr>" +
    					"</table>" + "<table id='output'><tr><td>test</td></tr></table>" 					
    				);				
    			
    			infowindow.open(map,marker);
    				
        		} else {
        			alert("No results found");
        		}
        	}else{
        		infowindow.setContent("lat: " + latlng.lat() + " lng: " + latlng.lng());
        		infowindow.open(map, marker);
        	}
    	    	
    	marker.addListener('click', function (event){    		
    		popInfoWindow(marker,latlng); 
    	});
    	
        });
    }
    	
	/*function addMarker(latlng) {
		marker = new google.maps.Marker({
			position: latlng,
			map: map
		});
		
	   	markersArray.push(marker);
	   	
	    //지도에 출력된 마커를 클릭했을 경우 이벤트(infoWindow 출력)
	   
	    });
	}*/
    
    ClickEventHandler.prototype.getPlaceInformation = function(placeId) {
        var me = this;
        this.placesService.getDetails({placeId: placeId}, function(place, status) {
          if (status === 'OK') {
            me.infowindow.close();
            me.infowindow.setPosition(place.geometry.location);
            me.infowindowContent.children['place-icon'].src = place.icon;
            me.infowindowContent.children['place-name'].textContent = place.name;
            me.infowindowContent.children['place-address'].textContent = place.formatted_address;
            me.infowindow.open(me.map);
          }
        });
      };
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

