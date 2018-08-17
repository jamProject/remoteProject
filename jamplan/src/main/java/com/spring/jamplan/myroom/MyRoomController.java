package com.spring.jamplan.myroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class MyRoomController {

	@Autowired(required = false)
	private MyRoomDAO myRoomDAO;
	
	
	
	@RequestMapping("/home.do")
	public String myRoomMain2(UserVO user, Model model) {
		
		return "home";
	}
	
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
	
	@RequestMapping(value="/printTeamList.do")
	public String printTeamList(TeamVO team, Model model) {
		System.out.println("printTeamList IN");
		List<TeamVO> teamList = myRoomDAO.getTeamList(team);
		
		model.addAttribute("teamList", teamList);
		model.addAttribute("id", team.getId());
		System.out.println("printTeamList OUT");
		return "MyRoomConfirm";
	}
	
	
	@RequestMapping(value="/ajaxPrintTeamList.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ajaxPrintTeamList(TeamVO team) {
		System.out.println("ajaxPrintTeamList IN");
		List<TeamVO> teamList = myRoomDAO.getTeamList(team);
		
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
	
	@RequestMapping(value="/ajaxPrintPlanList.do", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ajaxPrintPlanList(TeamVO team) {
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
	public String makeTeam(TeamVO team) {
		System.out.println(team.getTeamName());
		System.out.println(team.getId());
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
	public String validationCheck(TeamVO team, PlanVO plan) {
		System.out.println("validationCheck In");
		System.out.println(team.getTeamName());
		String check = myRoomDAO.validationTeamName(team);
		System.out.println("validationCheck Out");
		return check;
	}
	
	
	
	@RequestMapping(value="/updateCheck.do", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateCheck(UserVO user) {
		
		List<PlanVO> planList = myRoomDAO.checkUpdate(user);
		
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
	public String searchTeam(TeamVO team) {
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
