package com.service;

import com.dao.EMSLoginDao;

public class Authentication {

	public static boolean isValidCredentials(String email,String password) {
		
		if(EMSLoginDao.getPasswordFromDb(email).getPassword().equals(password)) {
			return true;
		}
		return false;
		
	}
}
