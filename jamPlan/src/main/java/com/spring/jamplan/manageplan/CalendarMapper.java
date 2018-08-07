package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;

public interface CalendarMapper {
	
	public ArrayList<PlanVO> getPlan();
	public PlanVO getUserPlan(int planNo);
}