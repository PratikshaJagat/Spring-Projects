package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
	public static Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/validationjwtrole","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void closeCon(Connection con) {
		try {
			con.close();

		} catch (Exception e) {
			e.printStackTrace();		}
	}

}
