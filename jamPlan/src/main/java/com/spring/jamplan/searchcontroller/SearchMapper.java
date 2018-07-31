package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TogetherBoardVO;

/**
 * @author Taehyuk, Kim
 *
 */
@Mapper
public interface SearchMapper {
	
	// 최신순, 조회순, 좋아요순 등에 따른 나열 순서 변화는 각 메소드 안에서 처리한다.
	@Select
	ArrayList<PlanVO> searchPlan(String planName);
	ArrayList<TogetherBoardVO> searchTogether(String togetherName);
	
}
