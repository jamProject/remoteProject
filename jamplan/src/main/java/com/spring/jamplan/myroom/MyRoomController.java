package com.spring.jamplan.myroom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyRoomController {
	
	@RequestMapping("/load.room")
	public String myRoomLoad(Model model) {
		System.out.println("test");
		return "myRoom/myRoomPage";
	}
	@RequestMapping("/test.room")
	public String myRoomTest(Model model) {
		System.out.println("test");
		return "myRoom/myRoomPage";
	}
}