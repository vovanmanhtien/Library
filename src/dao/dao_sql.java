package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class dao_sql {
		public static Connection cn;
		//connect database SQL
		public void connect() {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String st = "jdbc:sqlserver://DESKTOP-FRG682I:1433;databaseName=LIBRARI; user=sa; password=123456";
				cn = DriverManager.getConnection(st);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//construction
		public dao_sql(){
			connect();
		}
}
