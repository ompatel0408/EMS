package com.bean;


public class EMSLoginBean {
	
	private String name;
	private String email;
	private String password;
	private long phoneNumber;
	private String departmentName;
	private int role;
	private String token;
	private String secretKey;
	private int userId;
	
	public EMSLoginBean() {}
	
	public EMSLoginBean(String token, String secretKey) {	
		this.token = token;
		this.secretKey = secretKey;
	}
	public EMSLoginBean(String email,int role) {
		this.email = email;
		this.role = role;
	}
	
	public EMSLoginBean(int userId,String secretKey) {
		this.userId = userId;
		this.secretKey = secretKey;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public EMSLoginBean(String name,String email,int role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public EMSLoginBean(String email,String password,String secretKey) {
		this.email = email;
		this.password = password;
		this.secretKey = secretKey;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
