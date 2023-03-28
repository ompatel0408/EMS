package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bean.IndentBean;
import com.dao.IndentDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class IndentServices {
	public static ArrayList<IndentBean> fetchDataFromXHRRequest(BufferedReader reader, HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		ArrayList<IndentBean> AQb = new ArrayList<IndentBean>();
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
//		System.out.println(type);

		// Convert the JSON array to a List of Maps
		List<Map<String, Object>> data = gson.fromJson(jsonData, type);
		System.out.println(data);
		// Loop through each object in the list and extract the fields
//		for (Map<String, Object> item : data) {
//			IndentBean Qb = new IndentBean();
//			System.out.println(item);
//			Qb.setProjectId(item.get("ProjectId").toString());
//			Qb.setItemCategory(item.get("category-id").toString());
//			Qb.setItemName(item.get("item-id").toString());
//			Qb.setQuantity(Integer.parseInt(item.get("quantity-id").toString()));
//			Qb.setRemarks(item.get("remark-id").toString());
//			Qb.setGradeid(Integer.parseInt(item.get("grade").toString()));
//			Qb.setSizeid(Integer.parseInt(item.get("size").toString()));
//			Qb.setUom("botjod");
//			System.out.println(Qb);
//		   
//		    AQb.add(Qb);
//		}
		IndentDao id = IndentDao.getInstance();
		for (Map<String, Object> item : data) {
			IndentBean Qb = new IndentBean();
			String projectId = (String) item.get("ProjectId");
			Qb.setProjectId(projectId);
			String items = (String) item.get("item");
			Qb.setItemName(items);
			int category = Integer.parseInt(item.get("category").toString());
			Qb.setCategoryId(category);
			int grade = id.getGradeId((String) item.get("grade"));
			Qb.setGradeId(grade);
			int size = id.getSizeId((String) item.get("size"));
			Qb.setSizeId(size);
			int quantity = Integer.parseInt(item.get("quantity").toString());
			Qb.setQuantity(quantity);
			String remark = (String) item.get("remark");
			Qb.setRemark(remark);
			String UOM = "KG";
			Qb.setUOM(UOM);
			AQb.add(Qb);
			System.out.println(Qb);
		}
		return AQb;
	}
}