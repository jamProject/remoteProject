package com.spring.jamplan.manageplan;

import java.util.List;
import java.util.Map;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.MapVO;

public interface ManagePlanDAO {
	List<MapVO> getAllPickList(MapVO mapVO);
	List<MapVO> getPickList(MapVO mapVO);	
	int checkPick(MapVO mapVO);
	void insertMember(MapVO mapVO);
	int pickCount(MapVO mapVO); 
	void deleteMember(MapVO mapVO);
	void updatePickCount(MapVO mapVO);
	void confirmPlace(Map<String, Object> hm); 
	void resetPlace(MapVO mapVO);
	void changeColor(MapVO mapVO);
	List<MapVO> getSelectDate(CalendarVO calendarVO);
}
