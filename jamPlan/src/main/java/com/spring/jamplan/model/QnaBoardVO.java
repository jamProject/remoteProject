package com.spring.jamplan.model;

import java.text.DateFormat;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class QnaBoardVO {
	int boardNo;
	
	String id;
	String boardPass;
	String boardSubject;
	String boardContent;
	String boardReRef;
	String boardReSeq;
	String boardReLev;
	// boardFile;
	// boardImage;
	DateFormat boardDate;
	
}
