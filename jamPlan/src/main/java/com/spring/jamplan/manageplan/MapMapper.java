package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface MapMapper {
	List<MapVO> getPickList(String location);
	int checkPick(MapVO mapVO);
	int insertMember(MapVO mapVO);
	int deleteMember(MapVO mapVO);
	void updatePickCount(int newPickCount);
	int pickCount(MapVO mapVO); 
}
