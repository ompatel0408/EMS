package com.controller;




import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.service.GRNServices;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;


@MultipartConfig
public class EMSGRNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EMSGRNServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			GRNServices.uploadPic(request);		 	
	}

}
