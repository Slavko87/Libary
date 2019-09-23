package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class UtilDB 
{
	public static int vratiPoslednjiID (String tabela, String imePolja)
	{
		DBHelper db = new DBHelper();
		int poslednji = 0;
		String upit = "select MAX(" + imePolja + ") as max from " + tabela;
		
		try 
		{
			Statement s = db.getConn().createStatement();
			ResultSet rs = s.executeQuery(upit);
			
			while(rs.next())
			{
				poslednji = rs.getInt("max");
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
			return -1;
		}
		return poslednji;
	}
	
	public static String konvertujeTimestampUString(Timestamp ts)
	{
		return ts.toString().substring(0, 19);
	}
	
}
