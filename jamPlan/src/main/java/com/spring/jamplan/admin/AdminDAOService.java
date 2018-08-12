package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

@Service
public class AdminDAOService implements AdminDAO{

	@Autowired			//sqlSession에 sqlSessionTemplate가 대입됨(의존성주입).sqlSession이 부모클래스.
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
		userList = adminMapper.getUserList();
		return userList;
	}

	@Override
	public ArrayList<TeamVO> getTeamList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlanVO> getPlanList(String planName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamVO getTeam(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteTeam(String teamName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deletePlan(int planNo) {
		// TODO Auto-generated method stub
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

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
