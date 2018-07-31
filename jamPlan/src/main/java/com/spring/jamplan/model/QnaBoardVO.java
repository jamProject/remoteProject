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
	private int boardNo;
	private int boardReadCount;
	
	private String id;
	private String boardPass;
	private String boardSubject;
	private String boardContent;
	private String boardReRef;
	private String boardReSeq;
	private String boardReLev;
	private String boardFile;
	private String boardImage;
	private DateFormat boardDate;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardReadCount() {
		return boardReadCount;
	}
	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardReRef() {
		return boardReRef;
	}
	public void setBoardReRef(String boardReRef) {
		this.boardReRef = boardReRef;
	}
	public String getBoardReSeq() {
		return boardReSeq;
	}
	public void setBoardReSeq(String boardReSeq) {
		this.boardReSeq = boardReSeq;
	}
	public String getBoardReLev() {
		return boardReLev;
	}
	public void setBoardReLev(String boardReLev) {
		this.boardReLev = boardReLev;
	}
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
	public String getBoardImage() {
		return boardImage;
	}
	public void setBoardImage(String boardImage) {
		this.boardImage = boardImage;
	}
	public DateFormat getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(DateFormat boardDate) {
		this.boardDate = boardDate;
	}
	
}
