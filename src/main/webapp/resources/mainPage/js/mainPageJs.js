/* @author wookim
 */

$(document).ready(function(){
	
	
	$(".login").css('visibility', 'hidden');
	
	$(".join").css('visibility', 'hidden');
	
    $(".loginBut").click(function(){
    	$(".login").css('visibility', 'visible');
    	$(".join").css('visibility', 'hidden');
    });
    
    $(".joinBut").click(function(){
    	$(".join").css('visibility', 'visible');
    	$(".login").css('visibility', 'hidden');
    });
});


function check()
{
	
	// 아이디 체크 ----> 
	if ($('.login [name="id"]').val()=="")
		
	{
		console.log("dd");
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
