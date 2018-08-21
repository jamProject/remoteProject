package com.spring.jamplan2.plan;

import java.util.ArrayList;

public interface PlanMapper {
	ArrayList<PlanTableVO> planTable(PlanTableVO planTableVO);
	ArrayList<PlanCalendarVO> getPlanCalendar(PlanCalendarVO planCalendarVO);
	void insertPlanTable (PlanTableVO planTableVO);
	int savePlanTable (PlanTableVO planTableVO);
}
