package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface MapMapper {
	List<MapVO> getPickList(String location);
	List<MapVO> getAllPickList();
	int checkPick(MapVO mapVO);
	void insertMember(MapVO mapVO);
	void deleteMember(MapVO mapVO);
	void updatePickCount(MapVO mapVO);
	int pickCount(MapVO mapVO); 
}
