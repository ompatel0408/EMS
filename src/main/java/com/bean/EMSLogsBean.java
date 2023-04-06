package com.bean;

public class EMSLogsBean {
	
	private String LOGS;
	private int userId;
	private String category;
	private String departmentName;
	private int logId;
	private String currentTime;
	private String userName;
	
	public EMSLogsBean(String lOGS, int userId, String category, String departmentName) {
		this.LOGS = lOGS;
		this.userId = userId;
		this.category = category;
		this.departmentName = departmentName;
	}
	
	public EMSLogsBean(String lOGS, int userId, String category, String departmentName, int logId, String currentTime,String userName) {
		this.LOGS = lOGS;
		this.userId = userId;
		this.category = category;
		this.departmentName = departmentName;
		this.logId = logId;
		this.currentTime = currentTime;
		this.userName  = userName;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
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
