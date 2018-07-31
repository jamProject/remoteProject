package com.spring.jamplan.myroom;

import java.util.ArrayList;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;


public interface MyRoomDAO {

	public ArrayList<TeamVO> getTeamList(UserVO user);
	public ArrayList<PlanVO> getPlanList(UserVO user);
	public String getConfirmedDate(CalendarVO calendar);
	public UserVO getUserInfo(String id);
	public MessageVO getMessageList(String id);

	public TeamVO searchTeam();
	public int makeTeam();
	public int makePlan(UserVO user);
}
