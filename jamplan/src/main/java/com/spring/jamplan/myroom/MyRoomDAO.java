package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public interface MyRoomDAO {
	
	List<TeamInfoVO> getTeamList(String id);
	List<TeamInfoVO> getTeamMember(UserVO vo);
	List<PlanVO> getPlanList(TeamInfoVO team);	
	ArrayList<PlanVO> checkUpdate(UserVO vo);
	UserVO getUserInfo(UserVO vo);	
	List<TeamInfoVO> searchTeam(TeamInfoVO team);
	PlanVO searchPlan(PlanVO plan);
	int makeTeam(TeamInfoVO team);
	int insertPlan(TeamInfoVO vo);
	String validationTeamName(TeamInfoVO team);
	Object deleteTeam(TeamInfoVO team);
	Object deletePlan(PlanVO plan);
	ArrayList<TeamInfoVO> getTeamInfo (TeamInfoVO vo);
	public int getMaxPlanNo();
}
