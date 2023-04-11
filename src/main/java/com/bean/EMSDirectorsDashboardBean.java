package com.bean;

public class EMSDirectorsDashboardBean {
	
	private String clientName;
	private String projectName;
	private int  workDonePercentage;
	private String vendorName;
	private String PaymentTerms;
	private String sumTotalAmount;
	
	

	public EMSDirectorsDashboardBean() {}
	
	public EMSDirectorsDashboardBean(String clientName, String projectName,int  workDonePercentage) {
		this.clientName = clientName;
		this.projectName = projectName;
		this.workDonePercentage = workDonePercentage;
	}
	
	public EMSDirectorsDashboardBean(String vendorName,String sumTotalAmount) {
		this.vendorName = vendorName;
		this.sumTotalAmount = sumTotalAmount;
	}
	
	public String getsumTotalAmount() {
		return sumTotalAmount;
	}

	public void setsumTotalAmount(String sumTotalAmount) {
		this.sumTotalAmount = sumTotalAmount;
	}
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPaymentTerms() {
		return PaymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.PaymentTerms = paymentTerms;
	}

	public int getWorkDonePercentage() {
		return workDonePercentage;
	}


	public void setWorkDonePercentage(int workDonePercentage) {
		this.workDonePercentage = workDonePercentage;
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
