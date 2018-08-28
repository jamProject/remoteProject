package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanMapVO {
   private int planNo;
   private String calendar;
   private String map;
   public int getPlanNo() {
      return planNo;
   }
   public void setPlanNo(int planNo) {
      this.planNo = planNo;
   }
   public String getCalendar() {
      return calendar;
   }
   public void setCalendar(String calendar) {
      this.calendar = calendar;
   }
   public String getMap() {
      return map;
   }
   public void setMap(String map) {
      this.map = map;
   }
   
   
   
   
   
   
   
   
   
}