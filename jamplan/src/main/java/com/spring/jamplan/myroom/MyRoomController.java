package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;



@Controller
public class MyRoomController {

	@Autowired
	private MyRoomDAO myRoomDAO;
		
	private HashMap<String, Object> map;

	@RequestMapping(value="movePlanMainPage.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public String movePlanMainPage(TeamInfoVO vo, HttpSession session) {

		System.out.println("무브 컨트롤러 : " +vo.getPlanNo());
		vo.setId((String)session.getAttribute("id"));

		TeamInfoVO teamVO =  myRoomDAO.getRole(vo);
		session.setAttribute("planNo",vo.getPlanNo());
		System.out.println("role"+ teamVO.getRole());
		session.setAttribute("role", teamVO.getRole());
		System.out.println("페이지 이동 컨트롤러 진입");
		
		return "managePlan/main";
	}
	
	@RequestMapping(value="/idSession.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String idSession(HttpSession session) {
		System.out.println("세션"+session.getAttribute("id"));
		
		map = new HashMap<String, Object>();
		map.put("id", (String)session.getAttribute("id"));
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(map);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintTeamList OUT");
		return teamListToJson;
	}
	
	@RequestMapping(value="/getPlanListById.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getPlanListById(HttpSession session) {
		String id = (String)session.getAttribute("id");
		System.out.println("start getPlanListByTeamName id : " + id);
		
		ArrayList<TeamInfoVO> teamList = myRoomDAO.getPlanListById(id);
		
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(teamList);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*System.out.println("getPlanListByTeamName OUT");*/
		//System.out.println(teamListToJson);
		return teamListToJson;
	}
	

	@RequestMapping(value="/acceptToMember.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public void acceptToMember(HttpSession session) {
		
	}
	
	@RequestMapping(value="/updateMessage.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public void updateMessage(HttpSession session) {
		System.out.println("CONT updateMessage IN");
		String receiver = (String)session.getAttribute("id");
		myRoomDAO.updateReadMessage(receiver);
		
		System.out.println("CONT updateMessage OUT");
	}
	
