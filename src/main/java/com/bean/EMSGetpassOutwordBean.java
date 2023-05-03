package com.bean;

public class EMSGetpassOutwordBean 
{
	private String vendorName;
	private String matName;
	private int qty;
	private String issueDate;
	private String size;
	private String vehicleNo;
	private String address;
	private String remark;
	private int receive;
	private int received;
	
	public EMSGetpassOutwordBean() {}
	
	public EMSGetpassOutwordBean(String vendorName, String matName, int qty, String issueDate, String size,
			String vehicleNo, String address, String remark, int receive) {
		this.vendorName = vendorName;
		this.matName = matName;
		this.qty = qty;
		this.issueDate = issueDate;
		this.size = size;
		this.vehicleNo = vehicleNo;
		this.address = address;
		this.remark = remark;
		this.receive = receive;
	}
	
	public EMSGetpassOutwordBean(String vendorName, String matName, int qty, String issueDate, String size,
			String vehicleNo, String address, String remark, int receive, int received) {
		this.vendorName = vendorName;
		this.matName = matName;
		this.qty = qty;
		this.issueDate = issueDate;
		this.size = size;
		this.vehicleNo = vehicleNo;
		this.address = address;
		this.remark = remark;
		this.receive = receive;
		this.received = received;
	}

	public int getReceived() {
		return received;
	}
	
	public void setReceived(int received) {
		this.received = received;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getReceive() {
		return receive;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}
}
