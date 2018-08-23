package com.spring.jamplan2.plan;

import org.springframework.stereotype.Component;

@Component
public class PlanCalendarVO {
	private int planNo;
	private String calendar;
	
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
	
	
	
	
	
	
	
}
