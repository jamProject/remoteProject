package com.spring.jamplan.myroom;

import java.util.ArrayList;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public class MyRoomMapperService implements MyRoomMapper {
	public static final int ARRAY_MAX = 20;

	@Override
	public ArrayList<TeamVO> getTeamList(UserVO user) {
		ArrayList<TeamVO> teamList = new ArrayList<TeamVO>(ARRAY_MAX);
		
		return null;
	}

	@Override
	public ArrayList<PlanVO> getPlanList(UserVO user) {
		
		return null;
	}

	@Override
	public String getConfirmedDate(CalendarVO calendar) {
		
		return null;
	}

	@Override
	public UserVO getUserInfo(UserVO user) {
		
		return null;
	}

	@Override
	public MessageVO getMessageList(UserVO user) {
		
		return null;
	}

	@Override
	public TeamVO searchTeam(TeamVO team) {
		
		return null;
	}

	@Override
	public int makeTeam(UserVO user) {
		
		return 0;
	}

	@Override
	public int makePlan(UserVO user) {
		
		return 0;
	}

}
