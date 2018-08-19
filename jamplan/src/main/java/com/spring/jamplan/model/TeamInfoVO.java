package com.spring.jamplan.model;

import java.sql.Date;
import java.text.DateFormat;

import org.springframework.stereotype.Component;

@Component
public class TeamInfoVO {

	private int teamNo;
	private String teamName;
	private String id ; 
    private int  role;
    private int planNo;
    private String planName ;
    private DateFormat joinDate ;
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public DateFormat getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(DateFormat joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
