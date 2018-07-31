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
	private int togetherNo;
	private int planNo;
	private int readCount;
	
	private String id;
	private String togetherSubject;
	private String togetherContent;

	// 동행 일정 게시 날짜
	private DateFormat togetherDate;

	public int getTogetherNo() {
		return togetherNo;
	}

	public void setTogetherNo(int togetherNo) {
		this.togetherNo = togetherNo;
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTogetherSubject() {
		return togetherSubject;
	}

	public void setTogetherSubject(String togetherSubject) {
		this.togetherSubject = togetherSubject;
	}

	public String getTogetherContent() {
		return togetherContent;
	}

	public void setTogetherContent(String togetherContent) {
		this.togetherContent = togetherContent;
	}

	public DateFormat getTogetherDate() {
		return togetherDate;
	}

	public void setTogetherDate(DateFormat togetherDate) {
		this.togetherDate = togetherDate;
	}
	
	
}
