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
var x=1;

$(document).ready(function(){
	//marker.setMap(null);
	
	function getAllPick(){
		$.ajax({
			url:'getAllPickList.mp',
			type: 'GET',
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){ //성공적응답 받아오면 이 함수 자동실행 (data)가 컨트롤러에서 반환된 데이터가 들어있다.  // 변수이름은 바꿀수 있다.
				$.each(data, function(index, item){ // 이 데이터는 리스트니 하나의 데이터(peopleVO객체)가 아이템에 저장 / 인덱스는 고정된 값
					infowindow.close();
					var place = item.location;
								        
					if (place.geometry.viewport) {
			            map.fitBounds(place.geometry.viewport);
			        } else {
			            map.setCenter(place.geometry.location);
			            map.setZoom(15);  // Why 17? Because it looks good.
			        }
					
					marker.setPosition(place.geometry.location);
			        marker.setVisible(true);
			        
					infowindow.close();
					autocompletefun(place);

					var address = '';
				    var infowindow = new google.maps.InfoWindow();
					
					console.log(index);
					$(".ui button:nth-child(" + (index+1) + ")").append(item.location);
				});
			},
			error:function(){ //응답실패시
					alert("ajax통신실패!!!");
			}
		});
	};
});
		
		
//인포윈도우(마커말풍선)에 pick한 멤버 리스트 보여주는 함수
function pickList(){
	alert($('#placename').html());
	console.log($('#placename').html());
	console.log("==picklist()==");
	$('#output').empty();
	console.log("outputempty");
	$.ajax({
		url:'getPickList.mp',
		type: 'GET',
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
				
				console.log("output:" + output);
				
			});
			$('#output').append(output);
			
		},
		error:function(){
			alert("ajax통신실패!!!");
	   }
	});
}

function onPick(){
	alert($('#placename').html());
	$.ajax({
		url:'insertMember.mp',
		type: 'GET',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType :"json",
		data: { 
				"planNo":1,
				"id":$('#memberid').val(),
				"pickCount":0,
				"userColor":"black",
				"location":$('#placename').html()},
		success:function(retVal){ 	
			if(retVal.res == "OK"){	
				$('#pickCount').val(retVal.pickNum);
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
	url: 'deleteMember.mp',
	type: 'GET',
	data:{
		  "planNo":1,
		  "id":$('#memberid').val(),
		  "location": $('#placename').html()},
	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	dataType : "json",
	success:function(retVal){
		if(retVal.res == "OK"){
			$('#pickCount').val(retVal.pickNum);
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
    
    /*x=$('#pickCount').val();*/
    markerImage = new google.maps.Marker({    	
          url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ col + "|13|_|" + x ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
          anchor: new google.maps.Point(15,40),
          labelOrigin: { x: 16, y: 16 }     	 
     });
   
    marker = new google.maps.Marker({
    	icon: markerImage,   
    	map: map
    });
    
//   coordinates.push(init_latlng);        
//	// 마커의 수만큼 반복하여 coordinates에 push된 위치 정보값을 계산 후 
//	// 맵의 zoom level과 center를 맵에 적용
    //Bounds 는 반환된 결과를 완전히 포함 할 수있는 경계 상자를 저장
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
    //var infowindowContent = document.getElementById('infowindow-content');
    //infowindow.setContent(infowindowContent);
    
    var autocomplete = new google.maps.places.Autocomplete(input);			//자동 입력 기능
  
    // Bind the map's bounds (viewport) property to the autocomplete object,
    // so that the autocomplete requests use the current map bounds for the
    // bounds option in the request.
    autocomplete.bindTo('bounds', map);
  
    var clickHandler = new ClickEventHandler(map, marker);   

    
    /*fn*/  // autocomplete.addListener('place_changed', autocompletefn(place));
    autocomplete.addListener('place_changed', function autocompletefn(place){
    	
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace(); 
        if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
        }        
        
        // If the place has a geometry, then present it on a map.  
        //Viewport contains the recommended viewport for displaying the returned result
    	if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(15);  // Why 17? Because it looks good.
        }
    	
    	marker.setPosition(place.geometry.location);
        marker.setVisible(true);  
        
/*fn*/	autoInfo(place);
		
		infowindow.setContent($('#infoContent').html());
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

function autoInfo(place){
    var address = '';
   // this.infowindow = new google.maps.InfoWindow();
    if (place.address_components) {
    	  address = [
          (place.address_components[0] && place.address_components[0].short_name || ''),
          (place.address_components[1] && place.address_components[1].short_name || ''),
          (place.address_components[2] && place.address_components[2].short_name || '')
        ].join(' ');
      }
    
    $('#placename').html(place.name);
    $('#address').html(address);
     	
}

    var ClickEventHandler = function(map, marker) {
    	 //this.init_latlng = init_latlng;
         this.map = map;
         this.marker = marker;
         this.placesService = new google.maps.places.PlacesService(map);
         this.infoWindow = new google.maps.InfoWindow;
         //this.infowindow.setContent(this.infowindowContent);
         
         //Listen for clicks on the map.
         this.map.addListener('click', this.handleClick.bind(this));
    }     
    
    ClickEventHandler.prototype.handleClick = function(event) {    	
    	// If the event has a placeId, use it.
        if (event.placeId) {
        	console.log('You clicked on place:' + event.placeId);

        	event.stop();		//기본 인포창 안뜨게 하기
        	this.getPlaceInformation(event.placeId);
        }
        else{
        	console.log('You clicked on: ' + event.latLng);	       
        }
    };
      
    ClickEventHandler.prototype.getPlaceInformation = function(placeId) {
    	 var me = this;
    	 me.placesService.getDetails({placeId: placeId}, function(place, status) {
	    	if (status === 'OK') {
	    		//this.infoWindow = new google.maps.InfoWindow;//test
	    		me.infoWindow.close();
	    		me.marker.setPosition(place.geometry.location);
	    		me.infoWindow.setPosition(place.geometry.location);
	    		
	    		//autoInfo(place);
	    		 var address = '';	    		  
	    		    if (place.address_components) {
	    		    	  address = [
	    		          (place.address_components[0] && place.address_components[0].short_name || ''),
	    		          (place.address_components[1] && place.address_components[1].short_name || ''),
	    		          (place.address_components[2] && place.address_components[2].short_name || '')
	    		        ].join(' ');
	    		      }
	    		    
	    		$('#placename').html(place.name);
	    		$('#address').html(address);
	    		me.infoWindow.setContent($('#infoContent').html());
	    		me.infoWindow.open(me.map, me.marker);
	        }
	    	
	    	marker.addListener('mouseover', function(mouseEvent,marker){
	        	console.log("autocomplete.addListene ");
	        	//pickList();
	        	me.infoWindow.open(mouseEvent.latLng, me.marker);
	        });
	    	
	    	//mouseout발생하고 몇초후에 인포닫히게 해야해
	        marker.addListener('mouseout', function(mouseEvent){
	        	console.log("autocomplete.addListene ");
	        	me.infoWindow.close(); 
	        });
	    	
    	});
    };
    
	$('#reset').click(function(){
		alert("resetButton");
    	infowindow.close();
    });
    /*
     $('#confirm').click(function(){
    	
    });
    */
    
    
    
   
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
