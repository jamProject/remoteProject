package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;

@Service
public class ManagePlanDAOService {
	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	@Autowired
	private TeamVO teamVO;
	
	private CalendarMapper calendarMapper;
	private ArrayList<PlanVO> planList;
	private ArrayList<CalendarVO> voList;

	public ArrayList<PlanVO> getPlans(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getAllPlans();
		// System.out.println("dao");
		return planList;
	}

	public ArrayList<PlanVO> getTeamPlan(PlanVO planVO) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);

		planList = new ArrayList<PlanVO>();
		planList = calendarMapper.getTeamPlan(planVO);
		// System.out.println(planList.get(0).getPlanNo());
		return planList;
	}
	
	public TeamVO getPlanRole(HashMap<String, Object> map) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		teamVO = calendarMapper.getTeamRole(map);
		return teamVO;
	}
	
	public void getFixDate(CalendarVO vo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		calendarMapper.updateFixDate(vo);
	}
	
	public void getSelectDateFix(CalendarVO vo){
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		voList = calendarMapper.getSelectFixDate(vo);
		if(voList.size()==0) {
			vo.setDateCount(1);
			calendarMapper.insertSelectDate(vo);
		}else {
			getFixDate(vo);
		}
	}

	public ArrayList<CalendarVO> getSelectDate(int planNo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		voList = calendarMapper.getCalendarSelectDate(planNo);
		// System.out.println(sendList.size());
		// System.out.println(voList.size());
		int k = 0;
		int size = voList.size();
		//일정을 여러명이 선택 했을 때 중복 제거하고 +n 카운트 하나만 보이도록 
		for (int i = 0; i < voList.size(); i++) {
			for (int j = 0; j < voList.size(); j++) {
				if (i != j) {
					if (voList.get(i).getSelectDate().equals(voList.get(j).getSelectDate())) {
						//System.out.println("remove : " + voList.get(j).getSelectDate());
						voList.remove(j);
						k++;
						i = 0;
						break;
					}
				}
			}
			if (i > size - k) {
				break;
			}
		}
		// System.out.println("size : " + voList.size());
		return voList;
	}

	public void insertSelectDate(CalendarVO vo) {
		calendarMapper = sqlSession.getMapper(CalendarMapper.class);
		ArrayList<CalendarVO> checkVOList = calendarMapper.checkSelectDate(vo);
		ArrayList<CalendarVO> checkList = calendarMapper.getCountDate(vo);
		int dateCount = 1;
		if (checkVOList.size() == 0) {

			if (checkList.size() != 0) {
				// System.out.println(checkList.size());
				dateCount = checkList.get(0).getDateCount() + 1;
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				calendarMapper.updateCountDate(vo);

				// System.out.println("처음 눌렀고, 팀원");
			} else {
				vo.setDateCount(dateCount);
				calendarMapper.insertSelectDate(vo);
				// System.out.println("처음 눌렀고, 혼자");
			}

		} else {
			if (checkList.size() != 0) {
				dateCount = checkList.get(0).getDateCount() - 1;
				vo.setDateCount(dateCount);
				calendarMapper.updateCountDate(vo);
				calendarMapper.deleteSelectDate(vo);
				// System.out.println("두번 눌렀고, 팀원");
			} else {
				calendarMapper.deleteSelectDate(vo);
				// System.out.println("두번 눌렀고, 혼자");
			}

		}

	}

}
