package com.spring.jamplan.admin;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface AdminMapper {
	
	ArrayList<UserVO> getUserList();
	ArrayList<TeamVO> getTeamList();	
	ArrayList<PlanVO> getPlanList(String planName);			//전체 조회
	UserVO getUser(String id);
	TeamVO getTeam(String teamName);	
	/*PlanVO getPlan(String planName);  일정이름은 중복가능*/			//검색
	void deleteUser(String id);
	void deleteTeam(int teamName);
	void deletePlan(int planNo);				//삭제
	
	//보류
	int sendMessage(String id);				
	int sendMessage2(String teamName);
	int getCount();
}
