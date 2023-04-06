package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.bean.EMSIssueNoteBean;
import com.bean.EMSLogsBean;
import com.bean.EMSProductionBean;
import com.dao.EMSIssueNoteDao;
import com.dao.EMSLogsDao;
import com.dao.EMSProductionDao;
import com.dao.EMSStoreDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.IssueNoteServices;
import com.service.ItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EMSIssueNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSIssueNoteServlet instance = null;
	private static EMSIssueNoteDao eind = EMSIssueNoteDao.getInstance();

	public static EMSIssueNoteServlet getInstance() {

		if (instance == null) {
			instance = new EMSIssueNoteServlet();
		}
		return instance;
	}

	public EMSIssueNoteServlet() {
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String requestBody = sb.toString();

		EMSIssueNoteDao sd = EMSIssueNoteDao.getInstance();

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);

		String jsonType = jsonObject.getAsJsonObject().get("token").toString().replace("\"", "");
		
		if(jsonType.equals("grade"))
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
		}else if(jsonType.equals("catagory"))
		{
			System.out.println("innnnn...");
			String json = gson.toJson(sd.getCatagorytFromDatabase());
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);		
		}
		else if(jsonType.equals("Projects"))
		{
//			System.out.println(jsonObject.get("Projects").getAsString());
	    	String json = gson.toJson(sd.getProjectId());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
		else if(jsonType.equals("quantity"))
		{
			System.out.println("in,,,,,,,");
			String ctgry = jsonObject.getAsJsonObject().get("catagory").toString().replace("\"", "");
			String grd = jsonObject.getAsJsonObject().get("grade").toString().replace("\"", "");
			String size = jsonObject.getAsJsonObject().get("size").toString().replace("\"", "");
			
			String json = gson.toJson(sd.getQuantityFromDatabase(ctgry, grd,size));
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<EMSIssueNoteBean> areind = IssueNoteServices.fetchDataFromXHRRequestInIssueBean(request.getReader(),request);
		
		HttpSession session = request.getSession();
		if(eind.addItems(areind))
		{
			System.out.println("Items added successfylly....");
			for(EMSIssueNoteBean EINB:areind) {
				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A person Named ".concat(EINB.getIssuePerson()).concat(" collected ").concat(EINB.getCatagory()).concat(" ").concat(EINB.getGrade()).concat(" ").concat(EINB.getSize()).concat(" for ").concat(EINB.getPid()),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","GRN"))) {
					System.out.println("purchase insert Logs Inserted!");
				}else {
					System.out.println("purchase insert Logs not inserted!");
				}
			}
			
		}
		else {
			System.out.println("Items Not added....");
		}
		
		
		
		
	}

}

