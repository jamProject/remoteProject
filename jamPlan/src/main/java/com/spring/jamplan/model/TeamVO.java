package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class TeamVO {
	private String id;
	private boolean isTeamLeader;
	private String role;
	private String teamName;
	
	private int teamNo;
	private int planNo;
	
	private DateFormat chatTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsTeamLeader() {
		return isTeamLeader;
	}

	public void setIsTeamLeader(boolean isTeamLeader) {
		this.isTeamLeader = isTeamLeader;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public DateFormat getChatTime() {
		return chatTime;
	}

	public void setChatTime(DateFormat chatTime) {
		this.chatTime = chatTime;
	}
	

	
}
