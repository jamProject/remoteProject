package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.LikeVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.UserVO;


public interface SearchMapper {
	List<PlanVO> getPlanList();
	int fileUpload(PlanVO planVO);
	ArrayList<PlanVO> planSearch(PlanVO planVO);
	ArrayList<PlanVO> clickSearch(PlanVO planVO);
	void moveSchedule();
	UserVO getUserId(String id);
	int heartCheck(LikeVO likeVO);
	
}
