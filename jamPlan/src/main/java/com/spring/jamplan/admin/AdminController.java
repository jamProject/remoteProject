package com.spring.jamplan.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*.admin")
public class AdminController {
	
	@RequestMapping("load.admin")
	public String adminLoad(HttpServletRequest request) {
		return "admin/adminPage";
	}
}
