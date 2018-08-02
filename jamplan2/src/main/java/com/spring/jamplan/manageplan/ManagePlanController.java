package com.spring.jamplan.manageplan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagePlanController {
	@RequestMapping("main.manageplan")
	public String mainLoad() {
		return "managePlan/planMainPage";
	}
	
	@RequestMapping("calendar.manageplan")
	public String calendarLoad() {
		return "managePlan/calendarPage";
	}
	
	@RequestMapping("map.manageplan")
	public String mapLoad() {
		return "managePlan/mapPage";
	}
	
	@RequestMapping("plantable.manageplan")
	public String planTableLoad() {
		return "managePlan/planTablePage";
	}
	
	@RequestMapping("viewall.manageplan")
	public String viewAllLoad() {
		return "managePlan/viewAllPage";
	}
}
