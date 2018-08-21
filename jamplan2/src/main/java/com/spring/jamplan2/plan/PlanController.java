package com.spring.jamplan2.plan;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PlanController {

	
	@Autowired
	private PlanService planService;
	
	//메인에서 team방입장 테스트
	@RequestMapping("planMainPage.plan")
	public String movePlanMainPage() {
		return "plan/planMainPage";
	}
	
	//플랜테이블
	@RequestMapping(value = "planTable.plan", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String planTable(PlanTableVO planTableVO, PlanCalendarVO planCalendarVO, PlanMapVO planMapVO) {

		System.out.println("pt1");
		ArrayList planList = planService.planTable(planTableVO,planCalendarVO,planMapVO);
		/*ArrayList<PlanTableVO> calendarList = planService.planCalendar(planCalendarVO);
		ArrayList<PlanTableVO> mapList = planService.planMap(planMapVO);*/
		System.out.println("pt2");
		
		String str = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(planList);
		}
		catch (Exception e)
		{
			System.out.println("firest() mapper :" + e.getMessage());
		}
		
		System.out.println("str:" + str);
		return str;
		
	}
	
	//savePlanTable : 저장
	@RequestMapping(value = "savePlanTable.plan", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public int savePlanTable (PlanTableVO planTableVO) {
		System.out.println("memo" + planTableVO.getMemo());
		System.out.println("save1");
		int list = planService.savePlanTable(planTableVO);
		System.out.println("save4" + list);
		
		return list;
		
	}
	
	
	
}
