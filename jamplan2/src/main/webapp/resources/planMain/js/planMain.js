/**@author wookim
 * 
 */

//문서 전체 출력 후
$(document).ready(function() {
	
	$(".link").click(function(){
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

function ajaxPageMove(url, id){
	
}