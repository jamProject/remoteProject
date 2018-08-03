package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;

@Service
public class ManagePlanDAOService {

	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스

	private CalendarMapper calendarMapper;
	private ArrayList<PlanVO> planList;

	public ArrayList<PlanVO> getPlans(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getAllPlans();

		return planList;
	}

	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getTeamPlan(planVO);

		return planList;
	}
}
