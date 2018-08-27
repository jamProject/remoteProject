/**@author wookim
 * 
 */

//문서 전체 출력 후
$(document).ready(function() {
	//var myFolder = new Folder(window.location.pathname);
	//console.log(folder.exits);
	//console.log(window.location.pathname);
	$("#calendarContent").load("calendarPage.jsp");
	$("#mapContent").load("mapPage.jsp");
	//$("#calendarContent").load("./calendarPage.jsp");
	$("#viewAllContent").load("viewAllPage.jsp");

	$('.drawer').on("drawer",function(){
		$('.drawer').drawer({
			  class: {
			    nav: 'drawer-nav',
			    toggle: 'drawer-toggle',
			    overlay: 'drawer-overlay',
			    open: 'drawer-open',
			    close: 'drawer-close',
			    dropdown: 'drawer-dropdown'
			  },
			  iscroll: {
			    preventDefault: false
			  },
			  showOverlay: true
			});
	});
	
	
	document.getElementById("sendButton").onclick = function() {
		var input = document.getElementById('inputText').value;
		w.send(id + "/" + input);
	}
	
	$(document).ready(function() {
		$('.drawer').drawer();
		
		/* var trigEnter = document.getElementById('inputText')
		trigEnter.addEventListener('keyup', function(event) {
			event.preventDefault();
			if(event.keyCode === 13) {
				document.getElementById('sendButton').click();
				var input = document.getElementById('inputText').value;
				w.send(id + "/" + input);
			}
		}); */
		
		document.getElementById("sendButton").onclick = function() {
			var input = document.getElementById('inputText').value;
			w.send(id + "/" + input);
		}
		
});

// websocket 부분에 대한 스크립트
var log = function (s) {
	// 이 부분에 메시지 형식 넣어야함.
	document.getElementById("exampleFormControlTextarea3").textContent += (s + "\n");
}

var id = '<%=id%>';
var teamNo = '<%=teamNo%>';

w = new WebSocket("ws://localhost:8800/jamplan/planMainChat?id="+id + "&teamNo=" + teamNo);
// 서버에서 handshaking이 성공적으로 끝나면 자동으로 호출되는 메서드
w.onopen = function () {
	alert("WebSocket Connected!");
}
w.onmessage = function(e) {
	log(e.data.toString());
}
w.onclose = function(e) {
	log("WebSocket closed!!");
}
w.onerror = function(e) {
	log("WebSocket error!!");
}
// websocket 부분에 대한 스크립트 끝

// chat창에 대한 스크립트
function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}


// chat창에 대한 스크립트 끝




//planTable : 불러오기
function planTableGo() {
	
	
	$.ajax({
		url : '/jamplan2/planTable.plan',
		type : 'POST',
		dataType: "json",
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){
			$('#planput').empty();
			$('#saveput').empty();
			var put = '';
			var saveput = '';
				saveput += '<br><br>';
				saveput += '<a href="javascript:savePlanTable();"><button class="btn btn-primary pull-right" >저장</button></a>';
				$('#saveput').append(saveput);
				
			$.each(data, function(index, item){
				/* $('#planput').empty(); */
				var planput = '';
				planput += '<table class="table">';
				planput += '<thead>';
				planput += '<th>날짜 </th>';
				planput += '<th>장소</th>';
				planput += '<th>일정</th>';
				planput += '</thead>';
				planput += '<tbody>';
				planput += '<tr>';
				planput += '<td>' + item.calendar + '</td>';
				planput += '<td>' + item.map + '</td>';
				planput += '<td><textarea class="form-control" name="memo" id="memo' + index + '" placeholder="여행계획을 작성해보세요!" rows="5" cols="30" >'+ item.memo +'</textarea></td>';
				planput += '<td><input type="text" hidden="hidden" id="planSeq'+index+'" value="'+item.planSeq+'"></td>'
				planput += '</tr>';
				planput += '</tbody>';
				planput += '</table>';
				
//					memoList[index] = item.map;
				console.log("planput" + planput);
				$('#planput').append(planput);
			});
		},
		error:function() {
			alert('ajax통신실패!!!');
		}
	});
}




// savePlanTable : 저장
function savePlanTable(){
	var memoCnt = $('[name="memo"]');
	var params = {};
	for(var i=0; i<memoCnt.length; i++){
		params = {"memo" : $('#memo'+ i).val(), "planSeq" : $('#planSeq'+ i).val()};
		console.log(params);
		$.ajax({
			url : '/jamplan2/savePlanTable.plan',
			type : 'POST',
			dataType: "json",
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
	        data : params,
	        success:function(data) {
			
	        	/* $.each(data, function(index, item){
					$('#updateput').empty();
					var updateput = '';
					updateput += '<table class="table">';
					updateput += '<thead>';
					updateput += '<th>날짜 </th>';
					updateput += '<th>장소</th>';
					updateput += '<th>일정</th>';
					updateput += '</thead>';
					updateput += '<tbody>';
					updateput += '<tr>';
					updateput += '<td>' + item.calendar + '</td>';
					updateput += '<td>' + item.map + '</td>';
					updateput += '<td><textarea class="form-control" id="memo" placeholder="여행계획을 작성해보세요!" rows="5" cols="30" >'+ item.memo +'</textarea></td>';
					updateput += '</tr>';
					updateput += '</tbody>';
					updateput += '</table>';
					
					
					console.log("updateput" + updateput);
					$('#updateput').append(updateput);
					
				}); */
			},
	        	error:function() {
	        		alert('ajax통신실패!!!');
	        	}
	        });
	}
	alert('저장성공!');
	
	}
});

	
