package com.bean;

public class QuotationPerItemBean {
	
	private String itemId;
	private int catagoryId;
	private String gradeName;
	private String size;
	private double quantity;
	private String units;
	private String weight;
	private String pricePerItem;
	private String totalPricePerItem;
	private String profitPercentage;
	private String TotalAmountWithoutProfit;
	private String TotalAmountWithProfit;
	
	
	public QuotationPerItemBean() {}
	
	public QuotationPerItemBean(String itemId, int catagoryId, String gradeName, String size, int quantity,String units, String weight, String pricePerItem) {
		this.itemId = itemId;
		this.catagoryId = catagoryId;
		this.gradeName = gradeName;
		this.size = size;
		this.quantity = quantity;
		this.units = units;
		this.weight = weight;
		this.pricePerItem = pricePerItem;
	}
	
	
	public QuotationPerItemBean(String itemCode,String TotalAmountWithoutProfit) {
		this.itemId = itemCode;
		this.TotalAmountWithoutProfit = TotalAmountWithoutProfit;
	}
	
	public QuotationPerItemBean(String itemId,String TotalAmountWithoutProfit,String TotalAmountWithProfit) {
		this.itemId = itemId;
		this.TotalAmountWithoutProfit = TotalAmountWithoutProfit;
		this.TotalAmountWithProfit = TotalAmountWithProfit;
	}

	public QuotationPerItemBean(String itemId,  int catagoryId,double quantity,String weight, String units,String pricePerItem,String profitPercentage,String totalPricePerItem) {
		this.itemId = itemId;
		this.catagoryId = catagoryId;
		this.quantity = quantity;
		this.units = units;
		this.weight = weight;
		this.pricePerItem = pricePerItem;
		this.totalPricePerItem = totalPricePerItem;
		this.profitPercentage = profitPercentage;
	}

	
	public String getTotalAmountWithoutProfit() {
		return TotalAmountWithoutProfit;
	}
	public void setTotalAmountWithoutProfit(String totalAmountWithoutProfit) {
		this.TotalAmountWithoutProfit = totalAmountWithoutProfit;
	}

	public String getTotalAmountWithProfit() {
		return TotalAmountWithProfit;
	}
	public void setTotalAmountWithProfit(String totalAmountWithProfit) {
		this.TotalAmountWithProfit = totalAmountWithProfit;
	}
	
	public String getTotalPricePerItem() {
		return totalPricePerItem;
	}

	public void setTotalPricePerItem(String totalPricePerItem) {
		this.totalPricePerItem = totalPricePerItem;
	}


	public String getProfitPercentage() {
		return profitPercentage;
	}
	
	public void setprofitPercentage(String profitPercentage) {
		this.profitPercentage = profitPercentage;
	}
	
	
	public String getItemId() {
		return itemId;
	}
	
	public void setItemId(String itemName) {
		this.itemId = itemName;
	}
	
	public int getCatagoryId() {
		return catagoryId;
	}
	
	public void setCatagoryId(int catagoryId) {
		this.catagoryId = catagoryId;
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
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public String getUnits() {
		return units;
	}
	
	public void setUnits(String units) {
		this.units = units;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getpricePerItem() {
		return pricePerItem;
	}
	
	public void setpricePerItem(String pricePerItem) {
		this.pricePerItem = pricePerItem;
	}
}
