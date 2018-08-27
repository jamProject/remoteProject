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
import com.spring.jamplan.model.TeamInfoVO;


@Controller
public class ManagePlanController {

	@Autowired(required = true)
	private PlanVO planVO;

	@Autowired(required = true)
	private TeamInfoVO teamVO;

	@Autowired(required = true)	
	private ManagePlanDAOService mpDAOS;

	private ArrayList<PlanVO> planList;
	private HashMap<String, Object> map;
	private ObjectMapper mapper;

	
	@RequestMapping(value = "main.mp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String mainLoad() {
		return "managePlan/main";
	}

	@RequestMapping(value = "selectCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> calendarSelect(HttpSession session, CalendarVO vo) {
		vo.setId((String) session.getAttribute("id"));
		vo.setPlanNo((int) session.getAttribute("planNo"));
		mpDAOS.insertSelectDate(vo);

		map = new HashMap<String, Object>();
		map.put("res", "ok");
		return map;
	}
	
	@RequestMapping(value = "getMemberId.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String getMemberId(HttpSession session) {
		int planNo = (int)session.getAttribute("planNo");
		ArrayList<CalendarVO> voList = mpDAOS.getMemberId(planNo);
		
		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(voList);
			System.out.println("확인 : "+voList.get(0).getId());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("castring fail");
		}
		return str;
		
	}

	// 선택한 날짜 디비에서 불러오는 컨트롤러 json으로 해당 데이터 보내기
	@RequestMapping(value = "loadCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String calendarLoadDate(HttpSession session) {
		ArrayList<CalendarVO> calVO = mpDAOS.getSelectDate((int) session.getAttribute("planNo"));
		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
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
		map = new HashMap<String, Object>();
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
	
	@RequestMapping(value = "calendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String calendarLoad(HttpSession session) {
		System.out.println("실행됨");
		
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		int role = (int) session.getAttribute("role");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		map.put("role",role);
		map.put("link", "managePlan/calendarPage");
		//teamVO = mpDAOS.getPlanRole(map);
		//session.setAttribute("role", teamVO.getRole());
		
		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("castring fail");
		}
		return str;
		//return "managePlan/calendarPage";
	}
	
	@RequestMapping(value = "calendarajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String calendarAjax(HttpSession session) {
	/*	String id = (String) session.getAttribute("id");
		int planNo= (int)session.getAttribute("planNo");
		int role = (int) session.getAttribute("role");*/
		//int planNo = Integer.parseInt(planno);
		
		map = new HashMap<String, Object>();
		
		//teamVO = mpDAOS.getPlanRole(map);
		//session.setAttribute("role", teamVO.getRole());
		//map.put("link", "calendar.mp");
		map.put("link", "calendar.mp");
		
		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("castring fail");
		}
		return str;
	}
	
	@RequestMapping(value = "mapajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String mapAjax(HttpSession session) {
		
		map = new HashMap<String, Object>();
		map.put("link", "map.mp");

		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("castring fail");
		}
		return str;
	}

	@RequestMapping(value = "map.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String mapLoad(HttpSession session) {

		System.out.println("실행됨");
		
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		
		teamVO = mpDAOS.getPlanRole(map);
		session.setAttribute("role", teamVO.getRole());

		return "managePlan/mapPage";
	}
	
	@RequestMapping(value = "plantableajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String planTableAjax(HttpSession session) {
		map = new HashMap<String, Object>();
		map.put("link", "plantable.mp");

		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("castring fail");
		}
		return str;
	}

	@RequestMapping(value="plantable.mp",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String planTableLoad(HttpSession session) {

		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		
		teamVO = mpDAOS.getPlanRole(map);
		session.setAttribute("role", teamVO.getRole());
		return "managePlan/planTablePage";
	}

	@RequestMapping(value = "viewallajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String viewAllAjax(HttpSession session) {
		map = new HashMap<String, Object>();
		map.put("link", "viewall.mp");

		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String str = "";
		try {
			str = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("castring fail");
		}
		return str;
	}
	
	@RequestMapping(value="viewall.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String viewAllLoad(HttpSession session) {
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		
		teamVO = mpDAOS.getPlanRole(map);
		session.setAttribute("role", teamVO.getRole());
		
		return "managePlan/viewAllPage";
	}

}
