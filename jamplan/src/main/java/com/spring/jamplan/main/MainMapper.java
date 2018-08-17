package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD

=======
import java.util.List;

import com.spring.jamplan.model.PlanVO;
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

public interface MainMapper {
<<<<<<< HEAD
	public UserVO getUserInfo(UserVO userVO); 
	public ArrayList<TeamVO> getTeamInfo(String id);
=======
	public UserVO getUserInfo(String id); 
	public ArrayList<TeamVO> getTeamInfo(String id);
	public List<PlanVO> getPlanList();
	public int fileUpload(PlanVO planVO);
	public void InsertUser(UserVO vo);
>>>>>>> 77fe9338168c1f4b500958fcf4a99bb028370165
}
