package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public class AdminDAOService implements AdminDAO {

	@Autowired(required=false) 
	private SqlSession sqlSession;
	
	@Autowired(required=false)
	private AdminMapper adminMapper;
	
	@Override
	public ArrayList<TeamInfoVO> adminTeamSearch(TeamInfoVO teamInfo) {
		System.out.println("adminTeamSearch IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<TeamInfoVO> teamList = adminMapper.adminTeamSearch(teamInfo);
		
		System.out.println("adminTeamSearch OUT");
		return teamList;
	}

	@Override
	public ArrayList<PlanVO> adminPlanSearch(PlanVO plan) {
		System.out.println("adminPlanSearch IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<PlanVO> planList = adminMapper.adminPlanSearch(plan);
		
		System.out.println("adminPlanSearch OUT");
		return planList;
	}

	@Override
	public UserVO adminUserSearch(UserVO user) {
		System.out.println("adminUserSearch IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		user = adminMapper.adminUserSearch(user);
		
		System.out.println("adminUserSearch OUT");
		return user;
	}

}
