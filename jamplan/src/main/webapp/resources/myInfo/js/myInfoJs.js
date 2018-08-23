/**@author Taehyuk, Kim


 * 
 */

//문서 전체 출력 후
function autocomplete(inp, arr) {
  /*the autocomplete function takes two arguments,
  the text field element and an array of possible autocompleted values:*/
  var currentFocus;
  /*execute a function when someone writes in the text field:*/
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      /*close any already open lists of autocompleted values*/
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      /*create a DIV element that will contain the items (values):*/
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      /*append the DIV element as a child of the autocomplete container:*/
      this.parentNode.appendChild(a);
      /*for each item in the array...*/
      for (i = 0; i < arr.length; i++) {
        /*check if the item starts with the same letters as the text field value:*/
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          /*create a DIV element for each matching element:*/
          b = document.createElement("DIV");
          /*make the matching letters bold:*/
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          /*insert a input field that will hold the current array item's value:*/
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          /*execute a function when someone clicks on the item value (DIV element):*/
              b.addEventListener("click", function(e) {
              /*insert the value for the autocomplete text field:*/
              inp.value = this.getElementsByTagName("input")[0].value;
              /*close the list of autocompleted values,
              (or any other open lists of autocompleted values:*/
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  
  /*execute a function presses a key on the keyboard:*/
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        /*If the arrow DOWN key is pressed,
        increase the currentFocus variable:*/
        currentFocus++;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 38) { //up
        /*If the arrow UP key is pressed,
        decrease the currentFocus variable:*/
        currentFocus--;
        /*and and make the current item more visible:*/
        addActive(x);
      } else if (e.keyCode == 13) {
        /*If the ENTER key is pressed, prevent the form from being submitted,*/
        e.preventDefault();
        if (currentFocus > -1) {
          /*and simulate a click on the "active" item:*/
          if (x) x[currentFocus].click();
        }
      }
  });
  
  function addActive(x) {
    /*active클래스만 고른다.*/
    if (!x) return false;
    /*active 클래스 지운다.*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*"autocomplete-active"클래스 더하는 부분*/
    x[currentFocus].classList.add("autocomplete-active");
  }
  
  function removeActive(x) {
    /*active 상태 없애는 부분*/
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  
  function closeAllLists(elmnt) {
    /*하나 선택되면 창 닫는다.*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
      x[i].parentNode.removeChild(x[i]);
    }
  }
}
  
/* 다른 영역을 클릭하면 리스트가 닫힌다. */
document.addEventListener("click", function (e) {
    closeAllLists(e.target);
});
}

var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla",
				"Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia",
				"Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados",
				"Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia",
				"Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands",
				"Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon",
				"Canada","Cape Verde","Cayman Islands","Central Arfrican Republic",
				"Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica",
				"Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic",
				"Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt",
				"El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
				"Falkland Islands","Faroe Islands","Fiji","Finland","France",
				"French Polynesia","French West Indies","Gabon","Gambia","Georgia",
				"Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam",
				"Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti",
				"Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran",
				"Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan",
				"Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait",
				"Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya",
				"Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar",
				"Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands",
				"Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco",
				"Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar",
				"Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles",
				"New Caledonia","New Zealand","Nicaragua","Niger","Nigeria",
				"North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama",
				"Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal",
				"Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda",
				"Saint Pierre &amp; Miquelon","Samoa","San Marino","Sao Tome and Principe",
				"Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone",
				"Singapore","Slovakia","Slovenia","Solomon Islands","Somalia",
				"South Africa","South Korea","South Sudan","Spain","Sri Lanka",
				"St Kitts &amp; Nevis","St Lucia","St Vincent","Sudan","Suriname",
				"Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan",
				"Tanzania","Thailand","Timor L'Este","Togo","Tonga",
				"Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan",
				"Turks &amp; Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates",
				"United Kingdom","United States of America","Uruguay","Uzbekistan",
				"Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)",
				"Yemen","Zambia","Zimbabwe"];


// 프로필 사진을 등록하기 전에 올린 사진 미리보기 기능
function readURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function (e) {
			$('#imagePreview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$(document).ready(function() {
	
	$(".tab-pane.container").click(function(){
		console.log($(this).attr("value"));
		var link = $(this).attr("value");
		$.ajax({
			url : link,
			type : "post",
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "json",
			success:function(str){
				$(".content").load(str.link);
				console.log('로드 후')
			},	
			error:function(){
				alert("페이지 이동 ajax실패")
			}
		});
	})
	
	$("#imageUpload").on('click', function() {
		$.ajax({
			url: '/jamplan/imageUpload.mi',
			dataType: text,
			success: function(result) {
				if(result == '1') {
					alert('프로필 사진을 업로드했습니다.');
				}else {
					alert('프로필 사진 등록을 실패했습니다.');
				}
			},
			error: function () {
				console.log('프로필 사진 등록 중에 에러가 발생했습니다.');
			}
		});
	})
	
	autocomplete(document.getElementById("myNation"), countries);
	
	// 이미지를 찾으면 readURL함수를 통해 프리뷰 이미지를 생성한다.
	$('#searchImage').change(function () {
		readURL(this);
	})

});



