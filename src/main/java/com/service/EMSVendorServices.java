package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSVendorsBean;
import com.dao.EMSVendorsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class EMSVendorServices {
	public static ArrayList<EMSVendorsBean> fetchDataFromXHRRequest(BufferedReader reader, HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		ArrayList<EMSVendorsBean> AQb = new ArrayList<EMSVendorsBean>();
		String line;
		try { 
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String jsonData = sb.toString();
		System.out.println(jsonData);

		// Create a Gson object
		Gson gson = new Gson();

		// Define a TypeToken for the List of objects
		Type type = new TypeToken<List<Map<String, Object>>>() {
		}.getType();
//		System.out.println(type);

		// Convert the JSON array to a List of Maps
		List<Map<String, Object>> data = gson.fromJson(jsonData, type);
		System.out.println(data);
		//VendorDao id = VendorDao.getInstance();
		for (Map<String, Object> item : data) {
			EMSVendorsBean Qb = new EMSVendorsBean();
			String vendorName = (String) item.get("vendorName");
			Qb.setVendorName(vendorName);
			String email = (String) item.get("email");
			Qb.setEmail(email);
			String address = (String) item.get("address");
			Qb.setAddress(address);
			String mobile = (String) item.get("mobile");
			Qb.setMobile(mobile);
			AQb.add(Qb);
			System.out.println(Qb);
		}
		return AQb;
	}
}