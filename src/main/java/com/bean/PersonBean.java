package com.bean;

public class PersonBean {
	
	private int id;
	private String name;
	private String number;
	private String email;
	private String desg;
	
	public PersonBean() {}
	
	public PersonBean(String name, String number, String email, String desg) {
		this.name = name;
		this.number = number;
		this.email = email;
		this.desg = desg;
	}
	
	public PersonBean(int id, String name, String number, String email, String desg) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.email = email;
		this.desg = desg;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesg() {
		return desg;
	}
	public void setDesg(String desg) {
		this.desg = desg;
	}
}
