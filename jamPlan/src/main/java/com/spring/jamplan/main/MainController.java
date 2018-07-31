package com.spring.jamplan.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*.main")
public class MainController {
	
	@RequestMapping("load.main")
	public String mainLoad(HttpServletRequest request) {
		System.out.println("test1");
		return "mainPage";
	}
}
