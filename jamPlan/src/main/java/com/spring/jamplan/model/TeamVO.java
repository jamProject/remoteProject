package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class TeamVO {
	int teamNo;
	int planNo;
	
	String id;
	String teamName;
	String role;
	String isTeamLeader;

	int chatTime;
}
