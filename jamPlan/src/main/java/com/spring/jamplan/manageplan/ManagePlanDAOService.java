package com.spring.jamplan.manageplan;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.MapVO;

@Service("managePlanDAO")
public class ManagePlanDAOService implements ManagePlanDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MapVO> getPickList(String location){
		List<MapVO> pickList = null;
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		pickList = mapMapper.getPickList(location);
		System.out.println("picklistDAOS");
		return pickList;
	}
	
	@Override
	public int checkPick(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int check = mapMapper.checkPick(mapVO);		
		return check;
	}
	
	@Override
	public int insertMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int res = mapMapper.insertMember(mapVO);	
		return res;
	}

	@Override
	public int deleteMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int res = mapMapper.deleteMember(mapVO);
		return res;
	}

	public List<MapVO> getAllPickList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePickCount(int newPickCount) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		mapMapper.updatePickCount(newPickCount);
	}

	public int pickCount(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int pickCount = mapMapper.pickCount(mapVO);
		return pickCount;
	}

	
	
}