package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bean.DailyProgressReportBean;
import com.dao.IndentDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class DailyProgressReportServices {
	public static ArrayList<DailyProgressReportBean> fetchDataFromXHRRequest(BufferedReader reader, HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		ArrayList<DailyProgressReportBean> AQb = new ArrayList<DailyProgressReportBean>();
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

		// Convert the JSON array to a List of Maps
		List<Map<String, Object>> data = gson.fromJson(jsonData, type);
		System.out.println(data);
		// Loop through each object in the list and extract the fields

		IndentDao id = IndentDao.getInstance();
		for (Map<String, Object> item : data) {
			DailyProgressReportBean Qb = new DailyProgressReportBean();
			Qb.setProjectId((String) item.get("project"));
			Qb.setItemCode((String) item.get("item"));
			Qb.setCategoryId((String) item.get("catagory"));
			Qb.setGradeId((String) item.get("grade"));
			Qb.setSizeId((String) item.get("size"));
			System.out.println(item.get("quantity"));
			System.out.println(item.get("quantity").getClass().getSimpleName());
			int quantity = Integer.parseInt( item.get("quantity").toString());
			Qb.setQuantity(quantity);
			Qb.setUnitId((String) item.get("unit"));
			Qb.setSubItemId((String) item.get("subItem"));
			Qb.setDateId((String) item.get("date"));
			AQb.add(Qb);
			System.out.println(Qb);
		}
		System.out.println(AQb);
		return AQb;
	}
}
