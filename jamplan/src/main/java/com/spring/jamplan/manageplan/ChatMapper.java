package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.TeamInfoVO;


public interface ChatMapper {
	ArrayList<TeamInfoVO> chatConnect(TeamInfoVO teamInfo);
}
