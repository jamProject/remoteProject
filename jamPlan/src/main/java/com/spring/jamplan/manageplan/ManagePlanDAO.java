package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface ManagePlanDAO {
	List<MapVO> getPickList(String location);
	int checkPick(MapVO mapVO);
	int insertMember(MapVO mapVO);
	int pickCount(MapVO mapVO); 
	int deleteMember(MapVO mapVO);
	void updatePickCount(int newPickCount);
}
