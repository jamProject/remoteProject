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
		System.out.println("getpicklistDAOS");
		return pickList;
	}
	
	@Override
	public int checkPick(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int check = mapMapper.checkPick(mapVO);	
		System.out.println("checkPickDAOS");

		return check;
	}
	
	@Override
	public void insertMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("insertMemberDAOS1");
		mapMapper.insertMember(mapVO);	
		System.out.println("insertMemberDAOS2");

	}

	@Override
	public void deleteMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("deleteMemberDAOS1");
		
		System.out.println(mapVO.getPlanNo());
		mapMapper.deleteMember(mapVO);
		System.out.println("deleteMemberDAOS2");

	}

	public List<MapVO> getAllPickList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePickCount(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("updatePickCountDAOS1");

		mapMapper.updatePickCount(mapVO);
		System.out.println("updatePickCountDAOS2");

	}

	public int pickCount(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("pickCountDAOS1");

		int pickNum = mapMapper.pickCount(mapVO);
		System.out.println("pickCountDAOS2");

		return pickNum;
	}

}