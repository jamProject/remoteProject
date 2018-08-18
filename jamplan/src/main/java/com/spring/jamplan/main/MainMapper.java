package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import com.spring.jamplan.model.PlanVO;

import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface MainMapper {

	public ArrayList<TeamVO> getTeamInfo(String id);
	public UserVO getUserInfo(String id); 
	public List<PlanVO> getPlanList();
	public int fileUpload(PlanVO planVO);
	public void InsertUser(UserVO vo);

}
