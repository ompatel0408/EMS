package com.service;

import com.util.Trial;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServerListener implements ServletContextListener {


    public ServerListener() {
        // TODO Auto-generated constructor stub
    }
    
	
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener Started");
		Trial.DailyMailService();
	}
	
	
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener Destroyed");
		Trial.stopDailyMailService();
	}
	
}
