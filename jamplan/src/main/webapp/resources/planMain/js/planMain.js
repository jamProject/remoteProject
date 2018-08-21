/**@author wookim
 * 
 */

//문서 전체 출력 후
$(document).ready(function() {
	$('.drawer').drawer();
	/*var link = $(".tab-pane container active").attr("value");
	console.log(link);*/
	$.ajax({
		url : "calendarajax.mp",
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
	})
	
	document.getElementById("sendButton").onclick = function() {
		var input = document.getElementById('inputText').value;
		w.send(id + "/" + input);
	}
	
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
		})
	})
});


	


//chat창에 대한 스크립트
function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}


//websocket 부분에 대한 스크립트
var log = function (s) {
	// 이 부분에 메시지 형식 넣어야함.
	document.getElementById("exampleFormControlTextarea3").textContent += (s + "\n");
}

var id = 'thkim9198';
var teamNo = '3';

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



	
