package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.LikeVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.UserVO;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public List<PlanVO> getPlanjson() {
		List<PlanVO> planList = null;
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		planList = searchmapper.getPlanList();
		
		return planList;
	}
	
	@Override
	public int fileUpload(PlanVO planVO) {
		System.out.println("image");
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		int check = searchmapper.fileUpload(planVO);
		System.out.println("returnfile");
		return check;
	}
	
	/*@Override
	public ArrayList<PlanVO> planSearch(PlanVO planVO) {
		System.out.println("확인2");
		ArrayList<PlanVO> planList = null;
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		planList = searchmapper.planSearch(planVO);
		System.out.println("확인3");
		
		return planList;
	}*/
	
	@Override
	public ArrayList<PlanVO> planSearch(PlanVO planVO) {
		//넘어오는값확인
		System.out.println("데이터확인 : " + planVO.getPlanDate());
		System.out.println("데이터확인 : " + planVO.getPlanName());
		
		ArrayList<PlanVO> planList = null;
		
		if (planVO.getPlanDate() != null) {
			System.out.println("타겟확인a");
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.planSearch(planVO);
			//System.out.println(planList.get(0).getPlanDate());
			System.out.println("타겟확인a");
		}
		else if (planVO.getPlanName() != null) {
			System.out.println("타겟확인b");
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.planSearch(planVO);
			System.out.println("타겟확인b");
			
			return planList;
		}
		return planList;
	}
	
	@Override
	public ArrayList<PlanVO> clickSearch(PlanVO planVO) {
		//넘어오는값확인
		System.out.println("데이터확인 : " + planVO.getPlanDate());
		System.out.println("데이터확인 : " + planVO.getReadCount());
		System.out.println("데이터확인 : " + planVO.getGoodCount());
		System.out.println("데이터확인 : " + planVO.getPlanName());
		
		ArrayList<PlanVO> planList = null;
	
		if(planVO.getPlanDate() != null) {
			System.out.println("클릭확인a");
			
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.clickSearch(planVO);
			System.out.println("클릭확인a");
			/*ArrayList<PlanVO> planList = null;
			return planList;*/
		
		}
		
		else if(planVO.getReadCount() == 1) {
			System.out.println("클릭확인1");
			
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.clickSearch(planVO);
			System.out.println("클릭확인1");
			
			return planList;
			}
		
		else if(planVO.getGoodCount() == 2) {
			System.out.println("클릭확인2");
			
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.clickSearch(planVO);
			System.out.println("클릭확인2");
			
			return planList;
			}
		
		else if(planVO.getPlanName() != null) {
			System.out.println("클릭확인b");
			
			SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
			planList = searchmapper.clickSearch(planVO);
			System.out.println("클릭확인b");
			return planList;

			}
			/*else {
				return planList;
			}*/
		return planList;
		
	
	}
	
	@Override
	public void moveSchedule() {
		System.out.println("move2");
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		searchmapper.moveSchedule();
		System.out.println("move3");
	
	}
	//로그인 테스트
	@Override
	public UserVO getUserId(String id) {
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		UserVO vo = searchmapper.getUserId(id);
		
		return vo;
	}
	
	//라이크체크
	@Override
	public int heartCheck(LikeVO likeVO) {
		System.out.println("like2");
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		int check = searchmapper.heartCheck(likeVO);
		System.out.println("like3");
		
		return check;
		
		
	}

}
