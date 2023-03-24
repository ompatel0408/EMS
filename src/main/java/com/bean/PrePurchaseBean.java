package com.bean;

public class PrePurchaseBean {
	
	private String projectId;
	private String drawingId;
	
	public PrePurchaseBean() {}
	
	public PrePurchaseBean(String projectId,String drawingId) {
		this.projectId = projectId;
		this.drawingId = drawingId;
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
