package com.bean;

public class EMSGRNBean {
	
	private String VendorName;
	private String ReceiveDate;
	private String path1;
	private String path2;
	private int GRNID;
	private  String invoiceNumber;
	private String projectId;
	private String itemCode;
	private String categoryName;
	private String gradeName;
	private String size;
	private int quantity;
	private String units;
	

	public EMSGRNBean() {}
	
	public EMSGRNBean(String projectId, String itemCode, String categoryName, String gradeName, String size,int quantity, String units) {
		this.projectId = projectId;
		this.itemCode = itemCode;
		this.categoryName = categoryName;
		this.gradeName = gradeName;
		this.size = size;
		this.quantity = quantity;
		this.units = units;
	}
	
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
	
	public EMSGRNBean(String vendorName, String receiveDate,String path1,String path2,String invoiceNumber) {
		this.VendorName = vendorName;
		this.ReceiveDate = receiveDate;
		this.path1 = path1;
		this.path2 = path2;
		this.invoiceNumber = invoiceNumber;
	}	
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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
