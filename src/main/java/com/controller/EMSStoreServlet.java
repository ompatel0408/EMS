package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.EMSLogsBean;
import com.bean.EMSStoreBean;
import com.dao.EMSLogsDao;
import com.dao.EMSStoreDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSStoreServices;
import com.service.ExceptionHandler;

@SuppressWarnings("serial")
public class EMSStoreServlet extends HttpServlet {
	
	private static EMSStoreServlet instance = null;
	private static EMSStoreDao std = EMSStoreDao.getInstance();
	private static String userId = "";
	
	public static EMSStoreServlet getInstance() {
		if (instance == null) {
			instance = new EMSStoreServlet();
		}
		return instance;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			ArrayList<EMSStoreBean> alsb = EMSStoreServices.fetchDataFromXHRRequestInStore(request.getReader(),request);
			HttpSession session = request.getSession();
	
			if(std.addItems(alsb))
			{
				System.out.println("Items added successfylly....");
				
				for(EMSStoreBean ESB:alsb) {
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new size ".concat(ESB.getSize()).concat(" of grade ").concat(ESB.getGrade()).concat(" and category ").concat(ESB.getCategory()).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","GENERALSTORE"))) {
						System.out.println("GENERALSTORE insert Logs Inserted!");
					}else {
						System.out.println("GENERALSTORE insert Logs not inserted!");
					}
				}
			}
			else {
				System.out.println("Items Not added....");
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Gson gson = new Gson();
			EMSStoreDao sd = EMSStoreDao.getInstance();
			String json = gson.toJson(sd.getCategory());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

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
			
			
			 if(jsonType.equals("grade"))
			{
				String ctgry = jsonObject.getAsJsonObject().get("category").toString().replace("\"", "");
				
				String json = gson.toJson(sd.getGradeFromDatabase(ctgry));
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);		
			}
			else if(jsonType.equals("size"))
			{
				String ctgry = jsonObject.getAsJsonObject().get("category").toString().replace("\"", "");
				String grd = jsonObject.getAsJsonObject().get("grade").toString().replace("\"", "");
				
				String json = gson.toJson(sd.getSizeFromDatabase(ctgry, grd));
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);		
			}
			else if(jsonType.equals("quantity"))
			{
				String ctgry = jsonObject.getAsJsonObject().get("category").toString().replace("\"", "");
				String grd = jsonObject.getAsJsonObject().get("grade").toString().replace("\"", "");
				String size = jsonObject.getAsJsonObject().get("size").toString().replace("\"", "");
				
				String json = gson.toJson(sd.getQuantFromDatabase(ctgry, grd,size));
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);	
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}