package com.service;

import com.dao.ProjectDao;

public class ProjectServices {
	
	public String projectGenerate(String clientName) {
		String projectId;
		int pId = 0;
		pId = new ProjectDao().getProjectId();
		pId++;
		System.out.println(pId);
		projectId = clientName.concat("PRO").concat(Integer.toString(pId)); 
		return projectId;
	}
}
