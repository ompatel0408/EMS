package com.bean;

public class EMSDirectorsDashboardBean {
	
	private String clientName;
	private String projectName;
	private int  workDonePercentage;
	
	public EMSDirectorsDashboardBean() {}
	
	
	public EMSDirectorsDashboardBean(String clientName, String projectName,int  workDonePercentage) {
		this.clientName = clientName;
		this.projectName = projectName;
		this.workDonePercentage = workDonePercentage;
	}
	

	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
