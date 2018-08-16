package com.spring.jamplan.main;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;

@Controller
public class MainController {
	@Autowired(required = true)
	UserVO userVO;
	
	@Autowired
	MainDAOService mDAOS;

	@Autowired
	TeamVO teamVo;

	@RequestMapping("login.main")
	public ModelAndView loginMain(HttpSession session, UserVO userVO, ModelAndView mav) {

		System.out.println("login ID : " + userVO.getId() + " PW : " + userVO.getPass());

		UserVO vo = mDAOS.getUserInfo(userVO); 
		ArrayList<TeamVO> teamList = mDAOS.getTeamInfo(userVO.getId());
		System.out.println(teamList.get(0).getPlanNo());
		mav.addObject("userVO", vo);
		//mav.addObject("map",map);
		mav.setViewName("managePlan/main");
		session.setAttribute("id", userVO.getId());
		session.setAttribute("planNo", teamList.get(0).getPlanNo());
		//vo.printElement();
		return mav;

	}
}
