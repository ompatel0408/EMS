package com.bean;

public class ProjectBean {

	// For Our convenience

//	private boolean isPresent;
//	
//	//Real Fields	
//	private String projectId;
//	private String clientPoId;
//	private String poDate;
//	private String clientName; // Client Name belongs to Client table in database
//	private Integer advancePayPercent;
//	private Integer afterPayPercent;
//	private Integer clientId;
//	
//	public boolean getPresent() {
//		return isPresent;
//	}
//	
//	public void setPresent(boolean isPresent) {
//		this.isPresent = isPresent;
//	}
//	
//	public String getProjectId() {
//		return projectId;
//	}
//	public void setProjectId(String projectId) {
//		this.projectId = projectId;
//	}
//	public String getClientPoId() {
//		return clientPoId;
//	}
//	public void setClientPoId(String clientPoId) {
//		this.clientPoId = clientPoId;
//	}
//	public String getPoDate() {
//		return poDate;
//	}
//	public void setPoDate(String poDate) {
//		this.poDate = poDate;
//	}
//	public Integer getAdvancePayPercent() {
//		return advancePayPercent;
//	}
//	public void setAdvancePayPercent(Integer advancePayPercent) {
//		this.advancePayPercent = advancePayPercent;
//	}
//	public Integer getAfterPayPercent() {
//		return afterPayPercent;
//	}
//	public void setAfterPayPercent(Integer afterPayPercent) {
//		this.afterPayPercent = afterPayPercent;
//	}
//	public Integer getClientId() {
//		return clientId;
//	}
//	public void setClientId(Integer clientId) {
//		this.clientId = clientId;
//	}
//	public String getClientName() {
//		return clientName;
//	}
//	public void setClientName(String clientName) {
//		this.clientName = clientName;
//	}

	String projectId, clientPoId, poDate, finalDeliveryDate, clientName; // Client Name belongs to Client table in
																			// database
	Integer advancePayPercent, afterPayPercent, clientId;
	Float progress;

	public Float getProgress() {
		return progress;
	}

	public void setProgress(Float progress) {
		this.progress = progress;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	

	public String getClientPoId() {
		return clientPoId;
	}

	public void setClientPoId(String clientPoId) {
		this.clientPoId = clientPoId;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getFinalDeliveryDate() {
		return finalDeliveryDate;
	}

	public void setFinalDeliveryDate(String finalDeliveryDate) {
		this.finalDeliveryDate = finalDeliveryDate;
	}

	public Integer getAdvancePayPercent() {
		return advancePayPercent;
	}

	public void setAdvancePayPercent(Integer advancePayPercent) {
		this.advancePayPercent = advancePayPercent;
	}

	public Integer getAfterPayPercent() {
		return afterPayPercent;
	}

	public void setAfterPayPercent(Integer afterPayPercent) {
		this.afterPayPercent = afterPayPercent;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

}