// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var markersArray = [];
var coordinates= [];
var travelPathArray = [];
var map;
var markerImage;
var marker;
var lng;
var lat;
var init_latlng;
var col="FFFF42";
var x;

$(document).ready(function(){
	$.ajax({
		url:'getAllPickList.map',
		type: 'POST',
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){ //성공적응답 받아오면 이 함수 자동실행 (data)가 컨트롤러에서 반환된 데이터가 들어있다.  // 변수이름은 바꿀수 있다.
			$.each(data, function(index, item){ // 이 데이터는 리스트니 하나의 데이터(peopleVO객체)가 아이템에 저장 / 인덱스는 고정된 값
				var output = '';
		
			});
		}
	});	
});
		
		
//인포윈도우에 pick한 멤버 리스트 보여주는 함수
function pickList(){
	console.log("==picklist()==");
	$('#output').empty();
	console.log("outputempty");
	$.ajax({
		url:'getPickList.map',
		type: 'POST',
		dataType : "json",
		data:{"location":$('#placename').html()},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){
			console.log("data: " + Object.values(data));
			var output = '';
			$.each(data, function(index, item){
				output += '<tr>';
				output += '<td>' + item.id + '</td>';
				output += '</tr>'; 
				
				$(".ui button:nth-child(1)").append(place.name);
				console.log("output:" + output);			
			});
			$('#output').append(output);
			//x = str.pickCount;
			
		},
		error:function(){
			alert("ajax통신실패!!!");
	   }
	});
}

