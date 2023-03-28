package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bean.EMSPurchaseBean;
import com.bean.ItemBean;
import com.bean.QuotationBean;
import com.dao.EMSPurchaseDao;
import com.dao.QuotationDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class PurchaseServices {
	
	private static int count = 0;
	
	public static ArrayList<EMSPurchaseBean> fetchDataFromXHRRequest(BufferedReader reader,HttpServletRequest request) {
		System.out.println("Reached at Fetch");
		StringBuilder sb = new StringBuilder();
		ArrayList<EMSPurchaseBean> APB = new ArrayList<EMSPurchaseBean>();
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
			EMSPurchaseBean EPB = new EMSPurchaseBean(item.get("category").toString().concat(" ").concat(item.get("grade").toString()), item.get("size").toString(), Integer.parseInt(item.get("orderQuan").toString()), item.get("unit").toString(), item.get("rate").toString(), item.get("discountAmount").toString(), item.get("TotalAmount").toString(), Double.parseDouble(item.get("GST").toString())/2, Double.parseDouble(item.get("GST").toString())/2, EMSPurchaseDao.getInstance().getIndentId(item.get("ProjectId").toString()), LocalDate.now().toString(), PurchaseServices.generatePOId(), item.get("vendorName").toString());
			APB.add(EPB);
		}
		count = 0;
		return APB;
	}
	
	public static String generatePOId() {
		count++;
		return "EMSPL/".concat(String.valueOf(count)).concat("/").concat(LocalDate.now().toString());
	}
	
}
