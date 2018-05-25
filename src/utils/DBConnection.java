package utils;
import helper.DriverSession;

import java.io.IOException;
import java.sql.*;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
public class DBConnection extends DriverSession{
	static Statement stmt;
	static Connection con = null;
	static Session session = null;
	static int id=0,irs=0;
	static Xls_Reader xls;

	public static void CreateDBConnection()throws ClassNotFoundException, SQLException, IOException, JSchException{
		//CreateSSHTunnel();
//		String dbUrl = "jdbc:mysql://2umsdb.fliplearn.com:3306/";
//		String username = "mukeshg";
//		String password = "mukeshg@321";
		String dbUrl = "jdbc:mysql://stgumsdb.fliplearn.com:3306/";
		String username = "stgcomm_service";
		String password = "stgcomm_service$$514";
//		String dbUrl = "jdbc:mysql://int4soadb.fliplearn.com:3306/";
//		String username = "soa_app";
//		String password = "flip@159$$";
		String DbName = "ums_api";
		Class.forName("com.mysql.jdbc.Driver"); 
		con=DriverManager.getConnection(dbUrl+DbName,username,password);
		stmt = con.createStatement();
	}    

	
	public static void connectDatabase(String dbname)throws Exception{
		xls=new Xls_Reader();
		String Sheetname="Credentials";
		String dbUrl,DbName;
		String username = xls.getCellData(Sheetname, "DBusername", 2);
		String password = xls.getCellData(Sheetname, "DBPassword", 2);
		switch (dbname.toLowerCase()) {
		case "ums":
			DbName="ums_api";
			dbUrl=xls.getCellData(Sheetname, "umshost", 2);
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println(dbUrl+DbName+username+password);
			con=DriverManager.getConnection(dbUrl+DbName,username,password);
			stmt = con.createStatement();
			break;
		case "group":
			dbUrl=xls.getCellData(Sheetname, "grouphost", 2);
			DbName="group_api";
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(dbUrl+DbName,username,password);
			stmt = con.createStatement();
			break;
		case "school":
			dbUrl=xls.getCellData(Sheetname, "schoolhost", 2);
			DbName="school_api";
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(dbUrl+DbName,username,password);
			stmt = con.createStatement();
			break;
		case "bl":
			dbUrl=xls.getCellData(Sheetname, "blhost", 2);
			DbName="flip_bl";
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection(dbUrl+DbName,username,password);
			stmt = con.createStatement();
			break;
		default:
			break;
		}
		
		//String dbUrl = "jdbc:mysql://2umsdb.fliplearn.com:3306/";
		//String username = "mukeshg";
		//String password = "mukeshg@321";
		//String DbName = "ums_api";
		//Class.forName("com.mysql.jdbc.Driver"); 
		//con=DriverManager.getConnection(dbUrl+DbName,username,password);
		//stmt = con.createStatement();
	}  
	
	
	
	
	public static void CreateSSHTunnel() throws JSchException
	{
		JSch jsch = new JSch();
		session = jsch.getSession("qa_automation", "");
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword("crownit");
		session.connect(10000);
		session.setPortForwardingL(23306, "127.0.0.1", 3306);
	}

	public static void disconnectDBConnection() throws ClassNotFoundException, SQLException, IOException, JSchException
	{
		//session.disconnect();
		con.close();
	}

	public static ResultSet executeQuery(String query) throws SQLException
	{  
		ResultSet rs= stmt.executeQuery(query); 
		return rs;
	}

	public static int executeUpdate(String query) throws SQLException
	{   
		int rs= stmt.executeUpdate(query);
		return rs;	
	}
	
}