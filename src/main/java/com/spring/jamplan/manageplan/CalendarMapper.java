package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;

public interface CalendarMapper {
	public ArrayList<PlanVO> getAllPlans();
	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO);
}
