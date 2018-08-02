var geocorder;
var map;
var address;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {			/*지도생성, 지도를 표시할 div아이디가'map' */
    	center: {lat: 37.566, lng: 126.977},			/* 지도의 중심좌표 */
    	zoom: 10
    });
    var input = document.getElementById('searchInput');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);			//지도or위성

    var autocomplete = new google.maps.places.Autocomplete(input);			//자동 입력 기능
    autocomplete.bindTo('bounds', map);

    var infowindow = new google.maps.InfoWindow();   
    var geocoder = new google.maps.Geocoder;
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
    
    marker.addListener('click', function() {
        infowindow.open(map, marker);
      });
    
    autocomplete.addListener('place_changed', function() {
        infowindow.close();
        marker.setVisible(false);
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
    });   
    
    
    google.maps.event.addListener(map, 'click', function(mouseEvent) {        
        
        // 클릭한 위도, 경도 정보를 가져옵니다 
        //var latlng = mouseEvent.LatLng; 
        
        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(mouseEvent.LatLng);
        marker.setMap(map);
      //  infowindow.setContent('<div><strong>' +mouseEvent.LatLng+'</string>' );
       // infowindow.open(map, marker); 

    });
}
  /*  google.maps.event.addListener(map, 'click', function(mouseEvent) {
    	 marker.setPosition(mouseEvent.latLng);
    	 marker.setMap(map);    
    	
    	 var latlng = mouseEvent.latLng;
    	 infowindow.setContent( '<div>클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ' + '경도는 ' + latlng.getLng() + ' 입니다</div>');
    	 infowindow.open(map, marker);
      });
    
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
