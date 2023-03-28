package com.bean;

public class EMSProductionBean 
{
	
		private String projectId;
	    private String remark;
	    private int workDonePer;
	    private String qualityCheck;
	      
	   public EMSProductionBean() {}
	    
		public EMSProductionBean(String projectId, String remark, int workDonePer ,String qualityCheck) {
			this.projectId = projectId;
			this.remark = remark;
			this.workDonePer = workDonePer;
			this.qualityCheck= qualityCheck;
		}
		
		public String getprojectId() {
			return projectId;
		}
		
		public void setprojectId(String projectId) {
			this.projectId = projectId;
		}
		
		public String getRemark() {
			return remark;
		}
		
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		
		public int getworkDonePer() {
			return workDonePer;
		}
		
		public void setworkDonePer(int workDonePer) {
			this.workDonePer = workDonePer;
		}  
		public String getqualityCheck() {
			return qualityCheck;
		}
		
		public void setqualityCheck(String qualityCheck) {
			this.qualityCheck = qualityCheck;
		} 
	}

