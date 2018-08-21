package com.spring.jamplan.admin;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface AdminDAO {
	
	public ArrayList<UserVO> getUserList();
	public ArrayList<TeamVO> getTeamList();	
	public ArrayList<PlanVO> getPlanList(String planName);			//전체 조회
	public UserVO getUser(String id);
	public TeamVO getTeam(String teamName);			//검색
	//public PlanVO getPlan(String planName);		
	public void deleteUser(String id);
	public void deleteTeam(String teamName);
	public void deletePlan(int planNo);				//삭제
	
	//보류
	public int sendMessage(String id);				
	public int sendMessage2(String teamName);
	public int getCount();
}
