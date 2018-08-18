package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;



@Controller
public class MyRoomController {

	@Autowired(required = false)
	private MyRoomDAO myRoomDAO;
	
	@Autowired
	private TeamInfoVO teamVO;
	
	private HashMap<String, Object> map;
/*	
	@RequestMapping("/home.do")
	public String myRoomMain2(String id, Model model) {
		
		return "home";
	}*/
	
//	방(그룹)안으로들어가기
//	@RequestMapping(value="/inRoom.do")
//	public String inRoom(UserVO user, Model model, HttpSession session) {
//		//여기에다가 방 pk 값 넣기 session에 넣기
//		session.setAttribute("roomId", "roomid pk값 넣어버리기");
//		//여기에 넣는 이유 나중에 웹소켓에서 이 사람이 수정한 글이 속한 room_id에 속해있는 다른 사용자에게 알림 날리기 위해.
//		//만약에 html화면단에서 pk아이디를 직접 서버로 주게되면 내가 수정한 그룹이 아닌 다른 그룹 pk를 강제로 싣어서 웹소켓에 전송하면
//		//엉뚱한 사람이 알림 받게됨.
//		//웹소켓에서 session.get어쩌고를 이용해서 이 사람이 속한 그룹의 사용자에게 for돌면서 웹소켓 쏴줄예정.
//		return "방안으로들어가는jsp";
//	}
	
/*	@RequestMapping(value="/printTeamList.do")
	public String printTeamList(HttpSession session, Model model) {
		System.out.println("printTeamList IN");
		String id = (String)session.getAttribute("id");
		List<TeamVO> teamList = myRoomDAO.getTeamList(id);
		System.out.println("db sucess");
		model.addAttribute("teamList", teamList);
		model.addAttribute("id", id);
		System.out.println("printTeamList OUT");
		return "MyRoomConfirm";
	}*/
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
		
		System.out.println("teanNO : "+vo.getTeamNo());
		System.out.println("teamName : " + vo.getTeamName());
		System.out.println("id : "+vo.getId());
		System.out.println("role : "+vo.getRole());
		System.out.println("planNo : "+vo.getPlanno());
		System.out.println("planName : "+vo.getPlanName());
		System.out.println("joinDate "+vo.getJoinDate());
		
		//id 저장
		vo.setId((String)session.getAttribute("id"));
		System.out.println(vo.getId());
		//id와 teamName으로 role teamNo등등 값 가져오기
		ArrayList<TeamInfoVO> list = myRoomDAO.getTeamInfo(vo);
		
		System.out.println("af teanNO : "+list.get(0).getTeamNo());
		System.out.println("af teamName : " + list.get(0).getTeamName());
		System.out.println("af id : "+list.get(0).getId());
		System.out.println("af role : "+list.get(0).getRole());
		System.out.println("af planNo : "+list.get(0).getPlanno());
		System.out.println("af planName : "+list.get(0).getPlanName());
		System.out.println("af joinDate "+list.get(0).getJoinDate());
		vo.setRole(list.get(0).getRole());
		vo.setTeamNo(list.get(0).getTeamNo());
		//planNo값이 가장 큰 값을 가져와 +1 증가시켜 planNo 설정하기
		int maxplabNo = myRoomDAO.getMaxPlanNo() + 1;
		vo.setPlanno(maxplabNo);
		//설정된 teaminfo를 insert하기
		int check = myRoomDAO.insertPlan(vo);
		map = new HashMap<String, Object>();
		if(check==1) {
			map.put("res", 1);
		}else {
			map.put("res",0);
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
	/*@RequestMapping(value="/insertPlan.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public int getMaxPlanNo(TeamInfoVO vo) {
		

	}*/
		
	
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
	public String makeTeam(TeamInfoVO team) {
		System.out.println("teamName : " + team.getTeamName());
		System.out.println("id : " + team.getId());
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
	
	@RequestMapping(value="/searchTeam.do", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String searchTeam(TeamInfoVO team) {
		System.out.println("searchTeam In");
		System.out.println(team.getTeamName());
		
		List<TeamInfoVO> teamList = myRoomDAO.searchTeam(team);
		
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
