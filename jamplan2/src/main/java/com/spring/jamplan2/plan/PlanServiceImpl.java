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
		System.out.println("pt3");
		PlanMapper planMapper = sqlsession.getMapper(PlanMapper.class);
		planTableList = planMapper.planTable(planTableVO);
		System.out.println("planTableVO:" + planTableVO);
		return planTableList;
	}
}
