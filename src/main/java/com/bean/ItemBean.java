package com.bean;

public class ItemBean {
	
	
	// Real Fields	
	private String ItemName;
    private int Quantity;
    private String tagNo;
    private String remarks;
    private String totalPrice;
    private String date;
    private int clientId;
    private String projectId;
    private String ItemCode;
    private int quotationId;
    private String drawingId;
	private String clientName;
    private String DeliveryDate;
	


	public ItemBean() {
		// TODO Auto-generated constructor stub
	}
    
    public ItemBean(int clientId,String ItemCode,int quotationId,String drawingId,String ItemName,int Quantity,String tagNo,String remarks,String totalPrice,String date) {
    	this.ItemName = ItemName;
    	this.Quantity = Quantity;
    	this.tagNo = tagNo;
    	this.remarks = remarks;
    	this.totalPrice = totalPrice;
    	this.date = date;
    	this.clientId = clientId;
    	this.ItemCode = ItemCode;
    	this.quotationId = quotationId;
    	this.drawingId = drawingId;	
    }
    
    public ItemBean(String clientName,int clientId,String ItemCode,int quotationId,String drawingId,String ItemName,int Quantity,String tagNo,String remarks,String totalPrice,String date) {
    	this.clientName =clientName;
    	this.ItemName = ItemName;
    	this.Quantity = Quantity;
    	this.tagNo = tagNo;
    	this.remarks = remarks;
    	this.totalPrice = totalPrice;
    	this.date = date;
    	this.clientId = clientId;
    	this.ItemCode = ItemCode;
    	this.quotationId = quotationId;
    	this.drawingId = drawingId;	
    }
    
    
    public ItemBean(int clientId,int quotationId) {
    	this.quotationId = quotationId;
    	this.clientId = clientId;
    }
    
    
    public String getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		DeliveryDate = deliveryDate;
	}

    
    public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
    
    
    public String getProjectId() {
		return projectId;
	}

	public void setprojectId(String projectId) {
		this.projectId = projectId;
	}
    
    
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getItemCode() {
		return ItemCode;
	}

	public void setItemCode(String itemCode) {
		this.ItemCode = itemCode;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getDrawingId() {
		return drawingId;
		
	}

	public void setDrawingId(String drawingId) {
		this.drawingId = drawingId;
	}

	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		this.ItemName = itemName;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		this.Quantity = quantity;
	}
	
	public String getTagNo() {
		return tagNo;
	}
	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	} 
    
}
