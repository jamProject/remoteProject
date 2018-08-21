package com.spring.jamplan2.plan;

import java.util.ArrayList;



public interface PlanService {
	ArrayList planTable(PlanTableVO planTableVO, PlanCalendarVO planCalendarVO, PlanMapVO planMapVO);
	int savePlanTable (PlanTableVO planTableVO);
}
