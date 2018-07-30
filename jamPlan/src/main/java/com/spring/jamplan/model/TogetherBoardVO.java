package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class TogetherBoardVO {
	int togetherNo;
	int planNo;
	int ReadCount;
	
	String id;
	String togetherSubject;
	String togetherContent;

	DateFormat togetherDate;
	
	//file;
	//togetherImage;
}
