package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // specify the path of the file to be downloaded
		System.out.println(request.getParameter("fileName"));
	    String filePath = "C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\DrawingImages\\"+request.getParameter("fileName");
	    System.out.println(filePath);
	    // create a File object to represent the file
	    File file = new File(filePath);
	    if (!file.exists()) {
	        // File does not exist, redirect to the same page
	        response.sendRedirect("EMSDrawingList.jsp");
	        return;
	    }
	    // set the content type of the response to be the MIME type of the file
	    response.setContentType(getServletContext().getMimeType(filePath));
	    
	    // set the content length of the response to be the length of the file
	    response.setContentLength((int) file.length());
	    
	    // set the headers for the response to force download the file
	    response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
	    response.setHeader("Cache-Control", "no-cache");
	    
	    // create a FileInputStream to read the file
	    FileInputStream fileInputStream = new FileInputStream(file);
	    
	    // create a byte array to store the bytes read from the file
	    byte[] bytes = new byte[4096];
	    int bytesRead;
	    
	    // create an OutputStream to write the bytes to the response
	    OutputStream outputStream = response.getOutputStream();
	    
	    // read the bytes from the file and write them to the response
	    while ((bytesRead = fileInputStream.read(bytes)) != -1) {
	        outputStream.write(bytes, 0, bytesRead);
	    }
	    
	    // close the FileInputStream and OutputStream
	    fileInputStream.close();
	    outputStream.close();
	}


}

