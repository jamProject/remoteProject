package com.spring.jamplan2.searchcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	/*//라이크-로그인db에서 받아오기
	@Override
	public LikeVO likeUserId(String userId) {
		System.out.println("test!!!!2");
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		LikeVO vo = searchmapper.likeUserId(userId);
		System.out.println(vo);
		
		return vo;
	}*/
	
	//라이크체크
	@Override
	public void likeCheck(LikeVO likeVO) {
		System.out.println("like2");
		SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
		System.out.println(likeVO.getUserId());
		//라이크정보 불러오기
		ArrayList<LikeVO> likeList = searchmapper.getLikeData(likeVO);
		System.out.println("likeList : " + likeList.size());
		
		int planNo = 0;
		//값이 0(미선택), 1(선택) 일시 +1, -1
		if(likeList.size() == 0) {
			System.out.println("0일때 들어왔다!");
			planNo = likeList.get(0).getPlanNo() + 1;
			likeVO.setPlanNo(planNo);
			searchmapper.insertLikeData(likeVO);
			System.out.println("insertResult");
		}
		else {
			planNo = likeList.get(0).getPlanNo() - 1;
			likeVO.setPlanNo(planNo);
			searchmapper.updateLikeData(likeVO);
			System.out.println("updateResult");
		}
		
		
	}

}
