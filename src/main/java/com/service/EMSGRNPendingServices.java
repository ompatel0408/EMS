package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSGRNPendingBean;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class EMSGRNPendingServices {
	
	public static ArrayList<EMSGRNPendingBean> fetchDataFromXHRRequest(BufferedReader reader,HttpServletRequest request) {
		
		StringBuilder sb = new StringBuilder();
		ArrayList<EMSGRNPendingBean> APB = new ArrayList<EMSGRNPendingBean>();
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
			EMSGRNPendingBean EGPB = new EMSGRNPendingBean(item.get("MaterialCategory").toString(), item.get("projectId").toString(),QuotationPerItemDao.getInstance().getCategoryIdFromDatabase(item.get("Category").toString()),QuotationPerItemDao.getInstance().getgradeIdFromDatabase(item.get("Grade").toString()),QuotationPerItemDao.getInstance().getsizeIdFromDatabase(item.get("size").toString()), item.get("units").toString(), Integer.parseInt(item.get("Quantity").toString()),item.get("Category").toString(),item.get("Grade").toString(),item.get("size").toString());	
			APB.add(EGPB);
		}
		return APB;
	}
}
