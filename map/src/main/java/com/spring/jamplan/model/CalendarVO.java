package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class CalendarVO {
	int planNo;
	int selectDate;
	String id;
	int dateCount;
	int confirmIndicator;
	
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(int selectDate) {
		this.selectDate = selectDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDateCount() {
		return dateCount;
	}
	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}
	public int getConfirmIndicator() {
		return confirmIndicator;
	}
	public void setConfirmIndicator(int confirmIndicator) {
		this.confirmIndicator = confirmIndicator;
	} 
}
