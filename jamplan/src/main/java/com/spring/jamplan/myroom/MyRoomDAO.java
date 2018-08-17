package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.List;

public interface MyRoomDAO {
	
	List<TeamVO> getTeamList(TeamVO team);
	List<TeamVO> getTeamMember(UserVO user);
	List<PlanVO> getPlanList(TeamVO team);	
	ArrayList<PlanVO> checkUpdate(UserVO user);
	UserVO getUserInfo(UserVO user);	
	List<TeamInfoVO> searchTeam(TeamVO team);
	PlanVO searchPlan(PlanVO plan);
	int makeTeam(TeamVO team);
	int makePlan(PlanVO plan);
	String validationTeamName(TeamVO team);
	Object deleteTeam(TeamVO team);
	Object deletePlan(PlanVO plan);
}
