package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSStoreBean;
import com.dao.QuotationPerItemDao;
import com.dao.EMSStoreDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;

public class EMSStoreServices 
{
	private static QuotationPerItemDao qpid = QuotationPerItemDao.getInstance();
	public static ArrayList<EMSStoreBean> fetchDataFromXHRRequestInStore(BufferedReader reader,HttpServletRequest request) 
	{	
		EMSStoreDao sd = new EMSStoreDao();
		StringBuilder sb = new StringBuilder();
		ArrayList<EMSStoreBean> alsb = new ArrayList<EMSStoreBean>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
			    sb.append(line);
			}
		} catch (IOException e) {
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
			int categoryid = qpid.getCategoryIdFromDatabase(item.get("category").toString());
			EMSStoreBean stb = new EMSStoreBean(item.get("ProjectId").toString(), categoryid, sd.getGradeIdFromDatabase(categoryid,item.get("grade").toString()), sd.getSizeIdFromDatabase(categoryid, sd.getGradeIdFromDatabase(categoryid,item.get("grade").toString()), item.get("size").toString()), Integer.parseInt(item.get("quantity").toString()));
			alsb.add(stb);
		}
		return alsb;
	}
}