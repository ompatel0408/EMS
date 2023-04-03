package com.bean;

public class UserBean {

		int userId,role;
		long phNum;
		String userName,email,password,departmentName;
		
		public UserBean(int role, long phNum, String userName, String email, String departmentName) {
			this.role = role;
			this.phNum = phNum;
			this.userName = userName;
			this.email = email;
			this.departmentName = departmentName;
		}
		
		public UserBean() {}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getRole() {
			return role;
		}
		public void setRole(int role) {
			this.role = role;
		}
		public long getPhNum() {
			return phNum;
		}
		public void setPhNum(long phNum) {
			this.phNum = phNum;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		
	
}
