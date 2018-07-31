<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<%@ page session="false" %>
<html>
<head>
	<title>JAM Plan</title>
	<script type="text/javascript">
	  dt = new Date();
      nowMonth = dt.getMonth() + 1;
      nowDay = dt.getDate();
      nowYear = dt.getFullYear();
      
		window.onload = function() {
		calcCalendar(nowYear,nowMonth+1)
		selectYearList();
		selectMonthList();
	}
	
	// 현재 날짜와 윤달, 해당 월의 일수 계산
	   function Calendar(){
	      // 윤달 판단         
	       this.calYun = function(year){
	          var yun = false;
	          if(year % 4 == 0){
	             yun = true;
	          } else if (year % 100 == 0){
	             if(year % 400 == 0){
	               yun = true;
	             }
	             yun = false;
	          } else {
	             yun =  false;
	          }   
	          return yun;   
	       }
	      // 해당 월의 일수
	       this.totMonthDay = function(mon, yun){
	         var days = 0;
	         	for(var i = 0; i < 11; i++){
	         		if(i % 2 == 0){
	         			days = 31;
	         		}else if(i==1 && yun == true ){
	         			days = 28;
	         		}else if(i==1 && yun == false){
	         			days = 29;
	         		}else{
	         			days = 30;
	         		}
	         	}
	         return days;    
	      }
	   }
	   // 달력생성 및 비어있는 칸 생성
	   function calcCalendar(year, month){
	      var dt = new Date();
	      var calendar = new Calendar();
	      var calendarDiv = document.getElementById('calendar');
	      var html = '<table id="cal"><tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr><tr>';
	      // 계산하고자 하는 연도와 월을 날짜 객체에 지정
	      dt.setYear(Number(year));
	      dt.setMonth(Number(month) - 1);
	      // 날짜 표시 반복문
	      for(var day = 1;  day <= calendar.totMonthDay(dt.getMonth(), calendar.calYun(dt.getYear())); day ++){   
	         
	           dt.setDate(day);
	           // 달력 1주차 빈칸생성 로직    
	         if(day == 1){
	            for(var num = 0; num < dt.getDay(); num++){
	               html += '<td></td>';
	            }
	         }       
	         // 일요일이 아니라면 날짜를 표시하고 일요일이라면 날짜를 표시한 후 줄바꿈
	         if(dt.getDay() != 6){          
	             html += '<td>' + String(day) + '</td>';
	          }else{
	             html += '<td>' + String(day) + '</td></tr><tr>';
	         }
	       }
	      html += '</table></div>'
	      calendarDiv.innerHTML = html;
	   }
	   
	   //현재 년도부터 10년치 년도 선택
	   function selectYearList(year) {
		   // 현재 날짜 속성으로 지정
		   if(year==null){
			   dt = new Date();
			   nowYear = dt.getFullYear();
			   var html = '<select>';
			   
			   for(var i = 0; i < 10; i++){
				   html += '<option value = ' + nowYear + '>'+nowYear+'</option>';
				   nowYear++;
			   }
			   html+='</select>';
			   document.getElementById('nowYear').innerHTML = html;
		   }else{

			   var html = '<select>';
			   
			   for(var i = 0; i < 10; i++){
				   html += '<option value = ' + year + '>'+ year+'</option>';
				   Year++;
			   }
			   html+='</select>';
			   document.getElementById('nowYear').innerHTML = html;
		   }
	   }
	   //현재 월 부터 12월 까지
	   function selectMonthList(month) {
		   if(month==null){
			   dt = new Date();

			   nowMonth = dt.getMonth()+2;
			   /* console.log(nowMonth); */
			  
			   var html = '<select>';
			   for(var i = 1; i <= 12; i++){
				   if(i != nowMonth){
					   /* console.log(i+" i :"+ i);
					   console.log(i+" nowMonth : "+ nowMonth); */
					   html += '<option value = ' + i + '>'+i+'</option>';
				   }else if(nowMonth == i){
					   /* console.log(i);
					   console.log(nowMonth); */
					   html += '<option value = ' + i +  ' selected>'+i+ '</option>';
				   }
			   }
			   
			   html+='</select>';
			   document.getElementById('nowMonth').innerHTML = html;
		   }else{
			   nowMonth = month;
			   /* console.log(nowMonth); */
			  
			   var html = '<select>';
			   for(var i = 1; i <= 12; i++){
				   if(i != nowMonth){
					   /* console.log(i+" i :"+ i);
					   console.log(i+" nowMonth : "+ nowMonth); */
					   html += '<option value = ' + i + '>'+i+'</option>';
				   }else if(nowMonth == i){
					   /* console.log(i);
					   console.log(nowMonth); */
					   html += '<option value = ' + i +  ' selected>'+i+ '</option>';
				   }
			   }
			   
			   html+='</select>';
			   document.getElementById('nowMonth').innerHTML = html;
		   }
		   
	   }
	   
	   function selectYearChange(){
		   var langSelect = document.getElementById("nowYear");
		   console.log("a");
		    // select element에서 선택된 option의 value가 저장된다.
		   var selectValue = langSelect.options[langSelect.selectedIndex].value;
		   calcCalendar(selectValue, 1)
		   selectYearList(selectValue);
		   selectMonthList(1);
	   }
	   
	   function selectMonthChange(){
		   var langSelect = document.getElementById("nowMonth");
		   console.log(langSelect);
		   dt = new Date();
		   nowYear = dt.getFullYear();
		     
		   // select element에서 선택된 option의 value가 저장된다.
		   var selectValue = langSelect.options[langSelect.selectedIndex].value;
		   calcCalendar(nowYear,selectValue)
	       selectYearList(nowYear);
		   selectMonthList(selectValue);
	   }
	</script>
</head>
<body>
<h1>
	달력
</h1>
	<table>
		<tr>
			<td>
				<div id = "nowYear" onchange="selectYearChange"></div>	
			</td>
			<td>
				<div id = "nowMonth" onchange="selectMonthChange"></div>
			</td>
		</tr>
	</table>
	<h3></h3>
	<div id ="calendar"></div>
		
	
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>