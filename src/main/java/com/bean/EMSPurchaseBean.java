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
	private int ITEMCATAGORY;
	private int GradeId;
	private int SIZEId;
	private String paymentTerms;
	private double taxableValue;
	private double totalAmount;
	private double transportPrice;
	

	public double getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(double transportPrice) {
		this.transportPrice = transportPrice;
	}
	

	public EMSPurchaseBean(String projectId,String category,int quantity,String uom,String remarks,String itemName,String grade,String size,int indentId) {
		this.Size = size;
		this.quantity = quantity;
		this.Uom = uom;
		this.ProjectId = projectId;
		this.category = category;
		this.grade = grade;
		this.remarks = remarks;
		this.itemName = itemName;
		this.IndentId = indentId;
	}

	public EMSPurchaseBean() {}

	public EMSPurchaseBean(String projectName,String productDescription, String size, int quantity, String uom, String ratePerKg,String discount, String netAmount, double sGST, double cGST, int indentId, String currentDate, String pONumber,String vendorName,String paymentTerms) {
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
		this.paymentTerms = paymentTerms;
	}
	

	public EMSPurchaseBean(int iTEMCATAGORY, int gradeId, int sIZEId) {
		this.ITEMCATAGORY = iTEMCATAGORY;
		this.GradeId = gradeId;
		this.SIZEId = sIZEId;
	}
	
	public double getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(double taxableValue) {
		this.taxableValue = taxableValue;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public int getITEMCATAGORY() {
		return ITEMCATAGORY;
	}

	public void setITEMCATAGORY(int iTEMCATAGORY) {
		this.ITEMCATAGORY = iTEMCATAGORY;
	}

	public int getGradeId() {
		return GradeId;
	}

	public void setGradeId(int gradeId) {
		this.GradeId = gradeId;
	}

	public int getSIZEId() {
		return SIZEId;
	}
	
	public void setSIZEId(int sIZEId) {
		this.SIZEId = sIZEId;
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
