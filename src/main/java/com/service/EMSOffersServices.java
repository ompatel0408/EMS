package com.service;



import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bean.EMSOffersBean;
import com.bean.QuotationBean;
import com.dao.EMSOffersDao;
import com.dao.QuotationDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
public class EMSOffersServices{
		private static long offerNumber = 0;
		private static String offerCode;
		public static ArrayList<EMSOffersBean> fetchDataFromXHRRequest(BufferedReader reader, HttpServletRequest request) {

			StringBuilder sb = new StringBuilder();
			ArrayList<EMSOffersBean> AQb = new ArrayList<EMSOffersBean>();
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
//			System.out.println(type);

			// Convert the JSON array to a List of Maps
			List<Map<String, Object>> data = gson.fromJson(jsonData, type);
			// Loop through each object in the list and extract the fields

			EMSOffersDao id = EMSOffersDao.getInstance();
			for (Map<String, Object> item : data) {
				int clientId = Integer.parseInt(item.get("ClientId").toString());
				System.out.println("Client iD"+clientId);
				if(QuotationDao.getQuotationIdFromDataBase(clientId) == null) {
					QuotationDao.addQuotation(new QuotationBean(clientId,LocalDate.now().toString()));
				}
				EMSOffersBean Qb =  new EMSOffersBean(Integer.parseInt(item.get("ClientId").toString()), Integer.parseInt(item.get("quantity").toString()), QuotationDao.getQuotationIdFromDataBase(clientId).getQuotationId(), item.get("ItemName").toString(),item.get("remarks").toString(),EMSOffersServices.generateOfferCode(request), item.get("TotalPrice").toString(), EMSOffersServices.generateDrawingId(clientId));
				AQb.add(Qb);
				System.out.println(Qb);
			}
			return AQb;
		}
		
		public static String generateOfferCode(HttpServletRequest request) {
			
			HttpSession session = request.getSession();
			if(offerCode == null) {
				offerCode = (String) session.getAttribute("OfferCode");
			}
			if(offerCode != null) {
				offerNumber= Long.parseLong(offerCode.substring(offerCode.length() - 6));

				offerNumber++;
				
				offerCode ="EMSITC".concat(String.valueOf(offerNumber));
			}else {
				
				offerCode ="EMSITC".concat(String.valueOf(100001));
			}
			return offerCode;
		}
		
		public static String generateDrawingId(int ClientId) {
			
			String drawingId = "";
			drawingId = String.valueOf(ClientId).concat("_").concat(String.valueOf(offerCode));
			return drawingId;
		}
		
}