package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class PlanVO {
	int planNo;
	int teamNo;
	int goodCount;
	int readCount;
	
	int isOpen;
	
	String planName;
	String hashTag;
	
	String planDate;
	
}
