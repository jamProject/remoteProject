package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;

@Service
public class ManagePlanDAOService {
	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	private CalendarMapper calendarMapper;
	private ArrayList<PlanVO> planList;
	private ArrayList<CalendarVO> voList;
	

	public ArrayList<PlanVO> getPlans(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getAllPlans();
		System.out.println("dao");
		return planList;
	}

	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getTeamPlan(planVO);
		System.out.println(planList.get(0).getPlanNo());
		return planList;
	}

	public ArrayList<CalendarVO> getSelectDate(int planNo){
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		voList = calendarMapper.getCalendarSelectDate(planNo);
		return voList;
	}
	public void insertSelectDate(CalendarVO vo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		ArrayList<CalendarVO> checkVOList = calendarMapper.checkSelectDate(vo);
		ArrayList<CalendarVO> checkList = calendarMapper.getCountDate(vo);
		int dateCount = 1;
		if(checkVOList.size()==0) {	
			
			if(checkList.size()!=0) {
				//System.out.println(checkList.size());
				dateCount = checkList.get(0).getDateCount() + 1;
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				calendarMapper.updateCountDate(vo);
				
				System.out.println("처음 눌렀고, 팀원");
			}else {
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				System.out.println("처음 눌렀고, 혼자");
			}
			
		}else {			
			if(checkList.size()!=0) {
				dateCount = checkList.get(0).getDateCount() - 1;
				vo.setDateCount(dateCount);	
				calendarMapper.updateCountDate(vo);
				calendarMapper.deleteSelectDate(vo);
				System.out.println("두번 눌렀고, 팀원");
			}else {
				calendarMapper.deleteSelectDate(vo);
				System.out.println("두번 눌렀고, 혼자");
			}
		
		}
		
	}
	
}
