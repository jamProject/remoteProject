package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface MyRoomDAO {
	
	List<TeamVO> getTeamList(TeamVO team);
	List<TeamVO> getTeamMember(UserVO vo);
	List<PlanVO> getPlanList(TeamVO team);	
	ArrayList<PlanVO> checkUpdate(UserVO vo);
	UserVO getUserInfo(UserVO vo);	
	List<TeamInfoVO> searchTeam(TeamVO team);
	PlanVO searchPlan(PlanVO plan);
	int makeTeam(TeamVO team);
	int makePlan(PlanVO plan);
	String validationTeamName(TeamVO team);
	Object deleteTeam(TeamVO team);
	Object deletePlan(PlanVO plan);
}
