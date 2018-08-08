package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;

@Controller
public class ManagePlanController {

	@Autowired(required = true)
	PlanVO planVO;

	@Autowired(required = true)
	ManagePlanDAOService mpDAOS;
	
	//@Autowired
	//CalendarDAOService calDAOS;

	private ArrayList<PlanVO> planList;

	@RequestMapping("main.manageplan")
	public String mainLoad() {
		return "managePlan/planMainPage";
	}

	@RequestMapping(value = "calendar.manageplan", method = RequestMethod.GET)
	public String calendarLoad() {
		
		return "managePlan/calendarPage";
	}
	
	@RequestMapping(value = "selectCalendar.manageplan", method = RequestMethod.POST)
	@ResponseBody
	public void calendarSelect(HttpSession session, CalendarVO vo) {
		vo.setId((String)session.getAttribute("id"));
		vo.setPlanNo((int)session.getAttribute("planNo"));
		mpDAOS.insertSelectDate(vo);
		
	}
	
	@RequestMapping(value = "loadCalendar.manageplan", method = RequestMethod.POST)
	@ResponseBody
	public String calendarLoadDate(HttpSession session) {
		ArrayList<CalendarVO> calVO = mpDAOS.getSelectDate((int)session.getAttribute("planNo"));
		ObjectMapper mapper = new ObjectMapper();//json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str="";
		try {
			str = mapper.writeValueAsString(calVO);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("castring fail");
		}
		
		return str;
	}
	
	@RequestMapping(value = "select.manageplan", method = RequestMethod.POST, produces ="application/json;charset=UTF-8")
	public void selectDate() {
		
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
