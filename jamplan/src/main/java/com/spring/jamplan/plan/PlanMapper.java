package com.spring.jamplan.plan;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanMapVO;
import com.spring.jamplan.model.PlanTableVO;

public interface PlanMapper {
   ArrayList<PlanTableVO> getplanTable(PlanTableVO planTableVO);
   /*ArrayList<PlanCalendarVO> getPlanCalendar(PlanCalendarVO planCalendarVO);*/
   ArrayList<PlanMapVO> getPlanMap(PlanMapVO planMapVO);

   void insertPlanTable(PlanTableVO planTableVO);
   
   
   int savePlanTable (PlanTableVO planTableVO);
   
}