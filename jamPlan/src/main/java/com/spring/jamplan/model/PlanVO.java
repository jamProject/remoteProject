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
	
	boolean isOpen;
	
	String planName;
	String hashTag;
	
	DateFormat planDate;
	
}
