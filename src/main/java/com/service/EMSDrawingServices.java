package com.service;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//import org.apache.commons.compress.utils.IOUtils;
//
////import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
//
//import com.bean.EMSDrawingBean;
//import com.bean.EMSGRNBean;
//import com.dao.EMSDrawingDao;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.Part;
//
//public class EMSDrawingServices {
//	
//	public static EMSDrawingBean uploadPic(HttpServletRequest request,String offer,String project,String subItem) {
//
//	 	Part filePart;
//	 	Part filePart1;
//		try {
//			
//			filePart = request.getPart("file");
//			filePart1 = request.getPart("file1");
//			String fileName = filePart.getSubmittedFileName();
//			String fileName1 = filePart1.getSubmittedFileName();
//			
//			System.out.println("File" +fileName);
//
//			System.out.println("File 1"+fileName1);
//	        
//	        File file = new File("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS/src/main/webapp/DrawingImages/Clients/".concat(project).concat("").concat(offer).concat("").concat(subItem));
//	        File file1 = new File("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS/src/main/webapp/DrawingImages/Projects/".concat(project).concat("").concat(offer).concat("").concat(subItem));
//	        
//	        FileOutputStream outputStream = new FileOutputStream(file);
//
//	        FileOutputStream outputStream1 = new FileOutputStream(file1);
//	        
//	        IOUtils.copy(filePart.getInputStream(), outputStream);
//
//	        IOUtils.copy(filePart1.getInputStream(), outputStream1);
//	        EMSDrawingBean EGB = new EMSDrawingBean();
//	        EGB.setClientDrawing(fileName);
//	        EGB.setEMSDrawing(fileName1);
//	        
//	        EMSDrawingDao edd =new EMSDrawingDao();
//	        edd.getInstance().addToDrawingHistory(project,offer,subItem,"Client".concat(project).concat("").concat(offer).concat("").concat(subItem),"project".concat(project).concat("").concat(offer).concat("").concat(subItem));
//	        return EGB;
//	        
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	
//	public static String getActualDrawingId(String offerId,ArrayList<String> drawingIds) {
//		
//		for(String s:drawingIds) {
//		
//			if(offerId.equals(s.substring(s.indexOf('_')+1))) {
//				
//				return s;
//			}
//		}
//		return null;
//	}
//	
//	public static EMSGRNBean uploadFileWithFile(HttpServletRequest request) {
//		Part filePart;
//		try {
//			
//			filePart = request.getPart("file");
//			
//			String fileName = filePart.getSubmittedFileName();
//			
//	        File file = new File("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS/src/main/webapp/EMSPOFiles/".concat(fileName));
//	        
//	        FileOutputStream outputStream = new FileOutputStream(file);
//    
//	        IOUtils.copy(filePart.getInputStream(), outputStream);
//	        
//	        EMSGRNBean EGB = new EMSGRNBean();
//	        EGB.setPath1("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS/src/main/webapp/EMSPOFiles/".concat(fileName));
//	        
//	        return EGB;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}	
//}

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.commons.compress.utils.IOUtils;

//import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;
import com.dao.EMSDrawingDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class EMSDrawingServices {
	
	public static EMSDrawingBean uploadPic(HttpServletRequest request,String offer,String project,String subItem) {

	 	Part filePart;
	 	Part filePart1;
		try {
			
			filePart = request.getPart("file");
			filePart1 = request.getPart("file1");
			String fileName = filePart.getSubmittedFileName();
			String fileName1 = filePart1.getSubmittedFileName();
			
			System.out.println("File" +fileName);

			System.out.println("File 1"+fileName1);
			EMSDrawingDao edd =new EMSDrawingDao();
			int count=edd.getInstance().getCount(project,offer,subItem);
			File file=null;
			File file1=null;
			File file2=null;
			File file3=null;		
				file = new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\DrawingImages\\Clients\\".concat(project).concat("").concat(offer).concat("").concat(subItem));
				file1 = new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\DrawingImages\\Projects\\".concat(project).concat("").concat(offer).concat("").concat(subItem));
				file2= new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\DrawingImages\\Clients\\".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)));
				file3= new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\DrawingImages\\Projects\\".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)));
				if(count==0)
				{					
					edd.getInstance().addToDrawingHistory(project,offer,subItem,"Clients/".concat(project).concat("").concat(offer).concat("").concat(subItem),"Projects/".concat(project).concat("").concat(offer).concat("").concat(subItem));
					edd.getInstance().addToDrawingHistory(project,offer,subItem,"Clients/".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)),"Projects/".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)));
				}
				else {
					edd.getInstance().addToDrawingHistory(project,offer,subItem,"Clients/".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)),"Projects/".concat(project).concat("").concat(offer).concat("").concat(subItem).concat(Integer.toString(count)));
				}
	        
	        FileOutputStream outputStream = new FileOutputStream(file);
	        FileOutputStream outputStream1 = new FileOutputStream(file1);
	        FileOutputStream outputStream2 = new FileOutputStream(file2);
	        FileOutputStream outputStream3 = new FileOutputStream(file3);
	        IOUtils.copy(filePart.getInputStream(), outputStream2);
	        IOUtils.copy(filePart1.getInputStream(), outputStream3);
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
		
			if(offerId.equals(s.substring(s.indexOf('_')+1))) {
				
				return s;
			}
		}
		return null;
	}
	
	public static EMSGRNBean uploadFileWithFile(HttpServletRequest request) {
		Part filePart;
		try {
			
			filePart = request.getPart("file");
			
			String fileName = filePart.getSubmittedFileName();
			
	        File file = new File("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\EMSPOFiles\\".concat(fileName));
	        
	        FileOutputStream outputStream = new FileOutputStream(file);
    
	        IOUtils.copy(filePart.getInputStream(), outputStream);
	        
	        EMSGRNBean EGB = new EMSGRNBean();
	        EGB.setPath1("C:\\Users\\Admin\\eclipse-workspace\\EMS\\src\\main\\webapp\\EMSPOFiles\\".concat(fileName));
	        
	        return EGB;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}