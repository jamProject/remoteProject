package com.spring.jamplan2.plan;

import org.springframework.stereotype.Component;

@Component
public class PlanTableVO {
	private String calendar;
	private String map;
	
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
	
	
}
