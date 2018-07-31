package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TogetherBoardVO;

/**
 * @author Taehyuk, Kim
 *
 */
public interface SearchMapper {
	
	// 최신순, 조회순, 좋아요순 등에 따른 나열 순서 변화는 각 메소드 안에서 처리한다.
	ArrayList<PlanVO> searchPlan();
	ArrayList<TogetherBoardVO> searchTogether();
	
	
}
