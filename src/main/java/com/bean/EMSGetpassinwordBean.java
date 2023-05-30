package com.bean;

public class EMSGetpassinwordBean 
{
	private String vendor;
	private String itemName;
	private int qty;
	private String vehicleNo;
	private String receiveDate;
	private String remark;
	
	public EMSGetpassinwordBean() {}
	
	public EMSGetpassinwordBean(String vendor, String itemName, int qty, String vehicleNo, String receiveDate,
			String remark) {
		this.vendor = vendor;
		this.itemName = itemName;
		this.qty = qty;
		this.vehicleNo = vehicleNo;
		this.receiveDate = receiveDate;
		this.remark = remark;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
