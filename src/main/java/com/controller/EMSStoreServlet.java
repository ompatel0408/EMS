package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.EMSStoreBean;
import com.dao.EMSStoreDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSStoreServices;

public class EMSStoreServlet extends HttpServlet {
	
	private static EMSStoreServlet instance = null;
	private static EMSStoreDao std = EMSStoreDao.getInstance();
	
	public static EMSStoreServlet getInstance() {
		if (instance == null) {
			instance = new EMSStoreServlet();
		}
		return instance;
	}
	private static ArrayList<EMSStoreBean> alsb = new ArrayList<EMSStoreBean>();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<EMSStoreBean> alsb = EMSStoreServices.fetchDataFromXHRRequestInStore(request.getReader(),request);
		
		if(std.addItems(alsb))
		{
			System.out.println("Items added successfylly....");
		}
		else {
			System.out.println("Items Not added....");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String requestBody = sb.toString();

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
		EMSStoreDao sd = EMSStoreDao.getInstance();
		String json = gson.toJson(sd.getCategory());
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String requestBody = sb.toString();

		EMSStoreDao sd = EMSStoreDao.getInstance();

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);

		String jsonType = jsonObject.getAsJsonObject().get("token").toString().replace("\"", "");
		
		if (jsonType.equals("Projects")) {
			String json = gson.toJson(sd.getProjects());
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else if(jsonType.equals("grade"))
		{
			String ctgry = jsonObject.getAsJsonObject().get("category").toString().replace("\"", "");
			System.out.println("In grade : "+ ctgry);
			String json = gson.toJson(sd.getGradeFromDatabase(ctgry));
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);		
		}
		else if(jsonType.equals("size"))
		{
			String ctgry = jsonObject.getAsJsonObject().get("category").toString().replace("\"", "");
			String grd = jsonObject.getAsJsonObject().get("grade").toString().replace("\"", "");
			
			String json = gson.toJson(sd.getSizeFromDatabase(ctgry, grd));
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);		
		}
	}
}