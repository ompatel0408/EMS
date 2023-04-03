package com.bean;

public class EMSDrawingBean {
	
	private String projectId;
	private String drawingId;
	private int ClientId;
	private String ClientDrawing;
	private String EMSDrawing;
	private String offerCode;
	

	public EMSDrawingBean(String projectId, String drawingId, int clientId, String clientDrawing,String eMSDrawing) {
		this.projectId = projectId;
		this.drawingId = drawingId;
		this.ClientId = clientId;
		this.ClientDrawing = clientDrawing;
		this.EMSDrawing = eMSDrawing;
	}
	
	public EMSDrawingBean(String drawingId ,String ClientDrawing,String eMSDrawing, String projectId, String offerCode) {
		this.projectId = projectId;
		this.drawingId = drawingId;
		this.ClientDrawing = ClientDrawing;
		this.offerCode =offerCode;
		this.EMSDrawing = eMSDrawing;
	}
	
	
	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public EMSDrawingBean() {}
	
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
	public int getClientId() {
		return ClientId;
	}
	public void setClientId(int clientId) {
		ClientId = clientId;
	}
	public String getClientDrawing() {
		return ClientDrawing;
	}
	public void setClientDrawing(String clientDrawing) {
		ClientDrawing = clientDrawing;
	}
	public String getEMSDrawing() {
		return EMSDrawing;
	}
	public void setEMSDrawing(String eMSDrawing) {
		EMSDrawing = eMSDrawing;
	}
	
	
	
	
}
