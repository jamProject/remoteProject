package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface ManagePlanDAO {
	List<MapVO> getPickList(String location);
	int checkPick(MapVO mapVO);
	void insertMember(MapVO mapVO);
	int pickCount(MapVO mapVO); 
	void deleteMember(MapVO mapVO);
	void updatePickCount(MapVO mapVO);
}
