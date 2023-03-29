package com.bean;

public class EMSOffersBean {
	
	private int clientId,quantity,quotationId;
	private String offerName,remarks,offerCode,TotalPrice,drawingId;
	
	
	public EMSOffersBean(int clientId, int quantity, int quotationId, String offerName, String remarks, String offerCode, String totalPrice, String drawingId) {
		this.clientId = clientId;
		this.quantity = quantity;
		this.quotationId = quotationId;
		this.offerName = offerName;
		this.remarks = remarks;
		this.offerCode = offerCode;
		this.TotalPrice = totalPrice;
		this.drawingId = drawingId;
	}
	

	public EMSOffersBean() {}
	
	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}

	public String getDrawingId() {
		return drawingId;
	}

	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}

	public EMSOffersBean(String offerName,int quantity,String remarks) {
		this.quantity = quantity;
		this.offerName = offerName;
		this.remarks = remarks;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
