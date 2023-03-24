package com.bean;


public class EMSStoreBean 
{
	private String projectId;
	private int category;
	private int grade;
	private int size;
	private int quantity;
	
	public EMSStoreBean() {}

	public EMSStoreBean(String projectId, int category, int grade, int size, int quantity) {
		
		this.projectId = projectId;
		this.category = category;
		this.grade = grade;
		this.size = size;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}