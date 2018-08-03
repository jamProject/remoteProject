package com.spring.jamplan.main;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.UserVO;
@Service
public class MainDAOService implements MainDAO {
	
	@Autowired //
	private SqlSession sqlSession;
	
	@Override
	public UserVO getUserInfo(String id) {
		UserVO userVO = new UserVO();
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		//getMembers()의 메소드명과 mapper.xml의 id는 동일해야한다.
		 
		return userVO= mainMapper.getUserInfo(id);
	}

	public void insertUser(UserVO userVO) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		int res = mainMapper.insertMember(userVO);
		System.out.println("res="+res);
	}

}
