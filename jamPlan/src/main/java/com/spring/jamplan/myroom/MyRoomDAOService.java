package com.spring.jamplan.myroom;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

@Service
public class MyRoomDAOService implements MyRoomDAO {

	@Override
	public ArrayList<TeamVO> getTeamList(UserVO user) {
		ArrayList<TeamVO> teamList = new ArrayList<TeamVO>();
		
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
	public UserVO getUserInfo(String id) {
		
		return null;
	}

	@Override
	public MessageVO getMessageList(String id) {
		
		return null;
	}

	@Override
	public TeamVO searchTeam() {
		
		return null;
	}

	@Override
	public int makeTeam() {
		
		return 0;
	}

	@Override
	public int makePlan(UserVO user) {
		
		return 0;
	}

}