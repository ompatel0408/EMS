package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private static boolean isPresent = false;
	private static List<String> itemCode;
	private static ArrayList<String> orderCode = new ArrayList<String>();
	private static int clientId;
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
			
			clientId = ClientDao.getInstance().getClientIdFormDatabase(ClientName);
			
			itemCode = EMSOffersDao.getInstance().getOfferCodeFromdatabase(ClientName).stream()
            .filter(n -> n.equals(item.get("offerCode").toString()))
            .collect(Collectors.toList());
			
			if(!itemCode.isEmpty()) {
				orderCode.add(itemCode.get(0));
				AQb.add(new ItemBean(ClientName,clientId,itemCode.get(0),QuotationDao.getQuotationIdFromDataBase(clientId).getQuotationId(),ItemServices.generateDrawingId(clientId,itemCode.get(0)),item.get("ItemName").toString(), Integer.parseInt((String) item.get("quantity")), item.get("tagNo").toString(),item.get("remarks").toString(), item.get("TotalPrice").toString(),item.get("delivaryDate").toString()));
			}
		}
		EMSOffersDao.getInstance().updateStatus(new ArrayList<>(orderCode));
		EMSOffersDao.getInstance().updateStatus(clientId);
		return AQb;
	}
	public static String generateDrawingId(int clientId,String offerCode) {
		String drawingId = "";
		drawingId = String.valueOf(clientId).concat("_").concat(offerCode);
		return drawingId;
	}
	
}
