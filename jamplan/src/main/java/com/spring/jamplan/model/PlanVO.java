package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author wookim
 * @param 
<<<<<<< HEAD
 *	
*/

	
@Component
public class PlanVO {
   private int planNo;
   private int teamNo;
   private int goodCount;
   private int readCount;
   private int isOpen;
   private String image;
   private String planName;
   private String planDate;
   private String hashTag;
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
public int getIsOpen() {
	return isOpen;
}
public void setIsOpen(int isOpen) {
	this.isOpen = isOpen;
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
public String getHashTag() {
	return hashTag;
}
public void setHashTag(String hashTag) {
	this.hashTag = hashTag;
}
   

}
