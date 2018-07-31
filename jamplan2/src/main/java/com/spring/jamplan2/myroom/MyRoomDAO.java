package com.spring.jamplan2.myroom;

import java.util.ArrayList;

import com.spring.jamplan2.model.CalendarVO;
import com.spring.jamplan2.model.MessageVO;
import com.spring.jamplan2.model.PlanVO;
import com.spring.jamplan2.model.TeamVO;
import com.spring.jamplan2.model.UserVO;


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
