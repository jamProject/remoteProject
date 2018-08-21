// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var markersArray = [];
var infoArray = [];
var travelPathArray = [];
var map;
var markerImage;
var marker;
var lng;
var lat;
var init_latlng;
var col="FFFF42";
var z="0";
var placeName;
var address;
var newPickCount;

$(document).ready(function(){
	
	function getAllPick(){		
		markersArray = []; 
		infoArray = [];
		
		$.ajax({
			url:'getAllPickList.mp',
			type: 'POST',
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){ //성공적응답 받아오면 이 함수 자동실행 (data)가 컨트롤러에서 반환된 데이터가 들어있다.  // 변수이름은 바꿀수 있다.				
				for(var i=0;i<data.length; i++){
					newPickCount = data[i].pickCount;
					marker = new google.maps.Marker({
				    	icon: {
				    		url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ col + "|13|_|" + newPickCount ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
				            anchor: new google.maps.Point(13,58),
				            labelOrigin: { x: 16, y: 16 }
				    	},
				    	position:{  
				        	  lat : data[i].lat, 
				        	  lng : data[i].lng
				        },
				        map: map
				    }); 
					markersArray.push(marker);
					
					/*var init_latlng = {lat : data[i].lat, lng : data[i].lng};
					var coordinates=[];	
					coordinates.push(init_latlng);        
					// 마커의 수만큼 반복하여 coordinates에 push된 위치 정보값을 계산 후 
					// 맵의 zoom level과 center를 맵에 적용
				    //Bounds 는 반환된 결과를 완전히 포함 할 수있는 경계 상자를 저장
					bounds = new google.maps.LatLngBounds();
				    
				    for (var i=0; i < coordinates.length ; i++) {
				    	bounds.extend(coordinates[i]);
					}
					map.fitBounds(bounds);*/
					
					var infowindow = new google.maps.InfoWindow();
						
					$('#placeName').html(data[i].placeName);
					$('#address').html(data[i].address);
					pickList(data[i].placeName);
				
					infowindow.setContent($('#infoContent').html());				    
					$(".ui button:nth-child(" + (i+1) + ")").text(data[i].placeName);
					/*$(".ui button").click(function(){
						map.set()
					})*/
				 
				google.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
				//google.maps.event.addListener(marker, 'dbclick', makeOutListener(infowindow));
				}
				
				function makeOverListener(map, marker, infowindow){
					return function(){						
						infowindow.open(map,marker);
					};
				}
				
				function makeOutListener(infowindow){
					return function(){
						infowindow.close();
					};
				}
				
			},
			error:function(){ //응답실패시
					alert("ajax통신실패!!!");
			}
		});
	}

	$(document).on("click","#pickBtn", function(){
	//function onPick(){
		alert("clickBtnFN");
		$.ajax({
			url:'insertMember.mp',
			type: 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType :"json",
			data:{ 
					"planNo":1,
					"id":$('#memberid').val(),
					"pickCount":0,
					"userColor":"black",
					"placeName":$('#placeName').html(),
					"lat": $('#lat').val(),
					"lng": $('#lng').val(),
					"address":$('#address').html()
				},
			success:function(retVal){ 	
				if(retVal.res == "OK"){	
					$('#pickCount').val(retVal.pickNum);
					getAllPick();
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
	});
	
	$(document).on("click","#cancelBtn", function(){
	//function onCancel(){
		$.ajax({
			url: 'deleteMember.mp',
			type: 'POST',
			data:{
				  "planNo":1,
				  "id":$('#memberid').val(),
				  "placeName": $('#placeName').html()},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : "json",
			success:function(retVal){
				if(retVal.res == "OK"){				
					getAllPick();
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
	});
	
	getAllPick();
});

//인포윈도우(마커말풍선)에 pick한 멤버 리스트 보여주는 함수
function pickList(placeName){
	//placeName = $('#placeName').html();
	//alert("placeName=" + placeName);
	//console.log("==picklist()==");
	var output = '';
	$.ajax({
		url:'getPickList.mp',
		type: 'POST',
		dataType : "json",
		data:{"placeName":placeName},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		async:false,		//false: 동기->비동기적 일처리 방식으로 처리해라(이걸안하면 selectMember가 제대로 출력안됨)
		success:function(data){
			$('#output').empty();
			
			$.each(data, function(index, item){
				//alert("item.id=" + item.id);
				//output += item.id;

				//alert("item.id=" + item.id);
				output += '<tr>';
				output += '<td>' + item.id + '</td>';
				output += '</tr>'; 

			});
			//$('#output').html(output);
			$('#output').append(output);
			//return output;
			//alert($('#output').val());
			
		},
		error:function(){
			alert("ajax통신실패!!!");
	   }
	});
}


function initMap() {
	
	//init_latlng = new google.maps.LatLng(lat, lng);
    map = new google.maps.Map(document.getElementById('map'), {		
    	center: {lat: -33.871, lng: 151.197},		
    	zoom: 13
    });
    
    
    /* markerImage = new google.maps.Marker({    	
          url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ col + "|13|_|" + z ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
          anchor: new google.maps.Point(13,58),
          labelOrigin: { x: 16, y: 16 }     	 
     });*/
    
    marker = new google.maps.Marker({
    	icon: {
    		url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ col + "|13|_|" + z ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
            anchor: new google.maps.Point(13,58),
            labelOrigin: { x: 16, y: 16 }
    	},   
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
    //var mouseHandler = new MouseEventHandler(map, marker);   

    
    /*fn*/  //다른 회원이 pick하고 난후 검색했을 때는 인포클로스,마커안보이게하고 마커로 이동만하게 해야해
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
        
        autoInfo(place);		//autoInfo(place,info,marker)
		
		infowindow.setContent($('#infoContent').html());
	    infowindow.open(map, marker);

        marker.addListener('mouseover', function(mouseEvent){
        	console.log("autocomplete.addListene22 ");
        	//pickList();
        	infowindow.open(mouseEvent.latLng, marker); 
        	
        });
        marker.addListener('click', function(mouseEvent){	
        	console.log("autocomplete.addListene33 ");
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
    
    placeName = $('#placeName').html(place.name);
    address = $('#address').html(address);
}

var ClickEventHandler = function(map, marker) {
	 //this.init_latlng = init_latlng;
     this.map = map;
     this.marker = marker;
     this.placesService = new google.maps.places.PlacesService(map);
     this.infoWindow = new google.maps.InfoWindow;
     //this.infowindow.setContent(this.infowindowContent);
     
     //Listen for clicks on the map.
     this.map.addListener('click', this.handleClick.bind(this));	//.bind() : event가 발생하면 실행될 함수를 지정한다. (이미 생성된 DOM에 대하여)
}     

ClickEventHandler.prototype.handleClick = function(event) {    	
	// If the event has a placeId, use it.
    if (event.placeId) {
    	console.log('You clicked on place:' + event.placeId);

    	event.stop();		//기본 인포창 안뜨게 하기
    	
    	$('#placeId').val(event.placeId);
    	$('#lat').val(event.latLng.lat());
    	$('#lng').val(event.latLng.lng());
    	
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
    		
    		autoInfo(place);
    		
    		me.infoWindow.setContent($('#infoContent').html());
    		me.infoWindow.open(me.map, me.marker);
        }
    	
    	//getAllPick();
    	/*me.marker.addListener('mouseover',function(e){
        	console.log("autocomplete.addListene44 ");
        	me.infoWindow.open(e.latLng, me.marker);
        });
    	
    	me.marker.addListener('click', function(){
        	console.log("autocomplete.addListene55 ");
        	me.infoWindow.close(); 	        	 
        });	    	*/
	});
};

 
$('#reset').click(function(){
	markersArray.setMap(null);
    markersArray = [];
	alert("resetButton");
});


/*
 $('#confirm').click(function(){
	
});
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
