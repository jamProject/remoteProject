/* @author wookim

 */
dt = new Date();
nowMonth = dt.getMonth();
nowDay = dt.getDate();
nowYear = dt.getFullYear();


$(document).ready(function(){
	
	calcCalendar(nowYear, nowMonth + 1);
	selectYearList(nowYear);
	selectMonthList(nowMonth + 1);
	printYearMonth(nowYear, nowMonth + 1);
	hiddenAllBut();
	clickBut();
	
	var year = String(nowYear).substring(2,4);
	printSelectDateAjax(Number(year),nowMonth+1);
	
	/*if($("#save").length && ("#save").val()=="저장하기"){
		$("#save").click(function(){
			$("#save").val("취소");
		})
	}*/
	if($("#save").length && $("#save").val()=="저장하기"){
		$("#save").click(function(){
			$("#save").val("취소");
		})		
	}
	
	if($("#save").length && $("#save").val()=="취소"){
		$("#save").click(function(){
			$("#save").val("저장하기");
		})
	}
	/*해당 문서 로딩 이후
	 * ajax를 이용하여 mananagePlanController에 
	 * loadCalendar.manageplan로  맴핑되는 
	 * 메소드가 실행되고 해당 메소드는
	 * db에 접속하여 해당 일정에 날짜를 선택한 데이터를 가져와서 뿌려준다.
	 * 
	 * */
});


function printSelectDateAjax(year, month){
	$(".countDate").html("");	
	var selectDateList = new Array();

	$.ajax({
		url:'loadCalendar.mp',
		type:'POST',
		dataType:"json",
		contentType:'application/x-www-form-urlencoded; charest=utf-8',
		success:function(str){
			$.each(str, function(index, item){
				var yearSub = Number(item.selectDate.substring(0,2));
				console.log("yearSub : "+yearSub);
				var monthSub= Number(item.selectDate.substring(3,5));
				console.log("monthSub : "+monthSub);
				var day;
				var output =""
				
				console.log("now year : " +year);
				console.log("now month : " + month);
				if(yearSub==year && monthSub == Number(month)){ 
					day = Number(item.selectDate.substring(6,8));
					console.log(day);		
					output += '<div class = "countDate">';	
					output += '+' + String(item.dateCount);
					output += '</div>';
					$('#dateTd'+ day).append(output);
				}
			});
		},error:function(){
			alert("통신실패");
		}
	});
}


// 마우스가 밖으로 나갔을 때 좋아요 버튼 숨기기 이벤트
function hiddenBut(day) {

	$("#dateButton" + day).css('visibility', 'hidden');
}

// 모든 버튼 숨기기 이벤트
function hiddenAllBut() {

	$(".goodBut").css('visibility', 'hidden');
}

// 마우스 안으로 들어오묜 좋아요 버튼 보이기 이벤트
function showBut(day) {
	$("#dateButton" + day).css('visibility', 'visible');
}

// 좋아요 버튼 눌렀을 때 년도 저장 하기 이벤트
function clickBut(){
	$(".goodBut").on("click", function(){
		var yearSelect = $("#listYear").val();
		var monthSelect = $("#listMonth").val();
		var daySelect = $(this).val();
		var date;
		var year;
		
		if(yearSelect<2100){
			date = yearSelect.substring(2,4);
			year = date;
		}else{
			date = yearSelect.substring(1,4);
			year = date;
		}
		if(monthSelect < 10){
			date = date + '/0' + String(monthSelect);
		}else{
			date = date +'/'+ String(monthSelect);
		}
		if(daySelect < 10){
			date = date +'/0'+ String(daySelect);
		}else{
			date = date +'/'+ String(daySelect);
		}	

		console.log("date : "+date);

		$.ajax({
			url:"selectCalendar.mp",
			type:"POST",
			contentType:'application/x-www-form-urlencoded; charsert=utf-8',
			dataType:"json",
			data : {"selectDate" : date},
			success:function(map){
				//alert(map.res);
				printSelectDateAjax(Number(year),Number(monthSelect));
			},
			erorr:function(){
				alert("출력실패");
			}
		})
		
	});
}

// 현재 날짜와 윤달, 해당 월의 일수 계산
function Calendar() {
	// 윤달 판단
	this.calYun = function(year) {
		var yun = false;
		if (year % 4 == 0 && year % 400)

			if (year % 4 == 0) {
				yun = true;
			} else if (year % 100 == 0) {
				if (year % 400 == 0) {
					yun = true;
				}
				yun = false;
			} else {
				yun = false;
			}
		return yun;
	}
	// 해당 월의 일수
	this.totMonthDay = function(mon, yun) {
		var days = 0;

		if (mon < 8 && mon % 2 == 1) {
			days = 31;
		} else if (mon == 2 && yun == true) {
			console.log("day : " + days + " yun : " + yun);
			days = 29;
		} else if (mon == 2 && yun == false) {
			console.log("day : " + days + " yun : " + yun);
			days = 28;
		} else if (mon > 7 && mon % 2 == 0) {
			days = 31;
		} else {
			days = 30;
		}
		return days;
	}
}

