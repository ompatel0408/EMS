package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.time.LocalDate;
import com.google.gson.reflect.TypeToken;


import com.bean.QuotationBean;
import com.bean.ItemBean;
import com.dao.ClientDao;
import com.dao.EMSOffersDao;
import com.dao.QuotationDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ItemServices {

	
	private static long ItemNumber = 0;
	private static String ItemCode;
	public static ArrayList<ItemBean> fetchDataFromXHRRequest(BufferedReader reader,HttpServletRequest request) {
		
		StringBuilder sb = new StringBuilder();
		ArrayList<ItemBean> AQb = new ArrayList<ItemBean>();
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
			String ClientName = item.get("ClientId").toString();
			int clientId = ClientDao.getInstance().getClientIdFormDatabase(ClientName);
			if(QuotationDao.getQuotationIdFromDataBase(clientId) == null) {
				QuotationDao.addQuotation(new QuotationBean(clientId,LocalDate.now().toString()));
			}
			String it = ItemServices.generateItemCode(request);
			
			ItemBean Qb = new ItemBean(clientId,it,QuotationDao.getQuotationIdFromDataBase(clientId).getQuotationId(),ItemServices.generateDrawingId(clientId),item.get("ItemName").toString(), Integer.parseInt((String) item.get("quantity")), item.get("tagNo").toString(),item.get("remarks").toString(), item.get("TotalPrice").toString(),item.get("delivaryDate").toString());
		   
		    AQb.add(Qb);
		}
		
		for(ItemBean It :AQb) {
			System.out.println(It.getItemCode());
		}
		return AQb;
	}
	
	public static String generateItemCode(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(ItemCode == null) {
			ItemCode = (String) session.getAttribute("ItemCode");
		}
		if(ItemCode != null) {
			ItemNumber= Long.parseLong(ItemCode.substring(ItemCode.length() - 6));

			ItemNumber++;
			
			ItemCode ="EMSITC".concat(String.valueOf(ItemNumber));
		}else {
			
			ItemCode ="EMSITC".concat(String.valueOf(100001));
		}
		return ItemCode;
	}
	
	public static String generateDrawingId(int clientId) {
		
		String drawingId = "";
		drawingId = String.valueOf(clientId).concat("_").concat(String.valueOf(ItemCode));
		return drawingId;
	}
	
}
