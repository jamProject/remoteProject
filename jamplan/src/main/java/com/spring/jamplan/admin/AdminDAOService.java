package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class AdminDAOService implements AdminDAO {

	@Autowired(required=false) 
	private SqlSession sqlSession;
	
	@Autowired(required=false)
	private AdminMapper adminMapper;
	
	@Override
	public ArrayList<TeamInfoVO> adminTeamSearch(TeamInfoVO teamInfo) {
		System.out.println("adminTeamSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<TeamInfoVO> teamList = null;
		
		try {
			if(adminMapper.adminTeamSearch(teamInfo).size() != 0) {
				teamList = adminMapper.adminTeamSearch(teamInfo);
				System.out.println(teamList.get(0));
				return teamList;
			}else {
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("adminTeamSearch DAOService OUT");
		return teamList;
	}

	@Override
	public ArrayList<PlanVO> adminPlanSearch(PlanVO plan) {
		System.out.println("adminPlanSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<PlanVO> planList = adminMapper.adminPlanSearch(plan);
		
		System.out.println("adminPlanSearch DAOService OUT");
		return planList;
	}

	@Override
	public UserVO adminUserSearch(UserVO user) {
		System.out.println("adminUserSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		user = adminMapper.adminUserSearch(user);
		
		System.out.println("adminUserSearch DAOService OUT");
		return user;
	}

}
