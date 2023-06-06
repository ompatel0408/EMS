package com.bean;

public class EMSConsumablePurchaseBean {
	
	private Integer purchaseId;
	private String categoryName;
	private String itemName;
	private Integer quantity;
	private Integer vendor;
	private Double rate;
	private Double discount;
	private Float sgst;
	private Float cgst;
	private String currentDate;
	private String remarks;
	private Double totalAmount;
	
	public EMSConsumablePurchaseBean() {}
	
	public EMSConsumablePurchaseBean(String categoryName, String itemName, Integer quantity,Integer vendor, Double rate, Double discount, Float sgst, Float cgst, String currentDate, String remarks,Double totalAmount) {
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.quantity = quantity;
		this.vendor = vendor;
		this.rate = rate;
		this.discount = discount;
		this.sgst = sgst;
		this.cgst = cgst;
		this.currentDate = currentDate;
		this.remarks = remarks;
		this.totalAmount = totalAmount;
	}

	
	public Integer getPurchaseId() {
		return purchaseId;
	}

	
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	
	public String getCategoryName() {
		return categoryName;
	}

	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public String getItemName() {
		return itemName;
	}

	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getVendor() {
		return vendor;
	}

	public void setVendor(Integer vendor) {
		this.vendor = vendor;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	
	
}
