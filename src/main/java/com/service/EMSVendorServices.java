package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSVendorsBean;
import com.dao.EMSVendorsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;

public class EMSVendorServices {
	public static ArrayList<EMSVendorsBean> fetchDataFromXHRRequest(BufferedReader reader, HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		ArrayList<EMSVendorsBean> AQb = new ArrayList<EMSVendorsBean>();
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
		Type type = new TypeToken<List<Map<String, Object>>>() {
		}.getType();
		// Convert the JSON array to a List of Maps
		List<Map<String, Object>> data = gson.fromJson(jsonData, type);
		for (Map<String, Object> item : data) {
			EMSVendorsBean Qb = new EMSVendorsBean(item.get("vendorName").toString(), item.get("address").toString(), item.get("email").toString(), item.get("mobile").toString(), item.get("email1").toString(), item.get("mobile1").toString(), item.get("gst").toString(), item.get("panNumber").toString(), item.get("bankName").toString(), item.get("ACNumber").toString(), item.get("IFSC").toString(),item.get("Remarks").toString());
			AQb.add(Qb);
		}
		return AQb;
	}
}