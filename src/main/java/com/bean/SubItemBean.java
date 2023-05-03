package com.bean;

public class SubItemBean {
	String itemcode;
	String subitemcode;
	String projectId;
	
	public SubItemBean(String projectId, String itemcode, String subitemcode) {
		this.projectId = projectId;
		this.itemcode = itemcode;
		this.subitemcode = subitemcode;
	}

	public SubItemBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getSubitemcode() {
		return subitemcode;
	}

	public void setSubitemcode(String subitemcode) {
		this.subitemcode = subitemcode;
	}

}
