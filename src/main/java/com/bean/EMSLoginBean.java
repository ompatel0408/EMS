package com.bean;


public class EMSLoginBean {
	
	private static String name;
	private static String email;
	private static String password;
	private static long phoneNumber;
	private static String departmentName;
	private static int role;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		EMSLoginBean.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		EMSLoginBean.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		EMSLoginBean.password = password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		EMSLoginBean.phoneNumber = phoneNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		EMSLoginBean.departmentName = departmentName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		EMSLoginBean.role = role;
	}
}
