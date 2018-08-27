package com.spring.jamplan.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Controller
public class AdminController {
	
	ArrayList<TeamInfoVO> teamList;
	ArrayList<PlanVO> planList;
	
	@Autowired(required=false)
	private AdminDAO adminDAO;
	
	@Autowired(required=false)
	private TeamInfoVO teamInfo;
	
	@Autowired(required=false)
	private PlanVO plan;
	
	@Autowired(required=false)
	private UserVO user;
	
	@RequestMapping(value="adminPage.admin")
	public String adminPage(UserVO user) {
		return "admin/adminPage";
	}
	
	@RequestMapping(value="adminTeamSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminTeamSearch(HttpServletRequest request) {
		System.out.println("adminTeamSearch IN");
		teamInfo = (TeamInfoVO)request.getAttribute("searchItem");
		teamList = adminDAO.adminTeamSearch(teamInfo);
		
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(teamList);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("adminTeamSearch OUT");
		
		return teamListToJson;
	}
	
	@RequestMapping(value="adminPlanSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminPlanSearch(HttpServletRequest request) {
		System.out.println("adminPlanSearch IN");
		plan = (PlanVO)request.getAttribute("searchItem");
		planList = adminDAO.adminPlanSearch(plan);
		
		String planListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			planListToJson = mapper.writeValueAsString(planList);
			System.out.println(planListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("adminPlanSearch OUT");

		return planListToJson;
	}
	
	@RequestMapping(value="adminUserSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminUserSearch(HttpServletRequest request) {
		System.out.println("adminUserSearch IN");
		user = (UserVO)request.getAttribute("searchItem");
		user = adminDAO.adminUserSearch(user);
		
		String userToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			userToJson = mapper.writeValueAsString(user);
			System.out.println(userToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("adminUserSearch OUT");

		return userToJson;
	}
}
