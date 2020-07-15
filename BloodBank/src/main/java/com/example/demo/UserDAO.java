package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends BaseDAO{
	public static User validate(String username, String password){
		User res = null;
		Connection con = null;
		try {
			con = getCon();
			String sql = "select * from app_users where username = ? and password = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, username);
			st.setString(i++, password);
			ResultSet rs =  st.executeQuery();
			while(rs.next()) {
				User p1 = new User();
				p1.setId(rs.getString("id"));
				p1.setUserName(rs.getString("username"));
				p1.setPassword(rs.getString("password"));
				p1.setRole(rs.getString("role"));
				res = p1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	public static int insert(User p1){
		int res = 0;
		Connection con = null;
		try {
			con = getCon();
			String sql = "insert into app_users (username, password, role) values (?,?, ?) ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getUserName());
			st.setString(i++, p1.getPassword());
			st.setString(i++, p1.getRole());
			res =  st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	public static int update(User p1){
		int res = 0;
		Connection con = null;
		try {
			con = getCon();
			String sql = "update app_users set username=?, password = ?, role=? where id = ?  ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getUserName());
			st.setString(i++, p1.getPassword());
			st.setString(i++, p1.getRole());
			st.setString(i++, p1.getId());
			res =  st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	public static int delete(String id){
		int res = 0;
		Connection con = null;
		try {
			con = getCon();
			String sql = "delete from app_users where id = ?  ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, id);
			res =  st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}

}
