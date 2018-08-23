package com.spring.jamplan2.plan;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("planService")
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public ArrayList planTable(PlanTableVO planTableVO, PlanMapVO planMapVO){
		//질문. arraylist를 안써도 되지않나유?왜써주는건가유(노랑표시..)
		
		ArrayList<PlanTableVO> planTableList = null;
		ArrayList<PlanMapVO> planMapList = null;
		int i = 0;
		
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		planMapList = planMapper.getPlanMap(planMapVO);
		System.out.println("planMapList사이즈=" + planMapList.size());
		
		
		if(planMapList.size() != 0) {
			planTableList = planMapper.getplanTable(planTableVO);
			System.out.println("planTableList의존재=" + planTableList.size());
			
			/* CalendarVO 와 MapVO를 쓰는 형태 하지만 난 지은이의Map의 정보를 받아와서 뿌려주면됌 -> 테이블 2개사용해도 되겠다.
			 * //여러개 - for문
			String getCalendar = planCalendarList.get(0).getCalendar();
			System.out.println("Calendar에 들어왔니?!!!!!!!!" + getCalendar);
			planTableVO.setCalendar(getCalendar);
			System.out.println("planTable에 들어왔니??!!!" + planTableVO.getCalendar());
			
			String getMap = planMapList.get(0).getMap();
			planTableVO.setMap(getMap);
			-----------------------------------------------------------
			for(PlanCalendarVO planCalendar : planCalendarList) {
				for(PlanTableVO planTable : planTableList) {
					향상된 for문 사용해보기.
				}
			}*/
			
			
			if(planTableList.size() == 0) {
				System.out.println("플랜테이블이0일때 들어오고 둘다 null이아닐때 들어왔다!!!!");
				for (i=0; i < planMapList.size(); i++) {
					String getCalendar = planMapList.get(i).getCalendar();
					planTableVO.setCalendar(getCalendar);
					String getMap = planMapList.get(i).getMap();
					planTableVO.setMap(getMap);
					
					planMapper.insertPlanTable(planTableVO);
				}
				/*System.out.println("set한planTable insert!!");*/
				
				return planTableList;
			}
			else {
				return planTableList;
			}
			
		}
		else {
			System.out.println("다른 경우일때 들어왓다@@@@@@");
			return null;
		}
		
	}
	
	/*@Override
	public ArrayList planTable(PlanTableVO planTableVO, PlanCalendarVO planCalendarVO, PlanMapVO planMapVO){
		//질문. arraylist를 안써도 되지않나유?
		ArrayList<PlanTableVO> planTableList = null;
		ArrayList<PlanCalendarVO> planCalendarList = null;
		ArrayList<PlanMapVO> planMapList = null;
		int i = 0;
		//하나의 오버라이드에서 여러가지 객체를 파라미터로 다룰수있나?? 
		//날짜테이블의 정보를 불러와 있는지없는지 체크함과동시에 지도도 체크가가능??
	
		System.out.println("캘린더확인:" + planCalendarVO.getCalendar());
		
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);

		planCalendarList = planMapper.getPlanCalendar(planCalendarVO);
		planMapList = planMapper.getPlanMap(planMapVO);
		
		System.out.println("planCalendarList사이즈=" + planCalendarList.size());
		System.out.println("planMapList사이즈=" + planMapList.size());
		
		
		
		if(planCalendarList.size() != 0 && planMapList.size() != 0) {
			planTableList = planMapper.getplanTable(planTableVO);
			System.out.println("planTableList의존재=" + planTableList.size());
			if(planTableList.size() == 0) {
				System.out.println("플랜테이블이0일때 들어오고 둘다 null이아닐때 들어왔다!!!!");
				//두테이블에 값이 있을경우 플랜테이블에 값들을 넣어주는과정
				planMapper.insertPlanCalendar(planCalendarVO);
				System.out.println("planC=insert");
				
				for (i = 0; i<planMapList.size(); i++) {
					
				}
				
				
				
				planMapper.updatePlanMap(planMapVO);
				System.out.println("planM=update");
				return planTableList;
			}
			else {
				return planTableList;
			}
			
			문제(골머리썩혔던)똑같은 planNo일때 딜리트? -> if문으로  planTableList가 (번호planNo에대한)있으면 (!=null)안넣고, 없으면 셀렉
			=> 먼저 내 테이블을 조회후 인서트해줬어야함.. 난 인서트부터 시킨후 생각해서 너무 복잡하게생각하게됐었음 
			
			
		}
		else {
			System.out.println("다른 경우일때 들어왓다@@@@@@");
			return null;
		}
		
		
		
		calendarList = planMapper.getPlanCalendar(planCalendarVO);
		mapList = planMapper.getPlanMap(planMapVO.);
		
		첫테스트한것
		 	if(planTableList.size() != 0) {
			System.out.println("0이 아닐때 들어왔다@@@@@");
			
			
		}
		else {
			System.out.println("0일때 들어왔다!!!!");
			planMapper.insertPlanTable(planTableVO);
			System.out.println("test");
			
		}
		
		
		
		
	}*/
	
	@Override
	public ArrayList savePlanTable (PlanTableVO planTableVO) {
		System.out.println("save들어왔니?");
		ArrayList<PlanTableVO> checkList = null;
		int i = 0;
		
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		
		checkList = planMapper.getplanTable(planTableVO);
		System.out.println("checkList:" + checkList.get(0).getMap());
		
		System.out.println("checkList사이즈="+checkList.size());
		
		//for문 돌리면안됌..
		for (i=0; i < checkList.size(); i++) {
			String getMap = checkList.get(i).getMap();
			planTableVO.setMap(getMap);
			planMapper.savePlanTable(planTableVO);
		}
		System.out.println("for문");
		
		planMapper.savePlanTable(planTableVO);
		System.out.println("mapper연결되고나옴");
		
		return checkList;
		
		
		
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
