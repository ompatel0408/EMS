package com.bean;

public class ClientBean {
	
	private int clientId;
	private long phoneNumber;
	private String clientName;
	private String gstNo;
	private String email;
	private String panNo;
	private String address;
	private long phoneNumber1;
	private String email1;
	

	public ClientBean() {}
	
	public ClientBean(int clientId, String clientName) {
		this.clientId = clientId;
		this.clientName = clientName;
	}

	
	public long getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(long phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	
	public int getClientId() {
		return clientId;
	}
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
}
