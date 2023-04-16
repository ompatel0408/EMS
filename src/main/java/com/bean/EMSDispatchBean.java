package com.bean;

public class EMSDispatchBean 
{
	private String clientId,offerId,vehicleNo,travelComOwnr,travelCom,checkBy,dest,date;

	public EMSDispatchBean() {}
	
	public EMSDispatchBean(String clientId, String offerId, String vehicleNo, String travelComOwnr, String travelCom,
			String checkBy, String dest) {
		this.clientId = clientId;
		this.offerId = offerId;
		this.vehicleNo = vehicleNo;
		this.travelComOwnr = travelComOwnr;
		this.travelCom = travelCom;
		this.checkBy = checkBy;
		this.dest = dest;
	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setclientId(String clientId) {
		this.clientId = clientId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public void setTravelComOwnr(String travelComOwnr) {
		this.travelComOwnr = travelComOwnr;
	}

	public void setTravelCom(String travelCom) {
		this.travelCom = travelCom;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getclientId() {
		return clientId;
	}

	public String getOfferId() {
		return offerId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public String getTravelComOwnr() {
		return travelComOwnr;
	}

	public String getTravelCom() {
		return travelCom;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public String getDest() {
		return dest;
	}
}