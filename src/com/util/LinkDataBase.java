package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LinkDataBase {
		public static Connection getCon() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:mysql://localhost:3306/project2";
				//String url = "jdbc:oracle:thin:lzz/1234@localhost:1521:ORCL";

				Connection con = DriverManager.getConnection(url,"root","1234");
				System.out.println("连接成功！");
				return con;
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
