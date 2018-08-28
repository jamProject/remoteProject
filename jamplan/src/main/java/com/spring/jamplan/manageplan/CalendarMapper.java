package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;


public interface CalendarMapper {
	public ArrayList<PlanVO> getAllPlans();
	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO);
	public ArrayList<CalendarVO> getCalendarSelectDate(int planNo);
	
	public ArrayList<CalendarVO> checkSelectDate(CalendarVO calendarVO);
	public ArrayList<CalendarVO> getCountDate(CalendarVO calendarVO);
	public void insertSelectDate(CalendarVO calendarVO);
	public void deleteSelectDate(CalendarVO calendarVO);
	public void updateCountDate(CalendarVO calendarVO);
	public TeamInfoVO getTeamRole(HashMap<String, Object> map);
	public CalendarVO getDday(CalendarVO calendarVO);
	public void updateFixDate(CalendarVO calendarVO);
	public ArrayList<CalendarVO> getSelectFixDate(CalendarVO calendarVO);
	public ArrayList<CalendarVO> getMemberId(int planNo);

}
