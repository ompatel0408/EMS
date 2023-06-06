package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSConsumablePurchaseBean;
import com.dao.EMSVendorsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;

public class EMSConsumablePurchaseService {
	
	public static ArrayList<EMSConsumablePurchaseBean> fetchDataFromXHRRequest(BufferedReader reader,HttpServletRequest request) {
		
		StringBuilder sb = new StringBuilder();
		ArrayList<EMSConsumablePurchaseBean> APB = new ArrayList<EMSConsumablePurchaseBean>();
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
	

		// Create a Gson object
		Gson gson = new Gson();

		// Define a TypeToken for the List of objects
		Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();

		// Convert the JSON array to a List of Maps
		List<Map<String, Object>> data = gson.fromJson(jsonData, type);

		// Loop through each object in the list and extract the fields
		for (Map<String, Object> item : data) {
			
			EMSConsumablePurchaseBean ECPB = new EMSConsumablePurchaseBean(item.get("category").toString(),item.get("itemName").toString(), Integer.parseInt(item.get("quantity").toString()),EMSVendorsDao.getInstance().getVendorIdfromDba( item.get("vendor").toString()), Double.parseDouble(item.get("rate").toString()), Double.parseDouble(item.get("discountAmount").toString()), Float.parseFloat(item.get("GST").toString())/2, Float.parseFloat(item.get("GST").toString())/2, item.get("currentDate").toString(), item.get("remarks").toString(),Double.parseDouble(item.get("totalAmount").toString()));
			APB.add(ECPB);
		}
		return APB;
	}
}
