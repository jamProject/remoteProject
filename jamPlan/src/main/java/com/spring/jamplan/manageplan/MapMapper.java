package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface MapMapper {
	List<MapVO> getPickList();
	int checkPick(MapVO mapVO);
	int insertMember(MapVO mapVO);
	int markerPickCount();
	int deleteMember(String id); 
}
