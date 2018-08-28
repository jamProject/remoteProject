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
import com.spring.jamplan.model.UserVO;


@Controller
public class ManagePlanController {

	@Autowired(required = false)
	private PlanVO planVO;

	@Autowired(required = false)
	private TeamInfoVO teamVO;

	@Autowired(required = false)	
	private ManagePlanDAOService mpDAOS;
	
	@Autowired(required = false) 
	private ChatDAOService chatDAO;

	private ArrayList<PlanVO> planList;
	private HashMap<String, Object> map;
	private ObjectMapper mapper;

	
//	@RequestMapping(value = "/main.mp")
//	public String mainLoad() {
//		
//		
//		return "managePlan/main";
//	}
	
	// 웹소켓 테스트를 위한 부분
	@RequestMapping(value = "/main.mp")
	public String mainLoad(HttpSession session, UserVO user) {
		
		session.setAttribute("id", user.getId());
		
		return "managePlan/main";
	}

	@RequestMapping(value = "/selectCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> calendarSelect(HttpSession session, CalendarVO vo) {
		vo.setId((String) session.getAttribute("id"));
		vo.setPlanNo((int) session.getAttribute("planNo"));
		mpDAOS.insertSelectDate(vo);

		map = new HashMap<String, Object>();
		map.put("res", "ok");
		return map;
	}
	
	@RequestMapping(value = "/getMemberId.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
	@RequestMapping(value = "/loadCalendar.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
	@RequestMapping(value = "/fixcal.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
	
	@RequestMapping(value = "/calendar.mp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String calendarLoad(HttpSession session) {
		System.out.println("실행됨");
		
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		
		teamVO = mpDAOS.getPlanRole(map);
		session.setAttribute("role", teamVO.getRole());
		return "managePlan/calendarPage";
	}
	
	@RequestMapping(value = "/calendarajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String calendarAjax(HttpSession session) {

		map = new HashMap<String, Object>();
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
	
	@RequestMapping(value = "/mapajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

	@RequestMapping(value = "/map.mp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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
	
	@RequestMapping(value = "/plantableajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

	@RequestMapping(value="/plantable.mp",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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

	@RequestMapping(value = "/viewallajax.mp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
	
	@RequestMapping(value="/viewall.mp", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
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
	
	
	// 접속 중인 유저들의 프로필 사진명을 얻기 위한 메서드
	@RequestMapping(value="/onUserList.mp", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody 
	public String getOnUserList(String[] nameList, UserVO user) {
		System.out.println("getOnUserList IN");
		System.out.println(nameList[2]);
		Map<String, String> imageMap = new HashMap<String, String>();
		
		for(int i=1; i<nameList.length; i++) {
			String name = nameList[i];
			System.out.println(name);
			System.out.println("들어갔니");
			user.setId(name);
			System.out.println("user에 set은 잘했니");
			user = chatDAO.getImageName(user);
			System.out.println("DB엔 잘갔다왔고?");
			imageMap.put(name, user.getImage());
			System.out.println(name);
			System.out.println(user.getImage());
			System.out.println("이제 map에 다 넣었지?");
		}
		
		mapper = new ObjectMapper();// json형식으로 데이터를 반환하기 위해 사용(pom.xml 편집)
		String imageList = "";
		try {
			System.out.println("imageList 뽑기 전까지는 왔네");
			imageList = mapper.writeValueAsString(imageMap);
			System.out.println(imageList);
		} catch (Exception e) {
			e.getMessage();
		}
		
		System.out.println("getOnUserList OUT");
		return imageList;
	}

}