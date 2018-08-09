package com.spring.jamplan2.searchcontroller;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *   
 */
@Component
public class PlanVO {
   private int planNo;
   private int teamNo;
   private int goodCount;
   private int readCount;
   
   private String image;
   private String planName;
   private String planDate;
   
public int getPlanNo() {
	return planNo;
}
public void setPlanNo(int planNo) {
	this.planNo = planNo;
}
public int getTeamNo() {
	return teamNo;
}
public void setTeamNo(int teamNo) {
	this.teamNo = teamNo;
}
public int getGoodCount() {
	return goodCount;
}
public void setGoodCount(int goodCount) {
	this.goodCount = goodCount;
}
public int getReadCount() {
	return readCount;
}
public void setReadCount(int readCount) {
	this.readCount = readCount;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getPlanName() {
	return planName;
}
public void setPlanName(String planName) {
	this.planName = planName;
}
public String getPlanDate() {
	return planDate;
}
public void setPlanDate(String planDate) {
	this.planDate = planDate;
}

   // private int isOpen;
   //private String hashTag;

}