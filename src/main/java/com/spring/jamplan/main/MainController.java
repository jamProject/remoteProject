package com.spring.jamplan.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
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
	public ModelAndView loginMain(HttpSession session, UserVO userVO, ModelAndView mav,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
	
		출처: http://epthffh.tistory.com/entry/JAVA단에서-alert창-띄우기 [물고기 개발자의 블로그]
		System.out.println("ID : " + userVO.getId() + " PW : " + userVO.getPass());

		UserVO vo = mDAOS.getUserInfo(userVO.getId());
		ArrayList<TeamVO> teamList = mDAOS.getTeamInfo(userVO.getId());
		
		//mav.addObject("map",map);
		
		if(!(vo==null))
		{
		if(vo.getPass().equals(userVO.getPass()))
			{
			mav.addObject("userVO", vo);
			session.setAttribute("id", vo.getId());
			session.setAttribute("planNo", teamList.get(0).getPlanNo());
			mav.setViewName("managePlan/planMainPage");
			}
		else
			{	
			out.println("<script>alert('password error');location.href='';</script>");
			out.flush();
			}
		}
		
		else
			{	
			out.println("<script>alert('id error');location.href='';</script>");
			out.flush();
			//return "redirect:/loginform.me";
			}
		//vo.printElement();
		return mav;
	}
	/*
	@RequestMapping("join.main")
	public ModelAndView joinMain(HttpSession session, UserVO userVO, ModelAndView mav) {
		
		UserVO vo = 
	}
	*/
}
