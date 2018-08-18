package com.spring.jamplan.myroom;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanUpdateVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;


/**
 * @author Taehyuk, Kim
 *
 */

public interface MyRoomMapper {
		
	ArrayList<TeamVO> getTeamList(String id);
	List<TeamVO> getTeamMember(UserVO user);
	ArrayList<PlanVO> getPlanList(TeamVO team);
	ArrayList<PlanUpdateVO> checkUpdate(UserVO user);
	UserVO getUserInfo(UserVO user);
	ArrayList<MessageVO> getMessageList(String receiver);
	List<TeamInfoVO> searchTeam(TeamVO team);
	PlanVO searchPlan(PlanVO plan);
	int makeTeam(TeamVO team);
	int makePlan(PlanVO plan);
	TeamVO validationTeamName(TeamVO team);
	int deleteTeam(TeamVO team);
	int deletePlan(PlanVO plan);

}