function onPick(){		
	$.ajax({
		url:'insertMember.map',
		type: 'POST',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : "json",
		data: { 
				"planNo":1,
				"id":$('#memberid').val(),
				"userColor":"black",
				"location": $('#placename').html()},
		success:function(retVal){ 	
			if(retVal.res == "OK"){	
				
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
}

function onCancel(){
$.ajax({
	url: 'deleteMember.map',
	type: 'POST',
	data:{"id":$('#memberid').val(),
		  "location": $('#placename').html()},
	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	dataType : "json",
	success:function(retVal){
		if(retVal.res == "OK"){
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
}

function initMap() {
	init_latlng = {lat: -33.871, lng: 151.197};
	
	//init_latlng = new google.maps.LatLng(lat, lng);
    map = new google.maps.Map(document.getElementById('map'), {		
    	center: init_latlng,		
    	zoom: 13
    });
    
    markerImage = new google.maps.Marker({    	
     	// path: 'M30.6,15.737c0-8.075-6.55-14.6-14.6-14.6c-8.075,0-14.601,6.55-14.601,14.6c0,4.149,1.726,7.875,4.5,10.524c1.8,1.801,4.175,4.301,5.025,5.625c1.75,2.726,5,11.976,5,11.976s3.325-9.25,5.1-11.976c0.825-1.274,3.05-3.6,4.825-5.399C28.774,23.813,30.6,20.012,30.6,15.737z',
          url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ col + "|13|_|" + x ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
          anchor: new google.maps.Point(15,40),
          labelOrigin: { x: 16, y: 16 }     	 
     });
   
    marker = new google.maps.Marker({
      	 icon: markerImage,         
      	 draggable:true,
          map: map,
    });
    
//   coordinates.push(init_latlng);        
//	// 마커의 수만큼 반복하여 coordinates에 push된 위치 정보값을 계산 후 
//	// 맵의 zoom level과 center를 맵에 적용
//   bounds = new google.maps.LatLngBounds();
//    
//   for (var i=0; i < coordinates.length ; i++) {
//    	bounds.extend(coordinates[i]);
//	}
//	map.fitBounds(bounds);
//	
	//addMarker(up_latlng);
	
 	var input = document.getElementById('searchInput');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);			//지도or위성
  
    var geocoder = new google.maps.Geocoder;   
    var infowindow = new google.maps.InfoWindow();
    var infowindowContent = document.getElementById('infowindow-content');
    infowindow.setContent(infowindowContent);
    
    var autocomplete = new google.maps.places.Autocomplete(input);			//자동 입력 기능
  
    // Bind the map's bounds (viewport) property to the autocomplete object,
    // so that the autocomplete requests use the current map bounds for the
    // bounds option in the request.
    autocomplete.bindTo('bounds', map);
  
    var clickHandler = new ClickEventHandler(map, init_latlng);   

    
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
            map.setZoom(15);  // Why 17? Because it looks good.
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
	     
	     infowindow.setContent(
	     "<table>"+						
	     "<tr><strong id='placename' value =" + place.name + ">" + place.name +
	     "</tr><td>" + address + "</td>" +
	     "<tr>"+
	     	"<td>selectMember</td>" +
	     	"<td><input id ='pickBtn' type='button' value='Pick' onclick='onPick()'></td>"+
	     	"<td><input id='cancelBtn' type='button' value='Cancel' onclick='onCancel()'></td></tr>"+
	     "</table>" + "<table id='output'></table>");
	     
	    pickList(); 
	    infowindow.open(map, marker); 	
	    
		
			
        marker.addListener('mouseover', function(mouseEvent){
        	console.log("autocomplete.addListene ");
        	//pickList();
        	infowindow.open(mouseEvent.latLng, marker); 
        	
        });
        marker.addListener('mouseout', function(mouseEvent){
        	console.log("autocomplete.addListene ");
        	infowindow.close(); 
        });        
    });
}    

    //var placesService = new google.maps.places.PlacesService(map);
    var ClickEventHandler = function(map, init_latlng, marker) {
    	 this.init_latlng = init_latlng;
         this.map = map;
         this.marker = marker;
         this.placesService = new google.maps.places.PlacesService(map);
         this.infowindow = new google.maps.InfoWindow;
         //this.infowindow.setContent(this.infowindowContent);
         
         //Listen for clicks on the map.
         this.map.addListener('click', this.handleClick.bind(this));
    }     
    
    ClickEventHandler.prototype.handleClick = function(event) {    	
    	// If the event has a placeId, use it.
        if (event.placeId) {
        	console.log('You clicked on place:' + event.placeId);

        	// Calling e.stop() on the event prevents the default info window from
        	// showing.
        	// If you call stop here when there is no placeId you will prevent some
        	// other map click event handlers from receiving the event.
        	event.stop();
        	this.getPlaceInformation(event.placeId);
        }
        else{
        	console.log('You clicked on: ' + event.latLng);       
        }
    };
      
    ClickEventHandler.prototype.getPlaceInformation = function(placeId) {
    	var me = this;
    	this.placesService.getDetails({placeId: placeId}, function(place, status) {
	    	if (status === 'OK') {
	    		me.infowindow.close();
	    		marker.setPosition(place.geometry.location);
	    		me.infowindow.setPosition(place.geometry.location);
	    		me.infowindow.setContent(			//테이블길이정해야함
	    			     "<table>"+						
	    			     "<tr><strong id='placename' value =" + place.name + ">" + place.name +
	    			     "</tr><td>" + place.formatted_address + "</td>" +
	    			     "<tr>"+
	    			     	"<td>selectMember</td>" +
	    			     	"<td><input id ='pickBtn' type='button' value='Pick' onclick='onPick()'></td>"+
	    			     	"<td><input id='cancelBtn' type='button' value='Cancel' onclick='onCancel()'></td></tr>"+
	    			     "</table>" + "<table id='output'></table>");	    		
	    		pickList();
	    		me.infowindow.open(me.map);
	        }
	    	
	    	marker.addListener('mouseover', function(mouseEvent){
	        	console.log("autocomplete.addListene ");
	        	//pickList();
	        	me.infowindow.open(mouseEvent.latLng, marker);
	        });
	        marker.addListener('mouseout', function(mouseEvent){
	        	console.log("autocomplete.addListene ");
	        	me.infowindow.close(); 
	        });
    	});
    };
    /* google.maps.event.addListener(map, 'click', function(mouseEvent) {
 	var origin = mouseEvent.latLng;
});*/
          
 /*  
    google.maps.event.addListener(map, 'click', function(event) {
    	infowindow.close();
    	var latlng = event.latLng;    
    	var pickCount = $('#pickCount').val();
    	
        marker = new google.maps.Marker({
        	 icon: markerImage,
             label: {
                 text: '+' || pickCount || '●',
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
    });                        */      
    
    /*function popInfoWindow(marker, latlng) { 
    	console.log("popInfoWindow");

    	var lat_lng = {lat: latlng.lat(), lng: latlng.lng()};

    	console.log("marker: " + marker);
    	geocoder.geocode({'latLng': lat_lng}, function(results, status) {   		
    		
    		if (status == google.maps.GeocoderStatus.OK)  {
    			if (results[0]){	
    				
    				infowindow.setContent(
    					"<table>" +							
    						"<tr><td colspan='3' id = 'address'>" + results[0].formatted_address + "</td></tr>" +
    						"<tr><td>selectMember</td>" + 
    						"<td><input id ='pickBtn' type='button' value='Pick' onclick='onPick()'></td>" +
    						"<td><input id='cancelBtn' type='button' value='Cancel' onclick='onCancel()'></td></tr>" +
    					"</table>" + "<table id='output'></table>" 					
    				);		
    			
	    			pickList();	
	    			infowindow.open(map,marker);    			
    			}
    			
	    		marker.addListener('click', function (event){   
	    			console.log("popInfoWindow maker.addListner");
	    			popInfoWindow(marker,latlng); 
	        		
	        	});
    		}
    	});
    }
*/
    
	/*function addMarker(latlng) {
		marker = new google.maps.Marker({
			position: latlng,
			map: map
		});
		
	   	markersArray.push(marker);
	   	
	    //지도에 출력된 마커를 클릭했을 경우 이벤트(infoWindow 출력)
	   
	    });
	}*/
  
  
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
