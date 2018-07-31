package com.spring.jamplan.myroom;

import org.springframework.util.StringUtils;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.model.PlanVO;

public class MyRoomSQL {	
	
	public static final String GET_TEAM_LIST = "SELECT * FROM TEAM";
	public static final String GET_PLAN_LIST = "SELECT * FROM PLAN";
	public static final String GET_CONFIRMED_DATE = "SELECT SELECTDATE FROM CALENDAR";
	public static final String GET_USER_INFO = "SELECT * FROM USERINFO";
	public static final String GET_MESSAGE_LIST = "SELECT * FROM MESSAGE";
	public static final String SEARCH_TEAM = "SELECT * FROM TEAM";
	public static final String MAKE_TEAM = "INSERT INTO TEAM(ID, TEAMNO, ISTEAMLEADER, ROLE, TEAMNAME)";
	public static final String MAKE_PLAN = "INSERT INTO PLAN(PLANNO, TEAMNO, PLANNAME, PLANDATE, ISOPEN)";
	public static final String DELETE_TEAM = "DELETE FROM TEAM";
	public static final String DELETE_PLAN = "DELETE FROM PLAN";
	
	public String getTeamList(UserVO user) {
		
		StringBuilder query = new StringBuilder();

		query.append(GET_TEAM_LIST);
		if(!StringUtils.isEmpty(user)) {
			query.append("where id = " + user.getId());
		}
		return query.toString();
	}
	
	public String getPlanList(UserVO user) {
	
		StringBuilder query = new StringBuilder();
		
		query.append(GET_PLAN_LIST);
		if(!StringUtils.isEmpty(user)) {
			query.append("where teamNo = (select teamNo from team where id = " + user.getId() + ")");
		}
		return query.toString();
	}
	
	public String getConfirmedDate(CalendarVO calendar) {
		StringBuilder query = new StringBuilder();
		
		query.append(GET_CONFIRMED_DATE);
		if(!StringUtils.isEmpty(calendar)) {
			query.append("where id="+ calendar.getId() + 
					", planNo=" + calendar.getPlanNo() + 
					", confirmIndicator=" + calendar.getConfirmIndicator());
		}
		return query.toString();
	}
	
	public String getUserInfo(UserVO user) {
		StringBuilder query = new StringBuilder();
		
		query.append(GET_USER_INFO);
		if(!StringUtils.isEmpty(user)) {
			query.append("where id=" + user.getId());
		}
		return query.toString();
	}
	
	public String getMessageList(UserVO user) {
		StringBuilder query = new StringBuilder();
		
		query.append(GET_MESSAGE_LIST);
		if(!StringUtils.isEmpty(user)) {
			query.append("where id=" + user.getId());
		}
		return query.toString();
	}
	
	public String searchTeam(TeamVO team) {
		StringBuilder query = new StringBuilder();
		
		query.append(GET_MESSAGE_LIST);
		if(!StringUtils.isEmpty(team)) {
			query.append("where teamName=" + team.getTeamName());
		}
		return query.toString();
	}
	
	public String makeTeam(TeamVO team) {
		StringBuilder query = new StringBuilder();
		
		query.append(MAKE_TEAM);
		if(!StringUtils.isEmpty(team)) {
			query.append(" values(" + team.getId() + ", " + team.getTeamNo() +
					", " + team.getIsTeamLeader() + ", " + team.getRole() + ", " +
						team.getTeamName() + ")");
		}
		return query.toString();
	}
	
	public String makePlan(PlanVO plan) {
		StringBuilder query = new StringBuilder();
		
		query.append(MAKE_PLAN);
		if(!StringUtils.isEmpty(plan)) {
			query.append(" values(" + plan.getPlanNo() + ", " + plan.getTeamNo() +
					", " + plan.getPlanName() + ", " + plan.getPlanDate() + ", " +
						plan.getIsOpen() + ")");
		}
		return query.toString();
	}
	
	public String deleteTeam(TeamVO team) {
		StringBuilder query = new StringBuilder();
		
		query.append(DELETE_TEAM);
		if(!StringUtils.isEmpty(team)) {
			query.append("where teamName=" + team.getTeamName());
		}
		return query.toString();
	}
	
	public String deletePlan(PlanVO plan) {
		StringBuilder query = new StringBuilder();
		
		query.append(DELETE_PLAN);
		if(!StringUtils.isEmpty(plan)) {
			query.append("where planNo=" + plan.getPlanNo());
		}
		return query.toString();
	}
}
