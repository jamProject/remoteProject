package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class MapVO {
	int planNo;
	int mapCount;
	String id;
	String userColor;
	String location;
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getMapCount() {
		return mapCount;
	}
	public void setMapCount(int mapCount) {
		this.mapCount = mapCount;
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
