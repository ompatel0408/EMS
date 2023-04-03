package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class EMSDrawingServices {
	
	public static EMSDrawingBean uploadPic(HttpServletRequest request) {

	 	Part filePart;
	 	Part filePart1;
		try {
			
			filePart = request.getPart("file");
			filePart1 = request.getPart("file1");
			String fileName = filePart.getSubmittedFileName();
			String fileName1 = filePart1.getSubmittedFileName();
			
			System.out.println("File" +fileName);

			System.out.println("File 1"+fileName1);
	        
	        File file = new File("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS2/src/main/webapp/DrawingImages/".concat(fileName));
	        File file1 = new File("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS2/src/main/webapp/DrawingImages/".concat(fileName1));
	        
	        FileOutputStream outputStream = new FileOutputStream(file);

	        FileOutputStream outputStream1 = new FileOutputStream(file1);
	        
	        IOUtils.copy(filePart.getInputStream(), outputStream);

	        IOUtils.copy(filePart1.getInputStream(), outputStream1);
	        EMSDrawingBean EGB = new EMSDrawingBean();
	        EGB.setClientDrawing(fileName);
	        EGB.setEMSDrawing(fileName1);
	        return EGB;
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static String getActualDrawingId(String offerId,ArrayList<String> drawingIds) {
		
		for(String s:drawingIds) {
			System.out.println("-->"+s.substring(s.indexOf('_')+1));
			if(offerId.equals(s.substring(s.indexOf('_')+1))) {
				
				return s;
			}
		}
		return null;
	}
	
	
}
