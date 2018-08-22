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
	
	@Autowired(required=false)
	private MyInfoMapper myInfoMapper;

	@Override
	public UserVO getMyInfo(UserVO user) {
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		user = myInfoMapper.getMyInfo(user);
		return user;
	}

	@Override
	public int updateMyInfo(UserVO user) {
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.updateMyInfo(user);
		return result;
	}
	
	// user의 프로필 사진을 등록하는 메소드
	@Override
	public int setProfileImage(UserVO user) {
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.setProfileImage(user);
		
		return result;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamListAsLeader(UserVO user) {
		ArrayList<TeamInfoVO> teamList = null;
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		teamList = myInfoMapper.getTeamListAsLeader(user);
		
		return teamList;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamListAsMember(UserVO user) {
		ArrayList<TeamInfoVO> teamList = null;
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		teamList = myInfoMapper.getTeamListAsMember(user);
		
		return teamList;
	}

	@Override
	public int removeTeamAsLeader(UserVO user) {
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.removeTeamAsLeader(user);
		return result;
	}

	@Override
	public int signOutTeamAsMember(UserVO user) {
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.signOutTeamAsMember(user);
		return result;
	}

}
