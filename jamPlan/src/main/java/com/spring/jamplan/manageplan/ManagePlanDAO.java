package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface ManagePlanDAO {
	List<MapVO> getPickList();
	int insertMember(MapVO mapVO);
	int deleteMember(String id);
}
