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
	public List<MapVO> getAllPickList() {
		List<MapVO> allPickList = null;
		MapMapper memberMapper = sqlSession.getMapper(MapMapper.class);
		allPickList = memberMapper.getAllPickList(); 
		
		return allPickList;
	}
	
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
		System.out.println("mapVO.getId():" +  mapVO.getId() );
		System.out.println("mapVO.getPlanNo():" +  mapVO.getPlanNo() );
		System.out.println("mapVO.getLocation():" +  mapVO.getLocation() );
		int check = mapMapper.checkPick(mapVO);
		System.out.println(check);
		System.out.println("checkPickDAOS");

		return check;
	}
	
	@Override
	public void insertMember(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("insertMemberDAOS1");
		System.out.println("mapVOINSERT.getId():" +  mapVO.getId() );
		System.out.println("mapVOINSERT.getPlanNo():" +  mapVO.getPlanNo() );
		System.out.println("mapVOINSERT.getLocation():" +  mapVO.getLocation() );
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

	@Override
	public void updatePickCount(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("updatePickCountDAOS1");

		mapMapper.updatePickCount(mapVO);
		System.out.println("updatePickCountDAOS2");

	}
	
	@Override
	public int pickCount(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("pickCountDAOS1");

		int pickNum = mapMapper.pickCount(mapVO);
		System.out.println("pickCountDAOS2");

		return pickNum;
	}

}