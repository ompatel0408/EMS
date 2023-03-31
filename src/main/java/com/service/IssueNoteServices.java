package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.bean.EMSIssueNoteBean;
import com.dao.EMSIssueNoteDao;
import com.dao.EMSStoreDao;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;

public class IssueNoteServices 
{
	private static QuotationPerItemDao qpid = QuotationPerItemDao.getInstance();
	private static EMSStoreDao sd = EMSStoreDao.getInstance();
	public static ArrayList<EMSIssueNoteBean> fetchDataFromXHRRequestInIssueBean(BufferedReader reader,HttpServletRequest request) 
	{	
		EMSIssueNoteDao ind = new EMSIssueNoteDao();
		StringBuilder sb = new StringBuilder();
		ArrayList<EMSIssueNoteBean> alinb = new ArrayList<EMSIssueNoteBean>();
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

		/*
		  	public EMSIssueNoteBean(String pid, String catagory, String grade, String size, int quantity, String remark,
			String uom, String issuePerson, String contractor)    
		*/
		
		// Loop through each object in the list and extract the fields
		for (Map<String, Object> item : data) {
			int categoryid = qpid.getCategoryIdFromDatabase(item.get("category").toString());
			EMSIssueNoteBean stb = new EMSIssueNoteBean(item.get("ProjectId").toString(), item.get("category").toString(), item.get("grade").toString(), item.get("size").toString(),  Integer.parseInt(item.get("quantity").toString()), item.get("remark").toString(), item.get("unit").toString(), item.get("issuePer").toString(), item.get("contracter").toString());
//			EMSIssueNoteBean stb = new EMSStoreBean(item.get("ProjectId").toString(), categoryid, sd.getGradeIdFromDatabase(categoryid,item.get("grade").toString()), sd.getSizeIdFromDatabase(categoryid, sd.getGradeIdFromDatabase(categoryid,item.get("grade").toString()), item.get("size").toString()), Integer.parseInt(item.get("quantity").toString()));
			alinb.add(stb);
		}
		return alinb;
	}
}
