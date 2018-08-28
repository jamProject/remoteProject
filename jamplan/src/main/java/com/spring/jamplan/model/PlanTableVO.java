package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanTableVO {
   private int planNo;
   private int planSeq;
   private String calendar;
   private String map;
   private String memo;
   
   public int getPlanNo() {
      return planNo;
   }
   public void setPlanNo(int planNo) {
      this.planNo = planNo;
   }
   public int getPlanSeq() {
      return planSeq;
   }
   public void setPlanSeq(int planSeq) {
      this.planSeq = planSeq;
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
   public String getMemo() {
      return memo;
   }
   public void setMemo(String memo) {
      this.memo = memo;
   }
   
   
   
   
   
   
   
   
   
   
   
}