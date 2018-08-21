package com.spring.jamplan2.searchcontroller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	
	@RequestMapping("main.search") 
	public String mainLoad() {
		return "search/searchMainPage";
	}
	//검색페이지 이동
	@RequestMapping("plan.search")
	public String planLoad() {

		
		/*String str = "";
	
		UserVO vo = searchService.getUserId(userVO.getId());
		
		System.out.println(vo.getId());
		
		session.setAttribute("checkID", vo.getId());
		
		if (session.getAttribute("checkID") != null) {
			str = "search/searchPlanPage";
		}
		else {
			str = "//webapp/mianPage";
		}*/
		/*
		 * System.out.println("move1");
		 * HttpSession session, UserVO userVO
		 * 
		searchService.moveSchedule();
		System.out.println("move4");
		session.setAttribute("checkID", userVO.getId());*/
		
		
		
		return "search/searchPlanPage";
	}
	
	//일정 리스트 뿌려주기
	@RequestMapping(value = "/getPlanList.search", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getPlanGET() {

		System.out.println("1");
		List<PlanVO> list = searchService.getPlanjson();
		System.out.println("2");
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
	
	//파일업로드페이지 이동
	@RequestMapping("formFile.search")
	public String formFile() {
		return "search/formFile";
	}
	
	//저장한파일 인서트로 뿌려주는 컨트롤러
	@RequestMapping("fileUpload.search")
	public String fileUpload(MultipartHttpServletRequest multiRequest, PlanVO planVO) throws Exception {
		System.out.println("1");
		MultipartFile mf = multiRequest.getFile("file");
		
		String uploadPath = "C:\\BigDeep\\upload\\";
				//"C:\\Users\\Playdata\\Downloads\\0805ProjectHan\\jamplan2\\src\\main\\webapp\\resources\\search\\image\\";
		
		String originalFileExtension = mf.getOriginalFilename().substring(
				mf.getOriginalFilename().lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
		
		if(mf.getSize() != 0) {
			mf.transferTo(new File(uploadPath+storedFileName));
			
			planVO.setImage(storedFileName);
		}
		
		int check = searchService.fileUpload(planVO);
		
		return "redirect:/plan.search";
	}
	
	//일정검색컨트롤러
	@RequestMapping(value = "planSearch.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String planSearch(PlanVO planVO) {
		//데이터 확인
		System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanDate());
		System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanName());
				
		ArrayList<PlanVO> planList = searchService.planSearch(planVO);
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			str = mapper.writeValueAsString(planList);
			System.out.println("str=" + str);
		}
		catch(Exception e) {
			System.out.println("first() mapper :" + e.getMessage());
		}
		
		return str;
	}
	
	//최신순,조회순...클릭시 관련된 사항 뿌려주는 컨트롤러
	@RequestMapping(value = "clickSearch.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String clickSearch(PlanVO planVO) {
		//데이터 확인
		System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanDate());
		System.out.println("jsp에서 넘어오는 값?" + planVO.getReadCount());
		System.out.println("jsp에서 넘어오는 값?" + planVO.getGoodCount());
		System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanName());
		
		ArrayList<PlanVO> planList = searchService.clickSearch(planVO);
		String str = "";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			str = mapper.writeValueAsString(planList);
			System.out.println("str=" + str);
		}
		catch(Exception e) {
			System.out.println("first() mapper :" + e.getMessage());
		}
		
		return str;
	}
	
	//스케쥴페이지이동
	@RequestMapping("schedule.search")
	public String moveSchedule() {
		System.out.println("oo");
		
		
		
		/*UserVO vo = searchService.getUserId(userVO.getId());
		
		System.out.println(vo.getId());*/

		/*System.out.println("move1");
		searchService.moveSchedule();
		System.out.println("move4");*/
		
		
		
		return "search/schedule";
	}
	
	//login test
	@RequestMapping("login.search")
	public String moveLogin() {
		System.out.println("movelogin");
		return "search/login";
	}
	
	
	@RequestMapping("inpugLogin.search")
	public String inputLogin(HttpSession session, UserVO userVO) {
		System.out.println("sc");
		UserVO vo = searchService.getUserId(userVO.getId());
		System.out.println(vo.getId());
		session.setAttribute("checkID", vo.getId());
		return "search/searchPlanPage";
	}
	
	@RequestMapping("heartCheck.search")
	public String heartCheck(LikeVO likeVO) {
		System.out.println("like1");
		searchService.heartCheck(likeVO);
		System.out.println("like4");
		return "search/schedule";
	}
	

	
	
	
	

	
	
	
	/*// 좋아요 기능 테스트를 위한 추가
	@RequestMapping(value="/addGoodCount.search", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	@ResponseBody
	public String addGoodCount() {
		System.out.println("goodCount-here");
		return "";
	}*/
	
	/*@RequestMapping("together.search")
	public String togetherLoad() {
		return "search/searchTogetherPage";
	}*/
	
	/*@RequestMapping("myroom.search")
	public String myloomLoad() {
		return "search/searchMyRoomPage";
	}
	
	@RequestMapping("signup.search")
	public String signupLoad() {
		return "search/searchSignUpPage";
	}
	
	@RequestMapping("login.search")
	public String loginLoad() {
		return "search/searchLoginPage";
	}
	
	@RequestMapping("new.search")
	public String newLoad() {
		return "search/searchNewPage";
	}
	
	@RequestMapping("count.search")
	public String countLoad() {
		return "search/searchCountPage";
	}
	
	@RequestMapping("like.search")
	public String likeLoad() {
		return "search/searchLikePage";
	}
	
	@RequestMapping("date.search")
	public String dateLoad() {
		return "search/searchDatePage";
	}*/
}
	
