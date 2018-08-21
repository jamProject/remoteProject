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
	public ArrayList<PlanTableVO> planTable(PlanTableVO planTableVO){
		ArrayList<PlanTableVO> planTableList = null;
		/*먼저 if문으로 경우calendar와 지은이map이있는지 체크한다 
		있으면 select문으로 가져와서 뿌려주고 (메모는 저장시에 업데이트되게)
		없으면 기본 폼양식을 뿌려줄 수 있게한다
		----
		카톡참고
		*/
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		planTableList = planMapper.planTable(planTableVO);
		
		if(planTableList.size() != 0) {
			System.out.println("0이 아닐때 들어왔다@@@@@");
			
			
		}
		else {
			System.out.println("0일때 들어왔다!!!!");
			planMapper.insertPlanTable(planTableVO);
			System.out.println("test");
			
		}
		
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
