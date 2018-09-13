package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Admin;
import util.DBUtil;

public class AdminDao implements Serializable {

	public Admin findByCode(String adminCode) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
				"select * from admin_info_lhh "
				+ "where admin_code=?";
			PreparedStatement ps = 
				conn.prepareStatement(sql);
			ps.setString(1, adminCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Admin a = new Admin();
				a.setAdminId(rs.getInt("admin_id"));
				a.setAdminCode(rs.getString("admin_code"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("name"));
				a.setTelephone(rs.getString("telephone"));
				a.setEmail(rs.getString("email"));
				a.setEnrolldate(rs.getTimestamp("enrolldate"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"查询管理员失败",e);
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	public static void main(String[] args) {
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode("caocao");
		System.out.println(a.getAdminId());
		System.out.println(a.getName());
	}
	
}






