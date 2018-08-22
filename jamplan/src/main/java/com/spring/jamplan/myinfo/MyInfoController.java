package com.spring.jamplan.myinfo;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.jamplan.model.UserVO;

@Controller
public class MyInfoController {
	
	@Autowired(required=false)
	private MyInfoDAO myInfoDAO;
	
	@RequestMapping(value="myInfo.mi")
	public String getMyInfo(HttpSession session) {

		System.out.println("실행됨");
		
//		String id = (String) session.getAttribute("id");
//		int teamNo = (int) session.getAttribute("teamNo");
		
//		map = new HashMap<String, Object>();
//		map.put("id", id);
//		map.put("planNo", planNo);
//		
//		teamVO = mpDAOS.getPlanRole(map);
//		session.setAttribute("role", teamVO.getRole());

		return "myInfo/infoPage";
	}
	
	@RequestMapping(value="imageUpload.mi", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(MultipartHttpServletRequest multiRequest, UserVO user) throws Exception {
		System.out.println("1");
		
		MultipartFile mf = multiRequest.getFile("image");
		// 해당 경로에 지정해준 이름의 폴더가 없으면 만들어주게된다.
		String uploadPath = "C:\\BigDeep\\upload\\";
				//"C:\\Users\\Playdata\\Downloads\\0805ProjectHan\\jamplan2\\src\\main\\webapp\\resources\\search\\image\\";
		
		// 실제 파일의 확장자
		String originalFileExtension = mf.getOriginalFilename().substring(
				mf.getOriginalFilename().lastIndexOf("."));
		// 실제 파일명
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
		System.out.println("storedFileName=" + storedFileName);
		if(mf.getSize() != 0) {
			mf.transferTo(new File(uploadPath+storedFileName));
			user.setImage(storedFileName);
		}
		myInfoDAO.setProfileImage(user);
		String imagePath = user.getImage();
		
		return imagePath;
	}
}
