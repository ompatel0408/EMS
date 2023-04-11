package com.bean;


public class EMSStoreBean 
{
	private String category;
	private String grade;
	private String size;
	private int quantity;
	private int categoryId;
	private int gradeId;
	private int sizeId;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public EMSStoreBean() {}

	public EMSStoreBean(String category, String grade, String size, int quantity) {
		this.category = category;
		this.grade = grade;
		this.size = size;
		this.quantity = quantity;
	}
	public EMSStoreBean( int categoryId, int gradeId, int sizeId, int quantity,String category ,String grade ,String size) {
		
		this.categoryId = categoryId;
		this.gradeId = gradeId;
		this.sizeId = sizeId;
		this.quantity = quantity;
		this.category = category;
		this.grade = grade;
		this.size = size;
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
}