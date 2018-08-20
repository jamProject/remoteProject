package com.spring.jamplan2.plan;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan2.searchcontroller.PlanVO;

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
	public String planTable(PlanTableVO planTableVO) {

		System.out.println("pt1");
		ArrayList<PlanTableVO> list = planService.planTable(planTableVO);
		System.out.println("pt2");
		
		String str = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(list);
		}
		catch (Exception e)
		{
			System.out.println("firest() mapper :" + e.getMessage());
		}
		
		System.out.println("str:" + str);
		return str;
		
	}
	
	
	
}
