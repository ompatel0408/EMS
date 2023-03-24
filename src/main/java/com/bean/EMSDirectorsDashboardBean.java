package com.bean;

public class EMSDirectorsDashboardBean {
	
	private String clientName;
	private String projectName;
	
	public EMSDirectorsDashboardBean() {}
	
	
	public EMSDirectorsDashboardBean(String clientName, String projectName) {
		this.clientName = clientName;
		this.projectName = projectName;
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
