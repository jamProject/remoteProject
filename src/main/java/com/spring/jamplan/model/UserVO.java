package com.spring.jamplan.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class UserVO {
	String id;
	String email;
	String pass;
	String nation;
	String gender;
	String snsLink;
	String travelType;
	String hobby;
	
	//image
	
	int age;
	
	int isAdmin;
	
	String signDate;
	
	public void printElement() {
		System.out.println(id);
		System.out.println(pass);
		System.out.println(email);
		System.out.println(nation);
		System.out.println(gender);
		System.out.println(snsLink);
		System.out.println(travelType);
		System.out.println(hobby);
		System.out.println(age);
		System.out.println(isAdmin);
		System.out.println(signDate);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSnsLink() {
		return snsLink;
	}

	public void setSnsLink(String snsLink) {
		this.snsLink = snsLink;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
}
