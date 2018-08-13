package com.spring.jamplan2.searchcontroller;


import java.util.ArrayList;
import java.util.List;


public interface SearchService {
	List<PlanVO> getPlanjson();
	int fileUpload(PlanVO planVO);
	ArrayList<PlanVO> planSearch(PlanVO planVO);
	ArrayList<PlanVO> clickSearch(PlanVO planVO);
	void moveSchedule();
}


