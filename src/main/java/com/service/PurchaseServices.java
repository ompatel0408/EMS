package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bean.EMSPurchaseBean;
import com.bean.ItemBean;
import com.bean.QuotationBean;
import com.dao.EMSProductionDao;
import com.dao.EMSPurchaseDao;
import com.dao.ProjectDao;
import com.dao.QuotationDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class PurchaseServices {
	
	private static int count = 0;
	private static boolean isPresent = false;
	
	public static ArrayList<EMSPurchaseBean> fetchDataFromXHRRequest(BufferedReader reader,HttpServletRequest request) {
	
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
			if((isPresent == false) && (item.get("loss").toString().equals("true"))) {
				ProjectDao.getInstance().setLoss(item.get("ProjectId").toString());
				isPresent = true;
			}
			EMSPurchaseBean EPB = new EMSPurchaseBean(item.get("ProjectId").toString(),item.get("category").toString().concat(" ").concat(item.get("grade").toString()), item.get("size").toString(), Integer.parseInt(item.get("orderQuan").toString()), item.get("unit").toString(), item.get("rate").toString(), item.get("discountAmount").toString(), item.get("TotalAmount").toString(), Double.parseDouble(item.get("GST").toString())/2, Double.parseDouble(item.get("GST").toString())/2, Integer.parseInt(item.get("indentId").toString()), LocalDate.now().toString(), PurchaseServices.generatePOId(), item.get("vendorName").toString(),String.valueOf(LocalDate.now().plus(Period.ofDays(Integer.parseInt(item.get("PaymentTerms").toString())))));
			APB.add(EPB);
		}
		count = 0;
		isPresent = false;
		return APB;
	}
	
	public static String generatePOId() {
		count++;
		return "EMSPL/".concat(String.valueOf(count)).concat("/").concat(LocalDate.now().toString());
	}
	
}
