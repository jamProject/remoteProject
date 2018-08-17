package com.spring.jamplan.main;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamVO;
import com.spring.jamplan.model.UserVO;


@Controller
public class MainController {
	@Autowired(required = true)
	UserVO userVO;
	
	@Autowired
	MainDAOService mDAOS;
/*<<<<<<< HEAD

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
=======
	*/


	@Autowired
	TeamVO teamVo;
	
	@RequestMapping("/home.do")
	public String home(Locale locale, Model model) {
		System.out.println("AAAAAAA");
		return "main/mainPage";
	}

	@RequestMapping("/login.do")
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
			mav.setViewName("managePlan/main");
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
	
	/*@RequestMapping("/snsLogin.do")
	public String isComplete(HttpSession session) {
		return "main/naverlogin";

	}
	
	@RequestMapping("/callback.do")
	public String navLogin(HttpServletRequest request) throws Exception {	
		return "main/callback";
	}
	
	@RequestMapping("/personalInfo.do")
	public void personalInfo(HttpServletRequest request) throws Exception {
	        String token = "AAAAOIobSL5yToyq5itBVi6FDZAG3DxT1WTHDkOV2AtYWkVqzvQfBypXVNFZZ5azX4NEH14nQwsOwfP/8Bwwd6fzCY0=";// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.
	        String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        try {
	            String apiURL = "https://openapi.naver.com/v1/nid/me";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("Authorization", header);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}

	*/
	@RequestMapping("/fileUpload.do")
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
	
	
	@RequestMapping(value ="/imgJson.do", method = RequestMethod.GET, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getPlanGET() {
		System.out.println("제이슨 접근 전");
		List<PlanVO> list = mDAOS.getPlanjson();
		System.out.println("제이슨 접근 후");
		String var = list.get(1).getPlanName();
		String img = list.get(1).getImage();
		System.out.println("var="+var);
		System.out.println("img="+img);
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
