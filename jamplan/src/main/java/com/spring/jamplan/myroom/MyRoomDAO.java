package com.spring.jamplan.myroom;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public interface MyRoomDAO {
	
	int makeTeam(TeamInfoVO team);	
	int insertApplyMessage(MessageVO vo);
	int deleteCansleMessage(MessageVO vo);
	int getMaxPlanNo();
	ArrayList<TeamInfoVO> deleteValByTeamName(ArrayList<TeamInfoVO> teamList);
	ArrayList<TeamInfoVO> getPlanListByPlanName(TeamInfoVO team);
	String validationTeamName(TeamInfoVO team);
	Object deleteTeam(TeamInfoVO team);
	Object deletePlan(PlanVO plan);
	void deleteAlertMessage(MessageVO vo);
	void insertPlan(TeamInfoVO vo);//
	void deleteNullPlanTeaminfo(String teamName);	
	void updateReadMessage(String receiver);
	void insertToMember(MessageVO vo);
	void insertAlertMessage(MessageVO message);
	UserVO getUserInfo(UserVO vo);	
	ArrayList<TeamInfoVO> searchPlan(TeamInfoVO team);
	TeamInfoVO getRole(TeamInfoVO team);
	
	ArrayList<TeamInfoVO> getTeamList(String id);
	ArrayList<TeamInfoVO> getPlanListById(String id);
	ArrayList<TeamInfoVO> checkUpdate(UserVO vo);
	ArrayList<TeamInfoVO> getTeamMember(UserVO vo);
	ArrayList<TeamInfoVO> getPlanList(TeamInfoVO team);	
	ArrayList<TeamInfoVO> searchTeam(TeamInfoVO team);
	ArrayList<TeamInfoVO> getTeamInfo (TeamInfoVO team);
	ArrayList<MessageVO>getMessageList(MessageVO vo);
	ArrayList<TeamInfoVO> getTeamMemberList(TeamInfoVO teamVO);
	ArrayList<TeamInfoVO> deleteValByMemberID(ArrayList<TeamInfoVO> list);

}
