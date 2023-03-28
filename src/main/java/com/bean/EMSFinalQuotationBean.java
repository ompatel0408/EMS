package com.bean;

public class EMSFinalQuotationBean {
	
	private int quotationId;
	private String projectId;
	private String quotationDate;
	private String quotationAmount;
	private String finalDelivaryDate;
	private int quantity;
	private String discountPercentage;
	private String discountAmount;
	private String remarks;
	
	
	public EMSFinalQuotationBean(int quotationId, String projectId, String quotationDate, String quotationAmount,String finalDelivaryDate, int quantity, String discountPercentage, String discountAmount) {
		this.quotationId = quotationId;
		this.projectId = projectId;
		this.quotationDate = quotationDate;
		this.quotationAmount = quotationAmount;
		this.finalDelivaryDate = finalDelivaryDate;
		this.quantity = quantity;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
	}
	
	
	public EMSFinalQuotationBean(String quotationAmount,String finalDelivaryDate,int Quantity,String discountPercentage,String discountAmount,String projectId,String remarks) {
		this.quotationAmount = quotationAmount;
		this.finalDelivaryDate = finalDelivaryDate;
		this.quantity = Quantity;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.projectId = projectId;
		this.remarks = remarks;
	}
	
	public EMSFinalQuotationBean(int quantity,String quotationAmount) {
		this.quantity = quantity;
		this.quotationAmount = quotationAmount;
	}

	

	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getQuotationId() {
		return quotationId;
	}
	
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	
	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getQuotationDate() {
		return quotationDate;
	}
	
	public void setQuotationDate(String quotationDate) {
		this.quotationDate = quotationDate;
	}
	
	public String getQuotationAmount() {
		return quotationAmount;
	}
	
	public void setQuotationAmount(String quotationAmount) {
		this.quotationAmount = quotationAmount;
	}
	
	public String getFinalDelivaryDate() {
		return finalDelivaryDate;
	}
	
	public void setFinalDelivaryDate(String finalDelivaryDate) {
		this.finalDelivaryDate = finalDelivaryDate;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	public String getDiscountAmount() {
		return discountAmount;
	}
	
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
