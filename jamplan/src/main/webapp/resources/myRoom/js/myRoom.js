/**
 * 
 */
// 메시지를 실시간으로 처리하기 위한 웹소켓 개통 부분
var domain = "ws://localhost:8800/jamplan/jamplanWebSocket";
function sendMessage() {
	webSocket = new WebSocket(domain);

	// 서버와 연결이 성공하면 자동으로 호출되는 메서드
	webSocket.onopen = function(event) {
		onOpen(event);
	};
	webSocket.onmessage = function(event) {
		onMessage(event);
	};
	webSocket.onerror = function(event) {
		onError(event);
	};
}

function onOpen(event) {
	webSocket.send('<%=id%>');
}

function onMessage(event) {
	$('#countLabel').append(event.data);
}

function onError(event) {
}
// 메시지를 실시간으로 처리하기 위한 웹소켓 개통 부분 끝 --%>

// 배열을 생성하고 팀 이름들을 저장한다.
var teamNameArray = [];

function ajaxGetTeamList() {
	var id = '<%=id%>';
	$('#teamList').empty();
	$
			.ajax({
				url : '/jamplan/ajaxPrintTeamList.do',
				type : 'POST',
				data : {
					'id' : id
				},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				dataType : 'json',
				success : function(data) {

					// data에 들어있는 데이터 수만큼 반복되고 하나하나가 매번 item에 매핑된다.
					$
							.each(
									data,
									function(index, item) {
										var teamList = '';
										teamList += '<button class="btn btn-outline-light text-dark border-0 teamList" id="myTeam'
												+ index
												+ '"'
												+ ' data-toggle="collapse" data-target="#myPlan'
												+ index
												+ '">'
												+ item.teamName
												+ '</button>'
												+ '<div id="myPlan'
												+ index
												+ '"'
												+ ' class="collapse">'
												+ 'plan' + '</div>';
										$('#teamList').append(teamList);

										// 팀명을 배열에 담고 Add plan 버튼에서의 테이블 생성에
										// 이용한다.
										teamNameArray[index] = item.teamName;
									});
				},
				error : function(e) {
					alert("unload " + e);
				},
			});
}

function validationCheck() {
	var params = $('#teamName').val();
	alert(params);
	jQuery.ajax({
		url : '/jamplan/validationCheck.do',
		type : 'GET',
		data : {
			teamName : params
		},
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		dataType : 'text',
		// check가 responsebody에서 오는 데이터를 받는다.
		success : function(check) {
			alert(check);
			if (check == 'SUCCESS') {
				$('#validationCheck').val('check');
				$('#validationCheck').css('color', 'powderblue');

			} else {
				$('#teamName').val('');
				$('#inputForm').attr('disabled', 'true');

				if ($('#teamName').focus()) {
					alert("focus in");
					$('#validationCheck').enable();
				} else {
					alert("focus out");
				}

			}

		},
		error : function() {
			alert("error!!");
		}
	});

}

/* 메세지 팝업창을 위한 스크립트 */
$(document).on('click', '.panel-heading span.icon_minim', function(e) {
	var $this = $(this);
	if (!$this.hasClass('panel-collapse')) {
		$this.parents('.panel').find('.panel-body').slideUp();
		$this.addClass('panel-collapse');
		$this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
	} else {
		$this.parents('.panel').find('.panel-body').slideDown();
		$this.removeClass('panel-collapse');
		$this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
	}
});
/* 메세지 팝업창을 위한 스크립트 끝 */

