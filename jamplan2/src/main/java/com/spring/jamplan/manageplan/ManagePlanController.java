package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;

@Controller
public class ManagePlanController {

	@Autowired(required = true)
	PlanVO planVO;

	@Autowired(required = true)
	TeamVO teamVO;

	@Autowired(required = true)
	ManagePlanDAOService mpDAOS;

	// @Autowired
	// CalendarDAOService calDAOS;

	private ArrayList<PlanVO> planList;

	@RequestMapping("main.mp")
	public String mainLoad() {
		return "managePlan/planMainPage";
	}

	@RequestMapping(value = "calendar.mp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String calendarLoad(HttpSession session) {
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);

		TeamVO vo = mpDAOS.getPlanRole(map);
		session.setAttribute("role", vo.getRole());

		return "managePlan/calendarPage";
	}

	@RequestMapping(value = "selectCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> calendarSelect(HttpSession session, CalendarVO vo) {
		System.out.println(vo.getSelectDate());
		vo.setId((String) session.getAttribute("id"));
		vo.setPlanNo((int) session.getAttribute("planNo"));
		mpDAOS.insertSelectDate(vo);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("res", "ok");
		return map;
	}

	// 선택한 날짜 디비에서 불러오는 컨트롤러 json으로 해당 데이터 보내기
	@RequestMapping(value = "loadCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String calendarLoadDate(HttpSession session) {
		ArrayList<CalendarVO> calVO = mpDAOS.getSelectDate((int) session.getAttribute("planNo"));
		ObjectMapper mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(calVO);
			//System.out.println("확인 : "+calVO.get(0).getConfirmed());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("castring fail");
		}
		return str;
	}
	//방장(혹은팀원)이 날짜를 확정 지으면 update문으로 해당 일정 업데이트 + 삽입
	@RequestMapping(value = "fixcal.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//json받을 떄 필수다 리스폰스 바디
	@ResponseBody
	public HashMap<String, Object> fixDate(HttpSession session, CalendarVO vo) {
		
		vo.setId((String)session.getAttribute("id"));
		vo.setPlanNo((int)session.getAttribute("planNo"));
		System.out.println("컨트롤러 select date : "+vo.getSelectDate());
		System.out.println("컨트롤러 실행");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			mpDAOS.getSelectDateFix(vo);
			map.put("res", "ok");
			System.out.println("fixCal Success");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("res", "fail");
			System.out.println("fixCal fail");
		}
		return map;
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