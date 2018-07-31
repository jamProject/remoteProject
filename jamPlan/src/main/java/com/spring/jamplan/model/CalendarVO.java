package com.spring.jamplan.model;

import org.springframework.stereotype.Component;


/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class CalendarVO {
	private int planNo;
	private int selectDate;
	private int dateCount;
	private boolean confirmIndicator;
	
	private String id;
	private String userColor;
	
	
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
	public int getDateCount() {
		return dateCount;
	}
	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}
	public boolean getConfirmIndicator() {
		return confirmIndicator;
	}
	public void setConfirmIndicator(boolean confirmIndicator) {
		this.confirmIndicator = confirmIndicator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserColor() {
		return userColor;
	}
	public void setUserColor(String userColor) {
		this.userColor = userColor;
	}
	
	
}
