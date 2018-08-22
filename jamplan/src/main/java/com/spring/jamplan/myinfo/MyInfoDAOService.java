package com.spring.jamplan.myinfo;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class MyInfoDAOService implements MyInfoDAO {
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	

	@Override
	public UserVO getMyInfo(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMyInfo(UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamListAsLeader(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamListAsMember(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeTeamAsLeader(UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int signOutTeamAsMember(UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
