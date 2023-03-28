package com.service;

import com.dao.EMSLoginDao;

public class Authentication {

	public static boolean isValidCredentials(String email,String password) {
		
		EMSLoginDao ELD = EMSLoginDao.getInstance();
		
		if(ELD.getPasswordFromDb(email).getPassword().equals(password)) {
			return true;
		}
		
		return false;
		
	}
}
