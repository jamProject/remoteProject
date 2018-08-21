package com.spring.jamplan2.plan;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("planService")
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private SqlSession sqlsession;
	
	
	@Autowired
	private PlanCalendarVO planCalendarVO;
	
	@Autowired
	private PlanMapVO planMapVO;
	
	
	@Override
	public ArrayList planTable(PlanTableVO planTableVO, PlanCalendarVO planCalendarVO, PlanMapVO planMapVO){
		ArrayList<PlanTableVO> planTableList = null;
		ArrayList<PlanCalendarVO> planCalendarList = null;
		ArrayList<PlanMapVO> planMapList = null;
		//하나의 오버라이드에서 여러가지 객체를 파라미터로 다룰수있나?? 
		//날짜테이블의 정보를 불러와 있는지없는지 체크함과동시에 지도도 체크가가능??
		
		/*System.out.println("캘린더확인:" + planCalendarVO.getCalendar());*/
 		
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		
		planCalendarList = planMapper.getPlanCalendar(planCalendarVO);
		System.out.println("11");
		System.out.println("planCalendarList : " + planCalendarList);
		
		/*calendarList = planMapper.getPlanCalendar(planCalendarVO);
		mapList = planMapper.getPlanMap(planMapVO.);*/
		
		/*첫테스트한것
		 	if(planTableList.size() != 0) {
			System.out.println("0이 아닐때 들어왔다@@@@@");
			
			
		}
		else {
			System.out.println("0일때 들어왔다!!!!");
			planMapper.insertPlanTable(planTableVO);
			System.out.println("test");
			
		}*/
		
		return planTableList;
		
		
	}
	
	@Override
	public int savePlanTable (PlanTableVO planTableVO) {
		System.out.println("save들어왔니?");
		ArrayList<PlanTableVO> checkList = null;
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		
		checkList = planMapper.planTable(planTableVO);
		System.out.println("checkList:" + checkList.get(0));
		
		int list = planMapper.savePlanTable(planTableVO);
		
		return list;
		
		
		/*if(checkList.get(0) == null) {
			System.out.println("null일때 들어왔다!!!!!");
			//추가(insert)
			int list = planMapper.insertPlanTable(planTableVO);
			return list;
		}
		else {
			System.out.println("null이 아닐때 들어왔다@@@@@@@@");
			//업데이트
			int list = planMapper.savePlanTable(planTableVO);
			return list;
		}*/
		
		
		
	}
}
