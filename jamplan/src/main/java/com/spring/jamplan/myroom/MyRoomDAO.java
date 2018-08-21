package com.spring.jamplan.myroom;

import java.util.ArrayList;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public interface MyRoomDAO {
	
	int makeTeam(TeamInfoVO team);
	int insertPlan(TeamInfoVO vo);
	int getMaxPlanNo();
	
	String validationTeamName(TeamInfoVO team);
	Object deleteTeam(TeamInfoVO team);
	Object deletePlan(PlanVO plan);
	
	void deleteNullPlanTeaminfo(String teamName);
	void insertApplyMessage(MessageVO vo);
	void deleteCansleMessage(MessageVO vo);
	
	UserVO getUserInfo(UserVO vo);	
	PlanVO searchPlan(PlanVO plan);
	TeamInfoVO getRole(TeamInfoVO team);
	
	ArrayList<TeamInfoVO> getTeamList(String id);
	ArrayList<TeamInfoVO> getPlanListById(String id);
	ArrayList<PlanVO> checkUpdate(UserVO vo);
	ArrayList<TeamInfoVO> getTeamMember(UserVO vo);
	ArrayList<PlanVO> getPlanList(TeamInfoVO team);	
	ArrayList<TeamInfoVO> searchTeam(TeamInfoVO team);
	ArrayList<TeamInfoVO> getTeamInfo (TeamInfoVO team);

}
