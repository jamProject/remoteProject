package com.spring.jamplan.model;

import java.sql.Date;

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
<<<<<<< HEAD
	int role;

=======
	String role;
	String isTeamLeader;
	Date chatTime;
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165

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

<<<<<<< HEAD
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

=======
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

	public Date getChatTime() {
		return chatTime;
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
}
