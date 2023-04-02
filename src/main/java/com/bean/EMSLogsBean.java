package com.bean;

public class EMSLogsBean {
	
	private String LOGS;
	private int userId;
	private String category;
	private String departmentName;
	
	public EMSLogsBean(String lOGS, int userId, String category, String departmentName) {
		this.LOGS = lOGS;
		this.userId = userId;
		this.category = category;
		this.departmentName = departmentName;
	}

	public String getLOGS() {
		return LOGS;
	}

	public void setLOGS(String lOGS) {
		this.LOGS = lOGS;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
