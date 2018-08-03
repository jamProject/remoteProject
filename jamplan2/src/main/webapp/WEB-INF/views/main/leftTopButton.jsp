<!--한글 인코딩 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>

<head>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
window.onload = function(){
	//문서 객체를 선택합니다.
	var login = document.getElementById('login');
	var join = document.getElementById('join');
	var loginSns = document.getElementById('loginSns');
};
function loginclick()
{
	location.href = "/frontPage.jsp?page=login";
	}
function joinclick()
{
	location.href = "/frontPage.jsp?page=join";
	}
function loginSnsclick()
{
	location.href = "/frontPage.jsp?page=loginSns";
	}
</script>

<body>
<button type="button" id =login class="btn btn-primary btn-block" onclick="loginclick()">Login</button>
<button type="button" id = join class="btn btn-primary btn-block" onclick="joinclick()">join</button>
<button type="button" id = loginSns class="btn btn-default btn-block" onclick="loginSns()">loginSns</button>
</body>
</html>