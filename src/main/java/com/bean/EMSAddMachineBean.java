package com.bean;

public class EMSAddMachineBean 
{
	private int mid;
	private String machineName;
	private String modelNo;
	private String invoice;
	private String pDate;
	private String MachineCompany;
	private String mntDueDate;
	private String remark;
	private int inMnt;
	
	public EMSAddMachineBean(String machineName, String modelNo, String invoice, String pDate, String machineCompany,
			String mntDueDate, String remark) {
		this.machineName = machineName;
		this.modelNo = modelNo;
		this.invoice = invoice;
		this.pDate = pDate;
		MachineCompany = machineCompany;
		this.mntDueDate = mntDueDate;
		this.remark = remark;
	}

	public EMSAddMachineBean(int mid, String machineName, String modelNo, String invoice, String pDate, String machineCompany,
			String mntDueDate, String remark, int inMnt) {
		this.mid = mid;
		this.machineName = machineName;
		this.modelNo = modelNo;
		this.invoice = invoice;
		this.pDate = pDate;
		MachineCompany = machineCompany;
		this.mntDueDate = mntDueDate;
		this.remark = remark;
		this.inMnt = inMnt;
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getInMnt() {
		return inMnt;
	}

	public void setInMnt(int inMnt) {
		this.inMnt = inMnt;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String getMachineCompany() {
		return MachineCompany;
	}

	public void setMachineCompany(String machineCompany) {
		MachineCompany = machineCompany;
	}

	public String getMntDueDate() {
		return mntDueDate;
	}

	public void setMntDueDate(String mntDueDate) {
		this.mntDueDate = mntDueDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
