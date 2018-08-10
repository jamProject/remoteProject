package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;



@Service   
public class MainDAOService {

	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	@Autowired
	TeamVO teamVO;
	
	private ArrayList<TeamVO> list;
	public UserVO getUserInfo(String id) {
		System.out.println("getUserInfo start");
		System.out.println(sqlSession);
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
	
		UserVO vo = mainMapper.getUserInfo(id); 
		return vo;
	}
	
	public ArrayList<TeamVO> getTeamInfo(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		list = mainMapper.getTeamInfo(id);
		
		return list;
	}
}
