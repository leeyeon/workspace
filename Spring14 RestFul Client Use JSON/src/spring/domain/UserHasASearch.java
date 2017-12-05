package spring.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * FileName : Search.java
 *   ㅇ Dynamic SQL 구성시  <foreach> elements 를 이용 반복적 구문생성시 전달되는 
 *       Collection List , Array 갖는 ValueObeject  
  */
public class UserHasASearch {
	///Field
	private String userName;
	private String userId;
	private String password;
	private Integer age;
	private int grade;
	private Timestamp regDate;
	private boolean active;
	
	private Search search;
	
	///Constructor
	public UserHasASearch() {
	}


	public UserHasASearch(String userName, String userId, String password, Integer age, int grade) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		this.age = age;
		this.grade = grade;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public Timestamp getRegDate() {
		return regDate;
	}


	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Search getSearch() {
		return search;
	}


	public void setSearch(Search search) {
		this.search = search;
	}


	@Override
	public String toString() {
		return "UserHasASearch [userName=" + userName + ", userId=" + userId + ", password=" + password + ", age=" + age
				+ ", grade=" + grade + ", regDate=" + regDate + ", active=" + active + ", search=" + search + "]";
	}


}//end of class