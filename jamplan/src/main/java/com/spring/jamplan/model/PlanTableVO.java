package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanTableVO {
   private int planNo;
   private int planSeq;
   private String planDate;
   private String placeName;
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
public String getPlanDate() {
	return planDate;
}
public void setPlanDate(String planDate) {
	this.planDate = planDate;
}
public String getPlaceName() {
	return placeName;
}
public void setPlaceName(String placeName) {
	this.placeName = placeName;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
   
   
   
   
   
   
   
   
   
   
   
   
   
}