$(document)
		.ready(
				function() {
					// 페이지가 로드되면 바로 받은 메시지를 보여준다.
					/* sendMessage(); */

					// 로드 되면 업데이트 사항 바로 보여준다.

					// 로드 되면 출력한다.
					ajaxGetTeamList();

					$('#addPlan').click(function() {

					})

					// 팀명 클릭했을 경우 팀명을 보내고 해당 팀의 멤버들을 불러와야한다.

					/*
					 * $('.teamList').click(function () { var params = $(this);
					 * alert(params);
					 * 
					 * jQuery.ajax({ url : '/jamplan/ajaxPrintPlanList.do', type :
					 * 'GET', data : { id: <%=id %>, teamName: params },
					 * dataType : 'json', contentType :
					 * 'application/x-www-form-urlencoded;charset=utf-8', //
					 * check가 responsebody에서 오는 데이터를 받는다. success :
					 * function(data) { alert(data); if(data == null) { var
					 * nullPlan = ''; nullPlan += '<h4>일정을 만들어볼까요?</h4>';
					 * $(this).append(nullPlan); }else {
					 * $.each(data,function(index, item) { var planList = '';
					 * planList += '<button class="btn btn-outline-light
					 * text-dark border-0" id="myTeam' + index + '"' + '
					 * data-toggle="collapse" data-target="#myPlan' + index +
					 * '">' + item.teamName + '</button>' + '<div
					 * class="collapse" id="myPlan' + index + '">' + 'hello' + '</div>';
					 * $(this).append(planList);
					 * 
					 * });
					 *  } }, error : function() { alert("일정 목록을 불러올 수 없습니다."); }
					 * }); // 기본 이벤트 제거 event.preventDefault(); })
					 */

					$('#inputForm')
							.click(
									function(event) {
										var params = $('#makeTeamForm')
												.serialize();
										alert(params);
										jQuery
												.ajax({
													url : '/jamplan/makeTeam.do',
													type : 'POST',
													data : params,
													contentType : 'application/x-www-form-urlencoded;charset=utf-8',
													dataType : 'json',
													// check이 responsebody에서 오는
													// 데이터를 받는다.
													success : function(check) {
														if (check == "SUCCESS") {
															ajaxGetTeamList();
															// 초기화
															$('#teamName').val(
																	'');
														} else {
															alert("Insert Fail!!");
														}
													},
													error : function() {
														alert("에러 발생!!");
													}
												});
										// 기본 이벤트 제거
										event.preventDefault();
									});

					// 팀 이름에 대해 유효성 체크하는 부분
					$('#validationCheck').click(function() {
						validationCheck();
					})

					// Add plan 버튼에 대한 클릭 이벤트 부분
					$('#addPlan')
							.click(
									function() {
										$('#planSpace').empty();
										console.log('테이블 생성하는 부분까지는 들어옴');
										var html = '<table id="teamNameTable" class="table table-hover text-center">'
												+ '<thead><tr><th>No.</th><th>가입된 팀</th>'
												+ '</tr></thead><tbody>';
										console.log('테이블 생성하기 직전');
										for (var index = 0; index < teamNameArray.length; index++) {
											html += '<tr><td>' + (index + 1)
													+ '</td><td>'
													+ teamNameArray[index]
													+ '</td></tr>';
										}

										html += '</tbody></table>';
										console.log('테이블 생성 태그는 모두 완성');
										$('#planSpace').append(html);
										console.log('append했지만 과연??!!');
									})

					// Add plan 모달 창에서 테이블 내의 각 팀명 클릭 시, 팀명 값을 가져온다.
					$('#teamNameTable tr').click(function() {

						var teamNameTable = $('#teamNameTable').val();
						var str = '';
						var tr = $(this);
						var td = tr.children();

						// 값을 잘 받아왔는지 확인
						console.log('td: ' + td);
						str = '' + td + '';

						$('#teamNameByTable').val(str);
						alert($('#teamNameByTable').val());
					})

					// 업데이트된 사항이 있는지 5초마다 체크한다.
					var interval = setInterval(
							function() {
								var id = '<%=id %>';
								$('#updateSpace').empty();
								$
										.ajax({
											url : '/jamplan/updateCheck.do',
											type : 'GET',
											data : {
												id : id
											},
											dataType : 'json',
											contentType : 'application/x-www-form-urlencoded;charset=utf-8',
											success : function(data) {

												$
														.each(
																data,
																function(index,
																		item) {
																	var update = '';
																	update += '<div class="media border p-3">'
																			+ '<img src="http://alumnes.org/wp-content/uploads/2017/06/fa-user-circle-o-c0a2bd7a.png"'
																			+ 'alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">'
																			+ '<div class="media-body">'
																			+ '<h4>'
																			+ item.planName
																			+ '</h4>'
																			+ '<p>일정에 변화가 있어요. 확인해 보세요.</p>'
																			+ '</div>'
																			+ '</div>'
																			+ '</br></br>';
																	$(
																			'#updateSpace')
																			.append(
																					update);
																})
											},
											error : function() {
												alert("Error");
											}
										});

							}, 30000)

					// 팀검색을 시도할 경우 업데이트 사항 보여주기를 멈추고 비슷한 이름들의 팀을 나열해서 보여준다.
					$('#searchButton')
							.click(
									function() {
										// updateSpace에 나오던 업데이트 작업을 중단한다.
										clearInterval(interval);

										var teamName = $('#searchTeamName')
												.val();

										// 테이블 형태로 바꿔주기 위해 기존에 있던 업데이트 사항들을
										// 지워준다.
										$('#updateSpace').empty();

										var tableHead = '<table class="table table-hover">'
												+ '<thead><tr>'
												+ '<th scope="col">팀 이름 검색결과</th>'
												+ '</thead></tr><tbody>';

										var tableTail = '</tbody></table>';

										console.log('ajax전까지는 왔다.');
										$
												.ajax({
													url : '/jamplan/searchTeam.do',
													type : 'GET',
													data : {
														'teamName' : teamName
													},
													dataType : 'json',
													contentType : 'application/x-www-form-urlencoded;charset=utf-8',
													success : function(data) {

														$
																.each(
																		data,
																		function(
																				index,
																				item) {
																			var update = '';
																			update += '<tr><td>'
																					+ item.teamName
																					+ '</td></tr>';
																			$(
																					'tableHead')
																					.append(
																							update);
																		})
														$('tableTail').append(
																tableHead);
														$('#updateSpace')
																.append(
																		tableTail);
													},
													error : function() {
														alert("searchTeam ERROR");
													}
												});
									});
				})
