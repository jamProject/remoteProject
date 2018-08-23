package com.spring.jamplan2.plan;

import org.springframework.stereotype.Component;

@Component
public class PlanTableVO {
	private int planNo;
	private String calendar;
	private String map;
	private String memo;
	
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
	
	
	
	
	
}
