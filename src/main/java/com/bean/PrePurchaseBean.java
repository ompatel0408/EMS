package com.bean;

public class PrePurchaseBean {
	
	private String projectId;
	private String drawingId;
	private int clientId;
	
	public PrePurchaseBean() {}
	
	public PrePurchaseBean(String projectId,String drawingId) {
		this.projectId = projectId;
		this.drawingId = drawingId;
	}
	
	
	
	public PrePurchaseBean(String drawingId, int clientId) {
		this.drawingId = drawingId;
		this.clientId = clientId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	public String getDrawingId() {
		return drawingId;
	}
	
	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}
	
	
}
