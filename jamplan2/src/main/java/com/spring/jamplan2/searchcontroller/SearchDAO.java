package com.spring.jamplan2.searchcontroller;

import java.util.ArrayList;

import com.spring.jamplan2.model.PlanVO;
import com.spring.jamplan2.model.TogetherBoardVO;

public interface SearchDAO {
	ArrayList<PlanVO> searchPlan();
	ArrayList<TogetherBoardVO> searchTogether();
}
