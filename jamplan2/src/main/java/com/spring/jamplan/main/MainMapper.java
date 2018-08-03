package com.spring.jamplan.main;

import com.spring.jamplan.model.UserVO;

public interface MainMapper {
	public UserVO getUserInfo(String id);
	public int insertMember(UserVO userVO);
}
