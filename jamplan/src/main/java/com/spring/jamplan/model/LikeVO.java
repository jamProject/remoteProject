package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class LikeVO {
	private int likeNo;
	private int likeCheck;
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public int getLikeCheck() {
		return likeCheck;
	}
	public void setLikeCheck(int likeCheck) {
		this.likeCheck = likeCheck;
	}
	
	
	
}