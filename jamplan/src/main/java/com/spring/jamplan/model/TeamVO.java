package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
<<<<<<< HEAD
@Component
public class TeamVO {
	int teamNo;
	int planNo;
	
	String id;
	String teamName;
	int role;


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
=======

@Component
public class TeamVO {
	private String id;
	private String role;
	private String teamName;
	
	private int teamNo;
	private int planNo;
>>>>>>> 6ddd09edfe1ad83f2b818f9ba9b95e094714e95d

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

<<<<<<< HEAD
=======
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

>>>>>>> 6ddd09edfe1ad83f2b818f9ba9b95e094714e95d
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
=======
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
>>>>>>> 6ddd09edfe1ad83f2b818f9ba9b95e094714e95d
	}

}
