package com.spring.jamplan.main;

import com.spring.jamplan.model.UserVO;

public interface MainDAO {

	UserVO getUserInfo(String id);
	void insertUser(UserVO userVO);
}
