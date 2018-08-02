package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class UserVO {
	String id;
	String email;
	String pass;
	String nation;
	String gender;
	String snsLink;
	String travelType;
	String hobby;
	
	//image
	
	int age;
	
	boolean isAdmin;
	
	DateFormat signDate;
}
