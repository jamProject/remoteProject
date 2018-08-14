package com.spring.jamplan.main;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jamplan.model.PlanVO;
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
	

	@RequestMapping("fileUpload.main")
	public String fileUpload(MultipartHttpServletRequest multiRequest, PlanVO planVO) throws Exception {
		System.out.println("1");
		
		MultipartFile mf = multiRequest.getFile("file");
		String uploadPath = "C:\\BigDeep\\upload\\";
				//"C:\\Users\\Playdata\\Downloads\\0805ProjectHan\\jamplan2\\src\\main\\webapp\\resources\\search\\image\\";
		
		String originalFileExtension = mf.getOriginalFilename().substring(
				mf.getOriginalFilename().lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
		System.out.println("storedFileName="+storedFileName);
		if(mf.getSize() != 0) {
			mf.transferTo(new File(uploadPath+storedFileName));
			planVO.setImage(storedFileName);
		}
		int check = mDAOS.fileUpload(planVO);
		return "main/mainPage";
	}
	
	
	@RequestMapping(value ="imgJson.main", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getPlanGET() {
		System.out.println("제이슨 접근 전");
		List<PlanVO> list = mDAOS.getPlanjson();
		System.out.println("제이슨 접근 후");
		String str = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(list);
		}
		catch (Exception e)
		{
			System.out.println("firest() mapper :" + e.getMessage());
		}
		return str;
	}
	/*
	@RequestMapping("join.main")
	public ModelAndView joinMain(HttpSession session, UserVO userVO, ModelAndView mav) {
		
		UserVO vo = 
	}
	*/
}
