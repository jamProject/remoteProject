package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TogetherBoardVO;

public interface SearchDAO {
	ArrayList<PlanVO> searchPlan();
	ArrayList<TogetherBoardVO> searchTogether();
}
