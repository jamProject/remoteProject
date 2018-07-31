package com.spring.jamplan.admin;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface AdminDAO {
	
	public ArrayList<TeamVO> getTeamList(String teamName);	
	public ArrayList<PlanVO> getPlanList(String planName);
	public ArrayList<UserVO> getUserList(String id);
	public int deleteUser(String id);
	public int deletePlan(int planNo);
	public int deleteTeam(int teamNo);
	
	public int sendMessage(String id);		//보류
	public int sendMessage2(String teamName);
	
	
}
