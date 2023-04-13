package com.bean;

public class QuotationBean {
	
	private int quotationId;
	private String projectId;
	private int clientId;
	private String QuotationDate;
	private long QuotationAmount;
	private String finalDeliveryDate;
	private double remainingAmount;
	private double QuotationAmountForNotify;

	public QuotationBean() {}
	
	public QuotationBean(String projectId,String QuotationDate,long QuotationAmount,String finalDeliveryDate) {
		this.projectId = projectId;
		this.QuotationDate = QuotationDate;
		this.QuotationAmount = QuotationAmount;
		this.finalDeliveryDate = finalDeliveryDate;
	}
	
	public QuotationBean(int clientId,String QuotationDate) {
		this.clientId = clientId;
		this.QuotationDate = QuotationDate;
	}
	
	public QuotationBean(String projectId,double QuotationAmountForNotify,double remainingAmount) {
		this.projectId = projectId;
		this.QuotationAmountForNotify = QuotationAmountForNotify;
		this.remainingAmount = remainingAmount;
	}
	
	

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public double getQuotationAmountForNotify() {
		return QuotationAmountForNotify;
	}

	public void setQuotationAmountForNotify(double quotationAmountForNotify) {
		QuotationAmountForNotify = quotationAmountForNotify;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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
