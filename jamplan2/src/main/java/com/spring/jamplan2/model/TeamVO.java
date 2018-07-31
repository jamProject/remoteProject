package com.spring.jamplan2.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class TeamVO {
	int teamNo;
	int planNo;
	
	String id;
	String teamName;
	String role;
	String isTeamLeader;

	DateFormat chatTime;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIsTeamLeader() {
		return isTeamLeader;
	}

	public void setIsTeamLeader(String isTeamLeader) {
		this.isTeamLeader = isTeamLeader;
	}

	public DateFormat getChatTime() {
		return chatTime;
	}

	public void setChatTime(DateFormat chatTime) {
		this.chatTime = chatTime;
	}
}
