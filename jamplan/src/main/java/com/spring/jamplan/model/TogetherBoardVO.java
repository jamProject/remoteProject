package com.spring.jamplan.model;


import java.util.Date;

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
	Date togetherDate;
	
	//file;
	//togetherImage;
}
