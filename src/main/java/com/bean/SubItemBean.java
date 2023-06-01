package com.bean;

public class SubItemBean {
	String itemcode;
	String subitemcode;
	String projectId;
	String ems;
	public String getEms() {
		return ems;
	}

	public void setEms(String ems) {
		this.ems = ems;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	String client;
	
	public SubItemBean(String projectId, String itemcode, String subitemcode,String client,String ems) {
		this.projectId = projectId;
		this.itemcode = itemcode;
		this.subitemcode = subitemcode;
		this.client=client;
		this.ems=ems;
	}
	
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