// 달력생성 및 비어있는 칸 생성
function calcCalendar(year, month) {
	var calendar = new Calendar();
	var calendarDiv = document.getElementById('calendar');
	var html = '<table id="cal"><tr id = "calendarHead"><th id = "sun">일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th id = "sat">토</th></tr><tr>';
	// 계산하고자 하는 연도와 월을 날짜 객체에 지정
	dt.setYear(Number(year));
	dt.setMonth(Number(month) - 1);
	var totMonth = calendar.totMonthDay(month, calendar.calYun(year));
	// 날짜 표시 반복문
	for (var day = 1; day <= totMonth; day++) {
		dt.setDate(day);

		// 달력 1주차 빈칸생성 로직
		if (day == 1) {
			for (var num = 0; num < dt.getDay(); num++) {
				html += '<td></td>';
			}
		}
		// 일요일이 아니라면 날짜를 표시하고 일요일이라면 날짜를 표시한 후 줄바꿈
		if (dt.getDay() != 6) {
			html += '<td class = "date" id = "dateTd'+String(day)+'" value = ' + String(day)+ ' onmouseover = "showBut(' + String(day)+ ')" onmouseout = "hiddenBut(' + String(day) + ')">'
					+ String(day) 
					+ '<button class= "goodBut" id = "dateButton'+ String(day) + '" value =' + String(day)+ '>' + "좋아요" 
					+ '</button>' 
					+ '</td>';
		} else {
			html += '<td class = "date" id = "dateTd'+String(day)+'" value = ' + String(day)+ ' onmouseover = "showBut(' + String(day)+ ')" onmouseout = "hiddenBut(' + String(day) + ')">'
					+ String(day) 
					+ '<button class= "goodBut" id = "dateButton'+ String(day) + '"  value =' + String(day)+ '>'
					+ '좋아요' 
					+ '</button>' 
					+ '</td></tr>'
					+ '<tr>';
		}
	}
	var addBoxNum = dt.getDay() - 1;
	if (addBoxNum == 6) {
		addBoxNum = 0;
	}
	if (addBoxNum != 5) {
		for (var i = addBoxNum; i < 5; i++) {
			html += '<td></td>';
		}
	}
	// console.log(dt.getDay());

	html += '</table>'
	calendarDiv.innerHTML = html;
}

// 현재 년도부터 10년치 년도 선택
function selectYearList(year) {
	// 현재 날짜 속성으로 지정
	var html = '<select id = "yearList" onchange="selectYearChange()">';

	dt = new Date();
	year = year - 5;
	for (var i = 0; i < 10; i++) {
		if (i == 5) {
			html += '<option id = "listYear" value = ' + year + ' selected>' + year + "년"
					+ '</option>';
		} else {
			html += '<option value = ' + year + '>' + year + "년" + '</option>';
		}
		year++;
	}
	html += '</select>';
	document.getElementById('nowYear').innerHTML = html;
}

// 현재 월 부터 12월 까지
function selectMonthList(month) {
	var html = '<select id = "monthList" onchange="selectMonthChange()">';
	for (var i = 1; i <= 12; i++) {
		if (i != month) {
			html += '<option value = ' + i + '>' + i + '월' + '</option>';
		} else if (i == month) {
			html += '<option id = "listMonth" value = ' + i + ' selected>' + i + '월'
					+ '</option>';
		}
	}

	html += '</select>';
	document.getElementById('nowMonth').innerHTML = html;
}

// 셀렉트 년도 변경 이벤트
function selectYearChange() {

	var yearSelect = document.getElementById("yearList");
	var monthSelect = document.getElementById("monthList");
	// select element에서 선택된 option의 value가 저장된다.
	var yearVal = yearSelect.options[yearSelect.selectedIndex].value;
	var month = monthSelect.options[monthSelect.selectedIndex].value;
	
	
	//console.log("change year _ year : " + year);
	//console.log("change year _ month : " + month);
	
	calcCalendar(yearVal, month)
	selectYearList(yearVal);
	selectMonthList(month);
	printYearMonth(yearVal, month);
	hiddenAllBut();
	clickBut();
	var year = String(yearVal).substring(2,4);
	printSelectDateAjax(year, month);
}

// 셀렉트 월 변경 이벤트  '<select id = "monthList" onchange="selectMonthChange()">';

function selectMonthChange() {
	hiddenAllBut();
	var yearSelect = document.getElementById("yearList");
	var monthSelect = document.getElementById("monthList");
	// select element에서 선택된 option의 value가 저장된다.
	var yearVal = yearSelect.options[yearSelect.selectedIndex].value;
	var month = monthSelect.options[monthSelect.selectedIndex].value;

	
	// select element에서 선택된 option의 value가 저장된다.
	// console.log("change month _ year : " + year);
	// console.log("change month _ month : " + month);

	calcCalendar(yearVal, month);
	selectYearList(yearVal);
	selectMonthList(month);
	printYearMonth(yearVal, month);
	hiddenAllBut();
	clickBut();
	
	var year = String(yearVal).substring(2,4);
	printSelectDateAjax(year, month);
}

// 달력 출력 함수
function printYearMonth(year, month) {
	var html = '<h3>' + String(year) + '년 ' + String(month) + '월' + '</h3>';
	// console.log(html);
	document.getElementById('calendarDiv').innerHTML = html;
}
