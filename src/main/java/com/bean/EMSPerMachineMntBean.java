package com.bean;

public class EMSPerMachineMntBean 
{
	String mcName,modelName,comName,giveDate,dueDate,invoice,remark,reciveDate;
	long price;

	public EMSPerMachineMntBean(String mcName, String modelName, String comName, String giveDate, String dueDate,
			String invoice, String remark, String reciveDate, long price) {
		this.mcName = mcName;
		this.modelName = modelName;
		this.comName = comName;
		this.giveDate = giveDate;
		this.dueDate = dueDate;
		this.invoice = invoice;
		this.remark = remark;
		this.reciveDate = reciveDate;
		this.price = price;
	}

	public String getMcName() {
		return mcName;
	}

	public void setMcName(String mcName) {
		this.mcName = mcName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getGiveDate() {
		return giveDate;
	}

	public void setGiveDate(String giveDate) {
		this.giveDate = giveDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReciveDate() {
		return reciveDate;
	}

	public void setReciveDate(String reciveDate) {
		this.reciveDate = reciveDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
