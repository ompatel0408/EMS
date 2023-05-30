package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.PersonBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PersonsDao {
	private static PersonsDao instance = null;

	public static PersonsDao getInstance() {
		if (instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	public int addPersonInDba(PersonBean person,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement stmt = con
					.prepareStatement("insert into persons (name,number,email,designation) values (?,?,?,?)");
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getNumber());
			stmt.setString(3, person.getEmail());
			stmt.setString(4, person.getDesg());
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			
			try {
				ExceptionHandler.handleException(request, response, e);
			}catch(Exception e1) {		
				e1.printStackTrace();
			}
			
		}
		return -1;
	}

	public ArrayList<PersonBean> getAllPersonsFromDba()
	{
		Connection conn = MySqlConnection.getInstance();
		ArrayList<PersonBean> ar = new ArrayList<PersonBean>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from persons");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				PersonBean person = new PersonBean(rs.getInt("id"),  rs.getString("name"), rs.getString("number"), rs.getString("email"), rs.getString("designation"));
				ar.add(person);
			}
			return ar;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public boolean deletePersonById(int id)
	{
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("delete from persons where id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	public ArrayList<String> getAllPersons()
	{
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select name from persons");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				ar.add(rs.getString(1));
			}
			return ar;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	} 
	
	public int getPersonIsFromDatabase(String name) {
		
		Connection conn = MySqlConnection.getInstance();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("Select id from persons where name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			  return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}
	
	public String getPersonsFromDatabase(int id) {
		
		Connection conn = MySqlConnection.getInstance();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("Select name from persons where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
			  return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
}