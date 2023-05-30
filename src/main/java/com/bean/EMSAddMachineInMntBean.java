package com.bean;

public class EMSAddMachineInMntBean
{
	private String machine,model,compnyName,dueDate,invoice,mntGvnDate,remark,rcvDate;
	private long price;

	public EMSAddMachineInMntBean(String machine, String model, String rcvDate,long price)
	{
		this.machine = machine;
		this.model = model;
		this.price = price;
		this.rcvDate = rcvDate;
	}
	
	
	public String getRcvDate() {
		return rcvDate;
	}

	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public EMSAddMachineInMntBean(String machine, String model, String compnyName, String dueDate, String invoice,
			String mntGvnDate, String remark) {
		this.machine = machine;
		this.model = model;
		this.compnyName = compnyName;
		this.dueDate = dueDate;
		this.invoice = invoice;
		this.mntGvnDate = mntGvnDate;
		this.remark = remark;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCompnyName() {
		return compnyName;
	}

	public void setCompnyName(String compnyName) {
		this.compnyName = compnyName;
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

	public String getMntGvnDate() {
		return mntGvnDate;
	}

	public void setMntGvnDate(String mntGvnDate) {
		this.mntGvnDate = mntGvnDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
