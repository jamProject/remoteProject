package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	@RequestMapping("main.mp")
	public String mainLoad() {
		return "managePlan/planMainPage";
	}

	@RequestMapping(value = "calendar.mp", method = RequestMethod.GET)
	public String calendarLoad() {
		return "managePlan/calendarPage";
	}
	
	@RequestMapping(value = "selectCalendar.mp", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> calendarSelect(HttpSession session, CalendarVO vo) {
		System.out.println(vo.getSelectDate());
		vo.setId((String)session.getAttribute("id"));
		vo.setPlanNo((int)session.getAttribute("planNo"));
		mpDAOS.insertSelectDate(vo);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("res", "ok");
		return map;
	}
	//선택한 날짜 디비에서 불러오는 컨트롤러 json으로 해당 데이터 보내기
	@RequestMapping(value = "loadCalendar.mp", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "select.mp", method = RequestMethod.POST, produces ="application/json;charset=UTF-8")
	public void selectDate() {
		
	}

	@RequestMapping("map.mp")
	public String mapLoad() {
		return "managePlan/mapPage";
	}

	@RequestMapping("plantable.mp")
	public String planTableLoad() {
		return "managePlan/planTablePage";
	}

	@RequestMapping("viewall.mp")
	public String viewAllLoad() {
		return "managePlan/viewAllPage";
	}
}
