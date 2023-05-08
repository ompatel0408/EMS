package com.bean;


public class EMSGRNPendingBean {
	
	private String materialCategory;
	private String projectId;
	private int category;
	private int grade;
	private int size;
	private String categoryName;
	private String gradeName;
	private String sizeName;
	private String units;
	private int quantity;
	private int remamingQuantity;
	private int originalQuantity;
	
	
	public EMSGRNPendingBean() {}
	
	public EMSGRNPendingBean(String materialCategory, String projectId, int category, int grade,int size,String units, int quantity,int originalQuantity,String categoryName,String gradeName,String sizeName) {
		this.materialCategory = materialCategory;
		this.projectId = projectId;
		this.category = category;
		this.grade = grade;
		this.size = size;
		this.units = units;
		this.quantity = quantity;
		this.originalQuantity = originalQuantity;
		this.categoryName = categoryName;
		this.gradeName = gradeName;
		this.sizeName = sizeName;
	}
	
	public EMSGRNPendingBean(String materialCategory, String projectId, String  categoryName,String gradeName,String sizeName,String units, int quantity,int originalQuantity) {
		this.materialCategory = materialCategory;
		this.projectId = projectId;
		this.categoryName = categoryName;
		this.gradeName = gradeName;
		this.sizeName = sizeName;
		this.units = units;
		this.quantity = quantity;
		this.originalQuantity = originalQuantity;
	}
	
	public int getOriginalQuantity() {
		return originalQuantity;
	}

	public void setOriginalQuantity(int originalQuantity) {
		this.originalQuantity = originalQuantity;
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

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	
	public String getMaterialCategory() {
		return materialCategory;
	}
	
	public void setMaterialCategory(String materialCategory) {
		this.materialCategory = materialCategory;
	}
	
	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getUnits() {
		return units;
	}
	
	public void setUnits(String units) {
		this.units = units;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
