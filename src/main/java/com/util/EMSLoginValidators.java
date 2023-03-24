package com.util;


public class EMSLoginValidators {
	
	public static boolean isAlpha(String str) {

		return str.matches("^[a-zA-Z]+");
	}

	public static boolean isValidEmail(String str) {

		return str.matches("^([a-zA-Z0-9.#%&])+(@+gmail).([com]){3}$");
	}

	public static boolean isValidPassword(String str) {

		return str.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$");
	}
	
	
	public static boolean isValidAddress(String str) {

		return str.matches("[A-Za-z0-9\\-_,]{1,100}");
	}
	
	public static boolean isValidLandmark(String str) {

		return str.matches("[A-Za-z0-9\\-_,]{1,40}");
	}

	public static boolean isValidzipCode(String str) {

		return str.matches("[0-9]{6,6}");
	}

	public static boolean isValidPhoneNumber(String str) {

		return str.matches("[0-9]{10,10}");
	}
}
