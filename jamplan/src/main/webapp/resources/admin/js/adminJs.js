/**@author Taehyuk, Kim
 * 
 */

$(document).ready(function() {
	//select값의 변화에 따라 controller에 따로 들어가게된다.
	$('#searchButton').on('click', function(e) {
		e.preventDefault();
		console.log($('#searchButton'));
		if($('#adminSelection')[0].val().selected == 'true') {
			adminTeamSearch();
			
		}else if($('#adminSelection')[1].val().selected == 'true') {
			adminPlanSearch();
		
		}else if($('#adminSelection')[2].val().selected == 'true') {
			adminUserSearch();
		
		}
	})
	
	
});

// 팀명으로 검색할 경우
function adminTeamSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	
	var html = '<table id="teamSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>팀명</th><th>팀장</th>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminTeamSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			$.each(data, function(index,item){
				html += '<tr><td>' + item.teamName + '</td>'
					+ '<td>' + item.id + '</td></tr>'
			})
			
			html += '</tbody></table>';
			$('#adminItemPrint').append(html);
		},
		error : function() {}
	})
}


// 일정명으로 검색할 경우
function adminPlanSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	
	var html = '<table id="planSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>일정명</th><th>좋아요 수</th><td>조회수</td>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminPlanSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			
			$.each(data,function(index,item){
				html += '<tr><td>' + item.teamName + '</td>'
					+ '<td>' + item.goodCount + '</td>'
					+ '<td>' + item.readCount + '</td></tr>'
		
			})	
			html += '</tbody></table>';
			$('#adminItemPrint').append(html); 
			
		}
	})
}


// 사용자 id로 검색할 경우
function adminUserSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	
	var html = '<table id="userSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>아이디</th><th>이메일</th><td>비밀번호</td><td>지우기</td>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminUserSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			$.each(data,function(index,item){
				html += '<tr><td>' + item.id + '</td>'
					+ '<td>' + item.email + '</td>'
					+ '<td>' + item.pass + '</td>'
					+ '<td><button id="userRemove' + index + '" class="btn btn-outline-danger btn-rounded"' 
					+ ' type="submit">지우기</button></td></tr>'
			})
			
			html += '</tbody></table>';
			$('#adminItemPrint').append(html); 
		}
	})
}


// 일정을 클릭하면 해당 일정으로 들어가기. (수정은 불가능)
function watchPlanAsAdmin(planNo) {
	var planNO = planNo;
	
	var form = document.createElement("form");
	form.setAttribute("method","post");
	form.setAttribute("action","movePlanMainPage.do");
	document.body.appendChild(form);
	
	var inputPlanNo = document.createElement("input");
	input.setAttribute("type","hidden");
	input.setAttribute("name","planNo");
	input.setAttribute("value",planNo);
	form.appendChild(inputPlanNo);
	
	// 관리자의 아이디를 보내고 해당 일정페이지에서 아이디를 체크해서
	// 관리자임이 확인되면 
	var adminId = 'admin';
	var inputAdmin = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "id");
	input.setAttribute("value", adminId);
	form.appendChile(inputAdmin);
	
	form.submit();
}



