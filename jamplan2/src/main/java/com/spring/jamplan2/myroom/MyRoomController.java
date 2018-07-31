package com.spring.jamplan2.myroom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyRoomController {
	
	@RequestMapping("/load.room")
	public String MyRoomLoad(HttpServletRequest request) {
		System.out.println("test");
		return "myRoom/myRoomPage";
	}
}