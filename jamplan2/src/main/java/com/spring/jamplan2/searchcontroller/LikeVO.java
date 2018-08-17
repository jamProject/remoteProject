package com.spring.jamplan2.searchcontroller;

import org.springframework.stereotype.Component;

@Component
public class LikeVO {
	private int planNo;
	private String userId;
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

	
	
}
