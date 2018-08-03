package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.jamplan.model.PlanVO;

@Controller
public class ManagePlanController {

	@Autowired(required = true)
	PlanVO planVO;

	@Autowired(required = true)
	ManagePlanDAOService mpDAOS;

	private ArrayList<PlanVO> planList;

	@RequestMapping("main.manageplan")
	public String mainLoad() {
		return "managePlan/planMainPage";
	}

	@RequestMapping("calendar.manageplan")
	public String calendarLoad(Model model, PlanVO planVO) {
		planList = mpDAOS.getTeamPlan(planVO);
		model.addAllAttributes(planList);

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
