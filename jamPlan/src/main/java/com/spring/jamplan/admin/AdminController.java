package com.spring.jamplan.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	AdminMapper adminMapper;

	@RequestMapping("/adminLoad.admin")
	public String adminLoad(HttpServletRequest request) {
		return "admin/adminPage";
	}
	
	@RequestMapping("/getUserList.admin")
	public ModelAndView getUserList(String id) {
		return null;
	}

	@RequestMapping("/getTeamList.admin")
	public ModelAndView getTeamList(String teamName) {
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
