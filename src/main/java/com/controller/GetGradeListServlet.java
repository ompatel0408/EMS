package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bean.CatagoryGradeSizeBean;
import com.dao.GetGradeListDao;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;



public class GetGradeListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    	
    	StringBuilder sb = new StringBuilder();
    	String line;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
			    sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonData =sb.toString();
		Gson gson = new Gson();
		CatagoryGradeSizeBean grade =gson.fromJson(jsonData, CatagoryGradeSizeBean.class);
		String query =grade.getQuery();
//        String query = request.getParameter("query");
        System.out.println(query);
        GetGradeListDao gradeList = GetGradeListDao.getInstance1();
        ArrayList<CatagoryGradeSizeBean> grades = new ArrayList<CatagoryGradeSizeBean>();
        grades=gradeList.getGradeList(query);
        ArrayList<String> gradeName = new ArrayList<String>();
    	ArrayList<Integer> gradeId = new ArrayList<Integer>();
        for (CatagoryGradeSizeBean i : grades)
    	{
         	gradeName.add(i.getCatagoryName());
    		gradeId.add(i.getCatagoryId());
    	}
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = gson.toJson(gradeName);
//        out.print("{\"data\": \"" + json + "\"}");
//        String json1 = gson.toJson(data);
//        System.out.println(json+ ""+json1);
//        System.out.println(data);
//        out.print(data);
        out.print(json);
//        out.print("{\"data\": \"" + json+"\"}");
        out.flush();
    }

    

	 
}