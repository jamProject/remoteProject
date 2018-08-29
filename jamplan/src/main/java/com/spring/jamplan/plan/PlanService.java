package com.spring.jamplan.plan;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanMapVO;
import com.spring.jamplan.model.PlanTableVO;



public interface PlanService {
   ArrayList planTable(PlanTableVO planTableVO, PlanMapVO planMapVO);
   ArrayList savePlanTable (PlanTableVO planTableVO);
}