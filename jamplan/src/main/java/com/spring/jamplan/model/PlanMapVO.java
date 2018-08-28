package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanMapVO {
   private int planNo;
   private String planDate;
   private String placeName;
   private String id;
   private int pickCount;
   private String userColor;
   private int lat;
   private int lng;
   private String address;
   private int confirm;
   
public int getPlanNo() {
	return planNo;
}
public void setPlanNo(int planNo) {
	this.planNo = planNo;
}
public String getPlanDate() {
	return planDate;
}
public void setPlanDate(String planDate) {
	this.planDate = planDate;
}
public String getPlaceName() {
	return placeName;
}
public void setPlaceName(String placeName) {
	this.placeName = placeName;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getPickCount() {
	return pickCount;
}
public void setPickCount(int pickCount) {
	this.pickCount = pickCount;
}
public String getUserColor() {
	return userColor;
}
public void setUserColor(String userColor) {
	this.userColor = userColor;
}
public int getLat() {
	return lat;
}
public void setLat(int lat) {
	this.lat = lat;
}
public int getLng() {
	return lng;
}
public void setLng(int lng) {
	this.lng = lng;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getConfirm() {
	return confirm;
}
public void setConfirm(int confirm) {
	this.confirm = confirm;
}
   
   
   
   
   
   
   
   
   
   
   
   
}