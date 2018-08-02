package com.spring.jamplan.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@RequestMapping("/admin.jam")
	public String adminLoad(HttpServletRequest request) {
		return "admin/adminPage";
	}
	
	@RequestMapping("/adminUserList.jam")
	public ModelAndView adminUserList(String id) {
		return null;
	}
	
	@RequestMapping("/adminPlanList.jam")
	public ModelAndView adminPlanList(String planName) {
		return null;
	}

	@RequestMapping("/adminTeamList.jam")
	public ModelAndView adminTeamList(String teamName) {
		return null;
	}
	
	@RequestMapping("/adminDeleteUser.jam")
	public ModelAndView adminDeleteUser(String id) {
		return null;
	}
	
	@RequestMapping("/adminDeletePlan.jam")
	public ModelAndView adminDeletePlan(String planName) {
		return null;
	}
	
	@RequestMapping("/adminDeleteTeam.jam")
	public ModelAndView adminDeleteTeam(String teamName) {
		return null;
	}
}
