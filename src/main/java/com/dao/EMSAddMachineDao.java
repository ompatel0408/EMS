package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import com.bean.EMSAddMachineBean;
import com.bean.EMSAddMachineInMntBean;
import com.bean.EMSPerMachineMntBean;
import com.dbConnection.MySqlConnection;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

public class EMSAddMachineDao 
{
	private static EMSAddMachineDao instance = null;
	
	public static EMSAddMachineDao getInstance()
	{
		if(instance == null)
		{
			instance = new EMSAddMachineDao();
		}
		return instance;
	}
	
	public boolean insertItemInDba(EMSAddMachineBean emb)
	{
		Connection conn = MySqlConnection.getInstance();
		
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into machines(machineName,modelNo,purchaseDate,invoice,machineCompny,mntDueDate,remark) values (?,?,?,?,?,?,?)");
			stmt.setString(1, emb.getMachineName());
			stmt.setString(2, emb.getModelNo());
			stmt.setString(3, emb.getpDate());
			stmt.setString(4, emb.getInvoice());
			stmt.setString(5, emb.getMachineCompany());
			stmt.setString(6, emb.getMntDueDate());
			stmt.setString(7, emb.getRemark());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<EMSAddMachineBean> getItems()
	{
		ArrayList<EMSAddMachineBean> eamb = new ArrayList<EMSAddMachineBean>();
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from machines");

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				EMSAddMachineBean eb = new EMSAddMachineBean(rs.getInt("machineId"), rs.getString("machineName"), rs.getString("modelNo"), rs.getString("invoice"), rs.getString("purchaseDate"), rs.getString("machineCompny"), rs.getString("mntDueDate"), rs.getString("remark"), rs.getInt("inMaintenence"));
				eamb.add(eb);
			}
			return eamb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashSet<String> getMachineNamefromDba()
	{
		HashSet<String> eamb = new HashSet<String>();
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select machineName from machines");

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				eamb.add(rs.getString(1));
			}
			return eamb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashSet<String> getMachineNameMntfromDba()
	{
		HashSet<String> eamb = new HashSet<String>();
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select machineName from machines where inMaintenence = '0'");

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				eamb.add(rs.getString(1));
			}
			return eamb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getMachineIdfromDba(String machine, String model)
	{
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("select machineId from machines where machineName = ? and modelNo = ?");
			stmt.setString(1, machine);
			stmt.setString(2, model);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String[] getMachineNamefromDba(int id)
	{
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("select machineName,modelNo from machines where machineid = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			String[] st = new String[2];
			while(rs.next())
			{
				st[0] = rs.getString(1);
				st[1] = rs.getString(2);
			}
			return st;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getModelNumberFromDba(String machine)
	{
		ArrayList<String> eamb = new ArrayList<String>();
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select modelNo from machines where machineName = ? ");
			stmt.setString(1, machine);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				eamb.add(rs.getString(1));
			}
			return eamb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getModelNumberMntFromDba(String machine)
	{
		ArrayList<String> eamb = new ArrayList<String>();
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select modelNo from machines where machineName = ? and inMaintenence = '0'");
			stmt.setString(1, machine);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				eamb.add(rs.getString(1));
			}
			return eamb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addItemInMnt(EMSAddMachineInMntBean ab)
	{
		Connection conn = MySqlConnection.getInstance();
		try {
			
			PreparedStatement stmt = conn.prepareStatement("insert into maintenance(MACHINEID,COMPANYNAME,GIVENDATE,DUEDATE,INVOICE,REMARK) values (?,?,?,?,?,?)");
			stmt.setInt(1, EMSAddMachineDao.getInstance().getMachineIdfromDba(ab.getMachine(), ab.getModel()));
			stmt.setString(2, ab.getCompnyName());
			stmt.setString(3, ab.getMntGvnDate());
			stmt.setString(4, ab.getDueDate());
			stmt.setString(5, ab.getInvoice());
			stmt.setString(6, ab.getRemark());
			stmt.executeUpdate();
			
			PreparedStatement stmt1 = conn.prepareStatement("update machines set inMaintenence = '0' where machineName = ? and modelNo = ?");
			stmt1.setString(1, ab.getMachine());
			stmt1.setString(2, ab.getModel());
			stmt1.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<EMSPerMachineMntBean> getMachineMntData(int id)
	{
		ArrayList<EMSPerMachineMntBean> arl = new ArrayList<EMSPerMachineMntBean>();
		Connection conn = MySqlConnection.getInstance();
		EMSAddMachineDao dao = EMSAddMachineDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from maintenance where machineId = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				EMSPerMachineMntBean bean = new EMSPerMachineMntBean(dao.getMachineNamefromDba(id)[0], dao.getMachineNamefromDba(id)[1], rs.getString("COMPANYNAME"), rs.getString("GIVENDATE"), rs.getString("DUEDATE"), rs.getString("INVOICE"), rs.getString("REMARK"), rs.getString("receivedDate"), rs.getLong("mntPrice"));
				arl.add(bean);
			}
			return arl;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateReceiveFromMnt(EMSAddMachineInMntBean bean)
	{
		Connection conn  = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("update maintenance set receivedDate = ?, mntPrice = ? where machineId = ? and receivedDate IS NULL and mntPrice = '0'");
			stmt.setString(1, bean.getRcvDate());
			stmt.setLong(2, bean.getPrice());
			stmt.setInt(3, getMachineIdfromDba(bean.getMachine(), bean.getModel()));
			stmt.executeUpdate();
			
			PreparedStatement stmt1 = conn.prepareStatement("update machines set inMaintenence = '1' where machineId = ?");
			stmt1.setInt(1, getMachineIdfromDba(bean.getMachine(), bean.getModel()));
			stmt1.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
