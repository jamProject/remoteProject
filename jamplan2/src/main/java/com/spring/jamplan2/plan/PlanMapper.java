package com.spring.jamplan2.plan;

import java.util.ArrayList;

public interface PlanMapper {
	ArrayList<PlanTableVO> getplanTable(PlanTableVO planTableVO);
	/*ArrayList<PlanCalendarVO> getPlanCalendar(PlanCalendarVO planCalendarVO);*/
	ArrayList<PlanMapVO> getPlanMap(PlanMapVO planMapVO);

	void insertPlanTable(PlanTableVO planTableVO);
	
	
	int savePlanTable (PlanTableVO planTableVO);
	
}
