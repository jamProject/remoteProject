package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

@Service
public class AdminDAOService implements AdminDAO{

	@Override
	public ArrayList<TeamVO> getTeamList(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanVO> getPlanList(String planName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> getUserList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePlan(int planNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTeam(int teamNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendMessage(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendMessage2(String teamName) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
