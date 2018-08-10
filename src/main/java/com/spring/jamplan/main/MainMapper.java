package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface MainMapper {
	public UserVO getUserInfo(String id); 
	public ArrayList<TeamVO> getTeamInfo(String id);
}
