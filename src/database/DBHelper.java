package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper
{
	protected Connection conn;
	
	public DBHelper() 
	{
		String connString = "jdbc:mysql://localhost:3306/biblioteka2?user=root&password=";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connString);
		} 
		catch (SQLException e)
		{
			System.out.println("Neka greska sa bazom!");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void zatvoriKonekciju()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConn() {return conn;}
	public void setConn(Connection conn) {this.conn = conn;}
	
}
