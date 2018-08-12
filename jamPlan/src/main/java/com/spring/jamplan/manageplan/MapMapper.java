package com.spring.jamplan.manageplan;

import java.util.List;

import com.spring.jamplan.model.MapVO;

public interface MapMapper {
	public List<MapVO> getPickList();
	public int insertMember(MapVO mapVO);
	public int deleteMember(String id) ;
	
}
