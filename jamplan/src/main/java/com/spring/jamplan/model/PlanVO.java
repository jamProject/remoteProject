package com.spring.jamplan.model;

<<<<<<< HEAD
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

=======
import java.text.DateFormat;

import org.springframework.stereotype.Component;
>>>>>>> 6ddd09edfe1ad83f2b818f9ba9b95e094714e95d

/**
 * @author wookim
 * @param 
<<<<<<< HEAD
<<<<<<< HEAD
 *	
*/

	
@Component
public class PlanVO {
   private int planNo;
   private int teamNo;
   private int goodCount;
   private int readCount;
   private int isOpen;
   private String image;
   private String planName;
   private String planDate;
   private String hashTag;
public int getPlanNo() {
	return planNo;
}
public void setPlanNo(int planNo) {
	this.planNo = planNo;
}
public int getTeamNo() {
	return teamNo;
}
public void setTeamNo(int teamNo) {
	this.teamNo = teamNo;
}
public int getGoodCount() {
	return goodCount;
}
public void setGoodCount(int goodCount) {
	this.goodCount = goodCount;
}
public int getReadCount() {
	return readCount;
}
public void setReadCount(int readCount) {
	this.readCount = readCount;
}
public int getIsOpen() {
	return isOpen;
}
public void setIsOpen(int isOpen) {
	this.isOpen = isOpen;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getPlanName() {
	return planName;
}
public void setPlanName(String planName) {
	this.planName = planName;
}
public String getPlanDate() {
	return planDate;
}
public void setPlanDate(String planDate) {
	this.planDate = planDate;
}
public String getHashTag() {
	return hashTag;
}
public void setHashTag(String hashTag) {
	this.hashTag = hashTag;
}
   

=======
 *	
 */
@Component
public class PlanVO {
	private int planNo;
	private int teamNo;
	private int goodCount;
	private int readCount;
	
	private String isOpen;
	
	private String planName;
	private String hashTag;
	
	private String planDate;

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public int getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	
	
	
>>>>>>> 6ddd09edfe1ad83f2b818f9ba9b95e094714e95d
}
