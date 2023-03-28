package com.bean;

public class QuotationBean {
	
	private int quotationId;
	private String projectId;
	private String QuotationDate;
	private long QuotationAmount;
	private String finalDeliveryDate;
	
	public QuotationBean() {}
	
	public QuotationBean(String projectId,String QuotationDate,long QuotationAmount,String finalDeliveryDate) {
		this.projectId = projectId;
		this.QuotationDate = QuotationDate;
		this.QuotationAmount = QuotationAmount;
		this.finalDeliveryDate = finalDeliveryDate;
	}
	
	public QuotationBean(String projectId,String QuotationDate) {
		this.projectId = projectId;
		this.QuotationDate = QuotationDate;
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
		return QuotationDate;
	}
	
	public void setQuotationDate(String quotationDate) {
		QuotationDate = quotationDate;
	}
	
	public long getQuotationAmount() {
		return QuotationAmount;
	}
	
	public void setQuotationAmount(long quotationAmount) {
		QuotationAmount = quotationAmount;
	}
	
	public String getFinalDeliveryDate() {
		return finalDeliveryDate;
	}
	
	public void setFinalDeliveryDate(String finalDeliveryDate) {
		this.finalDeliveryDate = finalDeliveryDate;
	}
}
