package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.QuotationPerItemBean;
import com.dao.EMSOffersDao;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class QuotationPerItemServices {
	private static String offerName = "";
	private static boolean isPresent = false;
	
	public static ArrayList<QuotationPerItemBean> fetchDataFromXHRRequestInQuotaionPerItem(BufferedReader reader,HttpServletRequest request) {
	
		QuotationPerItemDao QPI = new QuotationPerItemDao();
		StringBuilder sb = new StringBuilder();
		ArrayList<QuotationPerItemBean> AQPIB = new ArrayList<QuotationPerItemBean>();
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
			String category = item.get("catagory").toString();
			int categoryId = QPI.getCategoryIdFromDatabase(category);
			int gradeId = QPI.getgradeIdFromDatabase(item.get("grade").toString());
			int sizeId = QPI.getsizeIdFromDatabase(item.get("size").toString());
			
			if(!isPresent) {
				offerName = EMSOffersDao.getInstance().getOfferNameFromDatabase(item.get("OfferName").toString());
				isPresent = true;
			}
			
			String TotalpricePerItem = String.valueOf(Double.parseDouble(item.get("price").toString()) * Double.parseDouble(item.get("quantity").toString()));
			QuotationPerItemBean QPIB = new QuotationPerItemBean(offerName,item.get("OfferName").toString(),categoryId,gradeId,sizeId,Double.parseDouble(item.get("quantity").toString()), item.get("waight").toString(), item.get("unit").toString(), item.get("price").toString(),item.get("percentage").toString(),TotalpricePerItem);
			
			AQPIB.add(QPIB);
		}
		isPresent = false;
		
		return AQPIB;
	}
}
