package com.spring.jamplan.main;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jamplan.model.UserVO;

@Controller
public class MainController {
	
	@Autowired
	private MainDAOService mainDAOService;
	
	@RequestMapping("/loginAction.me")
	public String userCheck(UserVO userVO, HttpSession session,
			HttpServletResponse response) throws Exception {
	UserVO user = mainDAOService.getUserInfo(userVO.getId());
	
	response.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
	PrintWriter writer = response.getWriter();
	if(user.getPass().equals(userVO.getPass()))
	{
		session.setAttribute("id", userVO.getId());
		writer.write("<script>alert('로그인 성공!!');location.href='./myRoom/MyRoom.jsp';</script>");
		//return "redirect:/main.me";
	}
	else
	{
		writer.write("<script>alert('로그인 실패!!');location.href='./mainPage.jsp';</script>");
		//return "redirect:/loginform.me";
	}
	
	return null;
}
	@RequestMapping("/joinAction.me")
	public ModelAndView userJoin(UserVO userVO,HttpSession session,
			HttpServletResponse response) throws Exception {
		mainDAOService.insertUser(userVO);
	
		ModelAndView result = new ModelAndView();
		result.setViewName("myroom");
		return result;
		
}
}
