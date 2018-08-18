package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
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
/*=======
		UserVO vo = mainMapper.getUserInfo(id); 
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165*/
		return vo;
	}
	
	public ArrayList<TeamVO> getTeamInfo(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		list = mainMapper.getTeamInfo(id);
		
		return list;
	}

	public List<PlanVO> getPlanjson() {
		List<PlanVO> planList = null;
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		// System.out.println("planList11="+planList);
		planList = mainMapper.getPlanList();
		//System.out.println("planList(DB갓다오고나서)="+planList);
		return planList;
	}
	public void InsertUser(UserVO vo) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		mainMapper.InsertUser(vo);
	}
	
	
	public int fileUpload(PlanVO planVO) {
		System.out.println("image");
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		int check = mainMapper.fileUpload(planVO);
		System.out.println("returnfile");
		return check;
	}

}
