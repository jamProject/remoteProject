package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;

public interface CalendarMapper {
	public ArrayList<PlanVO> getAllPlans();
	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO);
	public ArrayList<CalendarVO> getCalendarSelectDate(int planNo);
	
	public ArrayList<CalendarVO> checkSelectDate(CalendarVO calendarVO);
	public ArrayList<CalendarVO> getCountDate(CalendarVO calendarVO);
	public void insertSelectDate(CalendarVO calendarVO);
	public void deleteSelectDate(CalendarVO calendarVO);
	public void updateCountDate(CalendarVO calendarVO);
	
}
