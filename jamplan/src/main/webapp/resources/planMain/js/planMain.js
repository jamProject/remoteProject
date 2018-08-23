/**@author wookim
 * 
 */

//문서 전체 출력 후
$(document).ready(function() {
	
	
//	$('.drawer').drawer();
	/*var link = $(".tab-pane container active").attr("value");
	console.log(link);
	$.ajax({
		url : "calendarajax.mp",
		type : "post",
		contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
		dataType : "json",
		success:function(str){
			$(".content").load(str.link);
			console.log('로드 후11')
		},	
		error:function(){
			alert("페이지 이동 ajax실패")
		}
	})
	*/
	document.getElementById("sendButton").onclick = function() {
		var input = document.getElementById('inputText').value;
		w.send('thkim9198' + "/" + input);
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


