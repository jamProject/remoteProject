package com.spring.jamplan2.plan;

import java.util.ArrayList;



public interface PlanService {
	ArrayList planTable(PlanTableVO planTableVO, PlanMapVO planMapVO);
	ArrayList savePlanTable (PlanTableVO planTableVO);
}
