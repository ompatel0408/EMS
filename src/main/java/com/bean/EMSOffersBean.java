package com.bean;

public class EMSOffersBean {
	
	private int clientId,quantity,quotationId;
	private String offerName,remarks,offerCode,TotalPrice,drawingId,clientName;
	
	public EMSOffersBean(String clientName,int clientId, int quantity, int quotationId, String offerName, String remarks, String offerCode, String totalPrice, String drawingId) {
		this.clientName = clientName;
		this.clientId = clientId;
		this.quantity = quantity;
		this.quotationId = quotationId;
		this.offerName = offerName;
		this.remarks = remarks;
		this.offerCode = offerCode;
		this.TotalPrice = totalPrice;
		this.drawingId = drawingId;
	}
	
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
	
	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public EMSOffersBean(String offerName,String offerCode,int quantity,String remarks) {
		this.quantity = quantity;
		this.offerName = offerName;
		this.offerCode = offerCode;
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
