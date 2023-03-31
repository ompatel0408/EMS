package com.bean;

public class EMSPurchaseBean {
	
	private String  ProductDescription;
	private String Size;
	private int quantity;
	private String Uom;
	private String RatePerKg;
	private String discount;
	private String netAmount;
	private double SGST;
	private double CGST;
	private int IndentId;
	private String CurrentDate;
	private String PONumber;
	private String VendorName;
	private int poId;
	
	public EMSPurchaseBean() {}
	
	
	public EMSPurchaseBean(String productDescription, String size, int quantity, String uom, String ratePerKg,String discount, String netAmount, double sGST, double cGST, int indentId, String currentDate, String pONumber,String vendorName) {
		this.ProductDescription = productDescription;
		this.Size = size;
		this.quantity = quantity;
		this.Uom = uom;
		this.RatePerKg = ratePerKg;
		this.discount = discount;
		this.netAmount = netAmount;
		this.SGST = sGST;
		this.CGST = cGST;
		this.IndentId = indentId;
		this.CurrentDate = currentDate;
		this.PONumber = pONumber;
		this.VendorName = vendorName;
	}
	
	
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	public String getProductDescription() {
		return ProductDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.ProductDescription = productDescription;
	}
	
	public String getSize() {
		return Size;
	}
	
	public void setSize(String size) {
		this.Size = size;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getUom() {
		return Uom;
	}
	
	public void setUom(String uom) {
		this.Uom = uom;
	}
	
	public String getRatePerKg() {
		return RatePerKg;
	}
	
	public void setRatePerKg(String ratePerKg) {
		this.RatePerKg = ratePerKg;
	}
	
	public String getDiscount() {
		return discount;
	}
	
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getNetAmount() {
		return netAmount;
	}
	
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	
	public double getSGST() {
		return SGST;
	}
	
	public void setSGST(double sGST) {
		this.SGST = sGST;
	}
	
	public double getCGST() {
		return CGST;
	}
	
	public void setCGST(double cGST) {
		this.CGST = cGST;
	}
	
	public int getIndentId() {
		return IndentId;
	}
	
	public void setIndentId(int indentId) {
		this.IndentId = indentId;
	}
	
	public String getCurrentDate() {
		return CurrentDate;
	}
	
	public void setCurrentDate(String currentDate) {
		this.CurrentDate = currentDate;
	}
	
	public String getPONumber() {
		return PONumber;
	}
	
	public void setPONumber(String pONumber) {
		this.PONumber = pONumber;
	}
	
	public String getVendorName() {
		return VendorName;
	}
	
	public void setVendorName(String vendorName) {
		this.VendorName = vendorName;
	}
}
