package com.spring.jamplan.model;

import java.sql.Date;

import org.springframework.stereotype.Component;


/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class CalendarVO {
	String id;
	int planNo;
	String selectDate;
	int dateCount;
	int confirmIndicator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	public int getDateCount() {
		return dateCount;
	}
	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}
	public int getConfirmed() {
		return confirmIndicator;
	}
	public void setConfirmed(int confirmed) {
		this.confirmIndicator = confirmed;
	}
	

}