	@RequestMapping(value="/getMessageById.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public ArrayList<MessageVO> getMessageById(HttpSession session, MessageVO vo) {
		System.out.println("CONT getMessage IN");
		//server에서 id값으로 메세지 테이블 값 가져오기
		
		String receiver = (String)session.getAttribute("id");
		vo.setReceiver(receiver);
	
		ArrayList<MessageVO> messageList=null;
		try {
			messageList	= myRoomDAO.getMessageList(vo);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("CONT getMessage out");
		return messageList;
	}
	
	@RequestMapping(value="/ajaxPrintTeamList.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ajaxPrintTeamList(HttpSession session) {
		System.out.println("ajaxPrintTeamList IN");
		String id = (String)session.getAttribute("id");
		List<TeamInfoVO> teamList = myRoomDAO.getTeamList(id);
		
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(teamList);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintTeamList OUT");
		return teamListToJson;
	}
	
	@RequestMapping(value="/insertPlan.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String insertPlan(HttpSession session, TeamInfoVO vo) {
		System.out.println("insertplan run");
		
		//id 저장
		vo.setId((String)session.getAttribute("id"));

		//id와 teamName으로 role teamNo등등 값 가져오기
		ArrayList<TeamInfoVO> list = myRoomDAO.getTeamInfo(vo);
		
		//planNo값이 가장 큰 값을 가져와 +1 증가시켜 planNo 설정하기
		vo.setRole(list.get(0).getRole());
		vo.setTeamNo(list.get(0).getTeamNo());	
		int maxplabNo = myRoomDAO.getMaxPlanNo() + 1;
		vo.setPlanNo(maxplabNo);
		
		//설정된 teaminfo를 insert하기
		int check = myRoomDAO.insertPlan(vo);
		map = new HashMap<String, Object>();
		
		if(check==1) {
			map.put("res", "성공");
			myRoomDAO.deleteNullPlanTeaminfo(vo.getTeamName());
			
		}else {
			map.put("res","실패");
		}
		
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(map);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintTeamList OUT");
		return teamListToJson;
	}
	
	@RequestMapping(value="/ajaxPrintPlanList.do", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ajaxPrintPlanList(TeamInfoVO team) {
		System.out.println("ajaxPrintPlanList IN");
		List<PlanVO> planList = myRoomDAO.getPlanList(team);
		
		String planListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			planListToJson = mapper.writeValueAsString(planList);
			System.out.println(planListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintPlanList OUT");
		return planListToJson;
	}

	
	@RequestMapping(value="/makeTeam.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String makeTeam(TeamInfoVO team, HttpSession session) {
		System.out.println("팀만들기 컨트롤러");
		System.out.println("teamName : " + team.getTeamName());
		team.setId((String)session.getAttribute("id"));
		System.out.println("id : " + team.getId());
		
		/*ArrayList<TeamInfoVO> list  = myRoomDAO.getPlanListById(team.getId());*/
		
		Map<String, Object> checkMap = new HashMap<String, Object>();	
		String checkMapToJson = null;
		ObjectMapper mapper = new ObjectMapper(); 
				
		try {
			int check = myRoomDAO.makeTeam(team);
			
			if(check == 1) {
				checkMap.put("check", "SUCCESS");
				checkMapToJson = mapper.writeValueAsString(checkMap.get("check"));
			}else {
				checkMap.put("check", "FAIL");
				checkMapToJson = mapper.writeValueAsString(checkMap.get("check"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return checkMapToJson;
	}

	@RequestMapping(value="/validationCheck.do", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String validationCheck(TeamInfoVO team, PlanVO plan) {
		System.out.println("validationCheck In");
		System.out.println(team.getTeamName());
		String check = myRoomDAO.validationTeamName(team);
		System.out.println("validationCheck Out");
		return check;
	}
	
	
	
	@RequestMapping(value="/updateCheck.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateCheck(UserVO vo) {
		
		List<PlanVO> planList = myRoomDAO.checkUpdate(vo);
		
		String updateListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			updateListToJson = mapper.writeValueAsString(planList);
			System.out.println(updateListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateListToJson;
	}
	@RequestMapping(value="/applyToTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String applyToTeam(HttpSession session, MessageVO vo) {
		String id = (String)session.getAttribute("id");
		String teamName = vo.getTeamName();
		map = new HashMap<String, Object>();
		vo.setSender(id);
		try {
			int check = myRoomDAO.insertApplyMessage(id,vo);		
			if(check==0) {
				map.put("res", "이미 해당 팀원임");
			}else if (check ==1) {
				map.put("res", "이미 신청 했음");
			}else {
				map.put("res", "메세지 저장 완료");
			}
			
		}catch (Exception e) {
			map.put("res", "fail");
		}
		
		String searchTeamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			searchTeamListToJson = mapper.writeValueAsString(map);
			System.out.println(searchTeamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("applyTeam Out");
		return searchTeamListToJson;
	}
	//deleteCansleMessage
	@RequestMapping(value="/deleteMessageToTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMessageToTeam(HttpSession session, MessageVO vo) {
		System.out.println("deleteMessageToTeam 진입");
		String id = (String)session.getAttribute("id");
		String teamName = vo.getTeamName();
		map = new HashMap<String, Object>();
		vo.setSender(id);
		try {
			int check = myRoomDAO.deleteCansleMessage(vo);		
			if(check==0) {
				map.put("res", "이미 해당 팀원임");
			}else if (check ==1) {
				map.put("res", "메세지 삭제");
			}else {
				map.put("res", "신청한적 없음");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("res", "fail");
		}
		
		String searchTeamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			searchTeamListToJson = mapper.writeValueAsString(map);
			System.out.println(searchTeamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("deleteApplyMessageTeam Out");
		return searchTeamListToJson;
	}
	
	
	@RequestMapping(value="/searchTeam.do", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String searchTeam(TeamInfoVO team) {
		System.out.println("searchTeam In");
		System.out.println(team.getTeamName());
		
		ArrayList<TeamInfoVO> teamList = myRoomDAO.searchTeam(team);
		
/*		if(teamList == null) {
			map = new HashMap<String, Object>();
			map.put("res", "null");
		}*/
		String searchTeamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			searchTeamListToJson = mapper.writeValueAsString(teamList);
			System.out.println(searchTeamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("searchTeam Out");
		
		return searchTeamListToJson;
	}
}
