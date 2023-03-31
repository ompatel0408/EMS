package com.bean;

public class EMSGRNBean {
	
	private String VendorName;
	private String ReceiveDate;
	private String path1;
	private String path2;
	private int GRNID;
	
	public EMSGRNBean() {}
	
	public EMSGRNBean(String vendorName, String receiveDate) {
		this.VendorName = vendorName;
		this.ReceiveDate = receiveDate;
	}	
	
	public EMSGRNBean(int GRNID,String vendorName, String receiveDate,String path1,String path2) {
		this.GRNID = GRNID;
		this.VendorName = vendorName;
		this.ReceiveDate = receiveDate;
		this.path1 = path1;
		this.path2 = path2;
	}	
	
	public EMSGRNBean(String vendorName, String receiveDate,String path1,String path2) {
		this.VendorName = vendorName;
		this.ReceiveDate = receiveDate;
		this.path1 = path1;
		this.path2 = path2;
	}	
	public int getGRNID() {
		return GRNID;
	}

	public void setGRNID(int gRNID) {
		this.GRNID = gRNID;
	}

	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}

	
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		this.VendorName = vendorName;
	}
	public String getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.ReceiveDate = receiveDate;
	}
}
