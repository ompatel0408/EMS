package com.bean;

public class EMSIssueNoteBean {
	
	 private String pid;
     private String catagory;
	 private String grade;
	 private String size;
	 private int quantity;
	 private String remark;
	 private String uom;
	 private String issuePerson;
	 private String contractor;
	 private int issueId;
	 private String issueDate;
	 
		public EMSIssueNoteBean() {}
	  
       	public EMSIssueNoteBean(String pid, String catagory, String grade, String size, int quantity, String remark,
			String uom, String issuePerson, String contractor) 
       	{
		this.pid = pid;
		this.catagory = catagory;
		this.grade = grade;
		this.size = size;
		this.quantity = quantity;
		this.remark = remark;
		this.uom = uom;
		this.issuePerson = issuePerson;
		this.contractor = contractor;
	     }

       	public String getIssueDate() {
    		return issueDate;
    	}

    	public void setIssueDate(String issueDate) {
    		this.issueDate = issueDate;
    	}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
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

		

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getCatagory() {
			return catagory;
		}

		public void setCatagory(String catagory) {
			this.catagory = catagory;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getUom() {
			return uom;
		}

		public void setUom(String uom) {
			this.uom = uom;
		}

		public String getIssuePerson() {
			return issuePerson;
		}

		public void setIssuePerson(String issuePerson) {
			this.issuePerson = issuePerson;
		}

		public String getContractor() {
			return contractor;
		}

		public void setContractor(String contractor) {
			this.contractor = contractor;
		}

		public int getIssueId() {
			return issueId;
		}

		public void setIssueId(int issueId) {
			this.issueId = issueId;
		}		
}
