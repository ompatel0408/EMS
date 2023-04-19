package com.bean;

public class EMSVendorsBean {
	
	private int vendorId;
	private String vendorName;
	private String address;
	private String email;
	private String mobile;
	private String email1;
	private String mobile1;
	private String gst;
	private String panNumber;
	private String bankName;
	private String ACNumber;
	private String IFSC;
	private String remarks;
	

	public EMSVendorsBean() {}
	
	public EMSVendorsBean(String vendorName, String address, String email, String mobile, String email1,String mobile1, String gst, String panNumber, String bankName, String aCNumber, String iFSC,String remarks) {
		this.vendorName = vendorName;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.email1 = email1;
		this.mobile1 = mobile1;
		this.gst = gst;
		this.panNumber = panNumber;
		this.bankName = bankName;
		this.ACNumber = aCNumber;
		this.IFSC = iFSC;
		this.remarks = remarks;
	}
	
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getACNumber() {
		return ACNumber;
	}
	public void setACNumber(String aCNumber) {
		ACNumber = aCNumber;
	}
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}	
