package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

@Controller
public class AdminController {
	
	@Autowired(required = true)
	UserVO userVO;
	
	@Autowired(required = true)
	TeamVO teamVO;
	
	@Autowired(required = true)
	PlanVO planVO;
	
	@Autowired
	private AdminDAOService adminDAOService;

	//test
	@RequestMapping("mapPage.admin")
	public String mapPage() {
		return "managePlan/mapPage";
	}
		
	@RequestMapping("/getUserList.admin")	
	public ModelAndView getUserList() {
		ModelAndView result = new ModelAndView();
		ArrayList<UserVO> userList = adminDAOService.getUserList();
		result.addObject("userList", userList);
		result.setViewName("/admin/adminPage");
		return result;
	}

	@RequestMapping("/getTeamList.admin")
	public ModelAndView getTeamList() {
		return null;
	}
	
	@RequestMapping("/getPlanList.admin")
	public ModelAndView getPlanList(String planName) {
		return null;
	}
	
	@RequestMapping("/getUser.admin")
	public ModelAndView getUser(String id) {
		return null;
	}

	@RequestMapping("/getTeam.admin")
	public ModelAndView getTeam(String teamName) {
		return null;
	}
	
	@RequestMapping("/deleteUser.admin")
	public ModelAndView deleteUser(String id) {
		return null;
	}
	
	@RequestMapping("/deleteTeam.admin")
	public ModelAndView deleteTeam(String teamName) {
		return null;
	}
	
	@RequestMapping("/deletePlan.admin")
	public ModelAndView deletePlan(int planNo) {
		return null;
	}
	
	
	
}
