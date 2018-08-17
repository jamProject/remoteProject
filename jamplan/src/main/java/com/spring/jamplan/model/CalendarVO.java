package com.spring.jamplan.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

<<<<<<< HEAD
/**
 * @author wookim
 * @param
 * 
=======

/**
 * @author wookim
 * @param 
 *	
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
 */
@Component
public class CalendarVO {
	String id;
	int planNo;
	String selectDate;
<<<<<<< HEAD
	int dateCount;
	int confirmIndicator;	
	
=======
	String selectMonth;
	int dateCount;
	int confirmIndicator;
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
	
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
<<<<<<< HEAD
=======
	public String getSelectMonth() {
		return selectMonth;
	}
	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
	}
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
	public int getDateCount() {
		return dateCount;
	}
	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}
<<<<<<< HEAD
	public int getConfirmIndicator() {
		return confirmIndicator;
	}
	public void setConfirmIndicator(int confirmIndicator) {
		this.confirmIndicator = confirmIndicator;
	}
=======
	public int getConfirmed() {
		return confirmIndicator;
	}
	public void setConfirmed(int confirmed) {
		this.confirmIndicator = confirmed;
	}
	

>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
}
