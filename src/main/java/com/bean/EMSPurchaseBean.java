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
	private String projectName;
	private String ProjectId;
	private String category;
	private String grade;
	private String remarks;
	private String itemName;	
	
	public EMSPurchaseBean(String projectId,String category,int quantity,String uom,String remarks,String itemName,String grade,String size) {
		this.Size = size;
		this.quantity = quantity;
		this.Uom = uom;
		this.ProjectId = projectId;
		this.category = category;
		this.grade = grade;
		this.remarks = remarks;
		this.itemName = itemName;
	}

	public EMSPurchaseBean() {}

	public EMSPurchaseBean(String projectName,String productDescription, String size, int quantity, String uom, String ratePerKg,String discount, String netAmount, double sGST, double cGST, int indentId, String currentDate, String pONumber,String vendorName) {
		this.ProductDescription = productDescription;
		this.projectName = projectName; 
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
	
	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
