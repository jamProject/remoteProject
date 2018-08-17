package com.spring.jamplan.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class TeamInfoVO {
	private int teamNo;
	private String teamName;
	private String id;
	private Date joinDate;
	
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
