package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DonorDAO extends BaseDAO{
	public static int insertDonor(Donor p1){
		int res = 0;
		Connection con = null;
		try {
			con = getCon();
			String sql = "insert into donor (donor_name, blood_gp) values (?,?) ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getDonorName());
			st.setString(i++, p1.getBloodGp());
			res =  st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	public static int update(Donor p1){
		int res = 0;
		Connection con = null;
		try {
			con = getCon();
			String sql = "update donor set donor_name=?, blood_gp = ? where id = ?  ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getDonorName());
			st.setString(i++, p1.getBloodGp());
			st.setString(i++, p1.getId());
			res =  st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
}
