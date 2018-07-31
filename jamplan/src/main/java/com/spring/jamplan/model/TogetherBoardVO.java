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
	int readCount;
	
	String id;
	String togetherSubject;
	String togetherContent;

	// 동행 일정 게시 날짜
	DateFormat togetherDate;
	
	//file;
	//togetherImage;
}
