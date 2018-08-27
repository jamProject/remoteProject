/* @author wookim
 */

$(document).ready(function(){
	
	function selectData(){ 
		//table내부 내용을 제거(초기화), 동적으로 제거
		/* $(.imgClick).click(function) {
			
		} */
		//alert("hahaha");
		$.ajax({ //jquery에서 ajax호출할때 사용하는 방식 / jQuery.ajax=$.ajax : jquery 표현하는방식 2가지 
			url:'/jamplan/imgJson.do',
			type:'GET',
			dataType:"json",
			contentType:'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){ // 응답(getPeopleJSON.do)이 존재하면 함수 실행된다. ,  getPeopleJSON.do에서 실행한 값이 data에 저장된다
				$.each(data, function(index, item){ // data 는 PeopleVO의 list들이 저장되어 item에 값(항목들)이 주어진다. / each는 data가 가지고있는 값만큼 반복수행하게해준다.(각각의데이터접근시)
					var output = '';
					output += '<div class="col-sm-4">';
					output += '<div class="thumbnail">';
					//output += '<div class="imgClick"><a href = "schedule.search">' + item.image + '</a></div>';
					//output += 
					//output += '<div>' + item.image + '/<div>'
					
					output += '<div><a href = "schedule.search"><img src="/jamplan/image/' + item.image + '" style="width:400px; height:400px;"  />' + '</a></div>';
					output += '<span>' + item.planDate + '</span>' + '&nbsp' + '&nbsp';
					output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
					output += '<button class="btn goodCount">' + item.goodCount + '</button>' + '&nbsp' + '&nbsp';
					output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp' + '&nbsp';
					output += '</div>';
					output += '</div>';
					//console.log("output:" + output);
					$('#search').append(output);
				
				});
			},
			error:function(){
				alert("ajax통신 실패!!!");
			}
		});
	}

	selectData();
});

function check()
{
// 아이디 체크 ----> 
if ($('.login [name="id"]').val()=="")
	
{
	//console.log("dd");
	alert("아이디를 입력하세요!!!");
	$('.login [name="id"]').focus();
	return false;
	}
 
if ($('.login [name="pass"]').val()=="")

{
	alert("패스워드를 입력하세요!!!");
	$('.login [name="pass"]').focus();
	return false;
	}
	 // 패스워드 체크 <------
	
	//return true;
	return true;
}
