package com.service;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.bean.EMSGRNBean;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class GRNServices {
	
	public static EMSGRNBean uploadPic(HttpServletRequest request) {

	 	Part filePart;
	 	Part filePart1;
		try {
			
			filePart = request.getPart("file");
			filePart1 = request.getPart("file1");
			String fileName = filePart.getSubmittedFileName();
			String fileName1 = filePart1.getSubmittedFileName();
			
			System.out.println("File" +fileName);

			System.out.println("File 1"+fileName1);
	        
	        File file = new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\GRNImages\\".concat(fileName));
	        File file1 = new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\GRNImages\\".concat(fileName1));
	        
	        FileOutputStream outputStream = new FileOutputStream(file);

	        FileOutputStream outputStream1 = new FileOutputStream(file1);
	        
	        IOUtils.copy(filePart.getInputStream(), outputStream);

	        IOUtils.copy(filePart1.getInputStream(), outputStream1);
	        EMSGRNBean EGB = new EMSGRNBean();
	        EGB.setPath1(fileName);
	        EGB.setPath2(fileName1);
	        return EGB;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
