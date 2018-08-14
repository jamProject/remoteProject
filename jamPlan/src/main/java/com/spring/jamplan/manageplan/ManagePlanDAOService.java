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
	public List<MapVO> getPickList(){
		List<MapVO> pickList = null;
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		pickList = mapMapper.getPickList();
		System.out.println("picklistDAOS");
		return pickList;
	}
	
	@Override
	public int checkPick(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int count = mapMapper.checkPick(mapVO);		
		return count;
	}
	
	@Override
	public int insertMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int res = mapMapper.insertMember(mapVO);	
		return res;
	}
	
	@Override
	public int markerPickCount() {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int pickCount = mapMapper.markerPickCount();	
		return pickCount;
	}
	
	@Override
	public int deleteMember(String id) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int res = mapMapper.deleteMember(id);
		return res;
	}

	
	
}
