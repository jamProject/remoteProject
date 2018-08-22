package com.spring.jamplan.myinfo;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyInfoController {
	
	
	@RequestMapping(value = "/myInfo.mi")
	public String getMyInfo(HttpSession session) {

		System.out.println("실행됨");
		
		String id = (String) session.getAttribute("id");
		int planNo = (int) session.getAttribute("planNo");
		
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("planNo", planNo);
		
		teamVO = mpDAOS.getPlanRole(map);
		session.setAttribute("role", teamVO.getRole());

		return "myInfo/infoPage";
	}
}
