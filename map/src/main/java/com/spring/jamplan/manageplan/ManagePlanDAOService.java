package com.spring.jamplan.manageplan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MapVO;

@Service("managePlanDAO")
public class ManagePlanDAOService implements ManagePlanDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MapVO> getAllPickList(MapVO mapVO) {
		List<MapVO> allPickList = null;
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("getAllPickListDAOS1");
		System.out.println(mapVO.getSelectDate());
		allPickList = mapMapper.getAllPickList(mapVO); 
		System.out.println("getAllPickListDAOS2");
		return allPickList;
	}
	
	@Override
	public List<MapVO> getPickList(MapVO mapVO){
		List<MapVO> pickList = null;
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		pickList = mapMapper.getPickList(mapVO);
		System.out.println("getpicklistDAOS");
		return pickList;
	}
	
	@Override
	public int checkPick(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		int check = mapMapper.checkPick(mapVO);

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

	public void confirmPlace(Map<String, Object> hm) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("confirmPlace1");

		mapMapper.confirmPlace(hm);
		System.out.println("confirmPlace2");
	}

	public void resetPlace(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("resetPlaceDAOS1");

		mapMapper.resetPlace(mapVO);
		System.out.println("resetPlaceDAOS2");
		
	}

	public void changeColor(MapVO mapVO) {
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("changeColorDAOS1");

		mapMapper.changeColor(mapVO);
		System.out.println("changeColorDAOS2");
	}

	public List<MapVO> getSelectDate(CalendarVO calendarVO) {		
		List<MapVO> selectDateList = null;
		MapMapper mapMapper = sqlSession.getMapper(MapMapper.class);
		System.out.println("날짜받아온거="+mapMapper.getSelectDate(calendarVO));
		
		selectDateList = mapMapper.getSelectDate(calendarVO);
		System.out.println("getpicklistDAOS");
		return selectDateList;
	}

}