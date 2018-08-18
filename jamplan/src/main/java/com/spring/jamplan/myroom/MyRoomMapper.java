package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanUpdateVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;


/**
 * @author Taehyuk, Kim
 *
 */

public interface MyRoomMapper {
		
	ArrayList<TeamInfoVO> getTeamList(String id);
	List<TeamInfoVO> getTeamMember(UserVO user);
	ArrayList<PlanVO> getPlanList(TeamInfoVO team);
	ArrayList<PlanUpdateVO> checkUpdate(UserVO user);
	UserVO getUserInfo(UserVO user);
	ArrayList<MessageVO> getMessageList(String receiver);
	List<TeamInfoVO> searchTeam(TeamInfoVO team);
	PlanVO searchPlan(PlanVO plan);
	int makeTeam(TeamInfoVO team);
	void insertPlan(TeamInfoVO vo);
	TeamInfoVO validationTeamName(TeamInfoVO team);
	int deleteTeam(TeamInfoVO team);
	int deletePlan(PlanVO plan);
	TeamInfoVO getTeamInfo (TeamInfoVO vo);
	int getMaxPlanNo();

}
