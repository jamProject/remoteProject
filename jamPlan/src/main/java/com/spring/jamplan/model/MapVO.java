package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class MapVO {
	private int planNo;
	private int pickCount;
	private String id;
	private String userColor;
	private String location;
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getPickCount() {
		return pickCount;
	}
	public void setPickCount(int pickCount) {
		this.pickCount = pickCount;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
