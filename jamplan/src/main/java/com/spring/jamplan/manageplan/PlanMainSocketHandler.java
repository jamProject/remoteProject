package com.spring.jamplan.manageplan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.spring.jamplan.model.TeamInfoVO;

public class PlanMainSocketHandler extends TextWebSocketHandler{
	
	@Autowired(required=false)
	private ChatDAOService chatDAOService;
	
	@Autowired(required=false)
	private TeamInfoVO teamInfo;
	
	private final Logger logger = LogManager.getLogger(getClass());
	HttpServletRequest request;
	
	
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	
	// team마다 한개의 채팅방을 가지기위해 session들을 모아놓은 List별로 관리한다. 
	private Map<Integer, Set<WebSocketSession>> chatSetGroupMap = new HashMap<Integer, Set<WebSocketSession>>();
	
	// session을 id와 teamNo별로 관리하기 위한 Map 생성.
	private Map<WebSocketSession, String> idMap = new HashMap<WebSocketSession, String>();
	private Map<WebSocketSession, Integer> teamNoMap = new HashMap<WebSocketSession, Integer>();
	
	
	// 실질적으로 팀마다 채팅방을 개설, 유지하도록 관리해주는 list.
	// 만약 같은 session일 경우에 list에 넣는다면 중복될 가능성이 있지만 
	// set에 넣어줄 경우, 중복이 발생하지 않는다.
	private Set<WebSocketSession> chatListSet;
	private List<TeamInfoVO> teamList;
	
	
	public PlanMainSocketHandler() {
		super();
		this.logger.info("create SocketHandler instance!");
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		System.out.println("afterConnectionClosed IN");
//		idMap.remove(session);
//		System.out.println("afterConnectionClosed idMap에서 remove 됐는지");

		teamNoMap.remove(session);
		System.out.println("afterConnectionClosed teamNoMap에서 remove 됐는지");

		
		Map<String, Object> map = session.getAttributes();
		String id = (String)map.get("id");
		String teamNo = (String)map.get("teamNo");
		
		// 같은 팀 사람 중에 남아있는 사람이 있는지 알기 위해 DB에서 리스트 받아와서 비교한다.
		for (int i=0; i < chatDAOService.chatConnect(teamInfo).size(); i++) {
			System.out.println("for문 들어왔나??");
			for(TeamInfoVO teamInfo : chatDAOService.chatConnect(teamInfo)) {
				// idMap에 같은 팀 사람이 남아있는지 체크하는 부분
				if(idMap.containsValue(teamInfo.getId())) {
					System.out.println("아직 같은 팀 중에 남아있는 사람이 있다.");
				}else {
					System.out.println("남아있는 사람이 없다.");
					chatSetGroupMap.remove(Integer.parseInt(teamNo));
				}
			}
		}
		
		sessionSet.remove(session);
		
	}
	
	
	@Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		this.logger.info("add session!");
		
		// session에 담긴 정보(id와 teamNo)를 map에 담는다.
		Map<String, Object> map = session.getAttributes();
		// 생성돼있는 bean객체인 team의 id필드에 set.
		String id = (String)map.get("id");
		idMap.put(session, id);
		teamInfo.setId(id);
		
		// 생성돼있는 bean객체인 team의 teamNo필드에 set.
		String teamNoAsString = (String)map.get("teamNo");
		int teamNo = Integer.parseInt(teamNoAsString);

		teamNoMap.put(session, (Integer)teamNo);
		teamInfo.setTeamNo(teamNo);
		
		System.out.println("DB 들어가기 전");
		
		// 같은 팀멤버들을 select해서 모아놓은 list
		teamList = chatDAOService.chatConnect(teamInfo);
		System.out.println("for문 들어가기 전 teamList 나왔나 점검" + teamList.get(0).getId());
		for(int i=0; i<teamList.size(); i++) {
			TeamInfoVO teamResult = teamList.get(i);
			System.out.println("teamResult 나왔나 점검 : " + teamResult.getId());
			// 이미 team의 채팅방에 대한 set이 만들어져있는 상태라면 그곳에 session을 넣어준다.
			System.out.println("소켓핸들러에서 형변환 필요한지 전");
			if(chatSetGroupMap.containsKey(teamInfo.getTeamNo())) {
				System.out.println("소켓핸들러에서 형변환 필요한지 후1");
				
				// 특정 team의 채팅방 관리하는 set에 session을 넣는다.
				chatSetGroupMap.get(teamInfo.getTeamNo()).add(session);
				
				// session 집합을 teamNo를 key값으로해서 저장한다.
				for(WebSocketSession assignedSession : chatSetGroupMap.get(teamInfo.getTeamNo())) {
					assignedSession.sendMessage(new TextMessage("message/" + id + "님이 참여했습니다."));
				}
				
			}else {
				// 특정 팀에 속해있지만 각 팀의 사용자들 구분을 위한 session들의 집합은 만들어지지 않았을때.
				// 즉, 어떠한 팀에 누군가가 처음 접속했을 때 각 사용자들을 팀에 따라 구분하기 위해 list 생성
				System.out.println("소켓핸들러에서 형변환 필요한지 후2");
				
				// 새로운 list를 만들고 session을 넣어준다.
				chatListSet = getChatGroup(idMap, id);
				sessionSet.add(session);
				chatSetGroupMap.put(teamInfo.getTeamNo(), chatListSet);
				System.out.println("메시지 전송되기 직전!!!!");
				session.sendMessage(new TextMessage("message/" + teamResult.getTeamName() + " 방으로 입장했습니다."));
			}
			
		}
		
	}
	
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		
		Map<String, Object> map = session.getAttributes();
		String userId = (String)map.get("id");
		Integer teamNo = (Integer)map.get("teamNo");
		
		
		Set<WebSocketSession> instantSessionList = chatSetGroupMap.get(teamNo);
		
		for(WebSocketSession client_session : instantSessionList) {
			if(client_session.isOpen()) {
				try {
					client_session.sendMessage(new TextMessage("message/"+(String)message.getPayload()));
				}catch(Exception ignored) {
					
					this.logger.error("fail to send message!", ignored);
				}
			}
		}
	}
	
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
		this.logger.error("web socket error!", exception);
	}
	
	@Override
	public boolean supportsPartialMessages() {
		this.logger.info("call method!");
		
		return false;
	}
	
	
	public Set<WebSocketSession> getChatGroup(Map<WebSocketSession, String> group, String value) {
		Set<WebSocketSession> chatListSet = new HashSet<WebSocketSession>();
		System.out.println("getChatGroup IN");
		for(WebSocketSession one : group.keySet()) {
			if(group.get(one).equals(value)) {
				chatListSet.add(one);
				System.out.println(chatListSet.size());
			}
		}
		System.out.println("getChatGroup OUT");

		return chatListSet;
	}
}
