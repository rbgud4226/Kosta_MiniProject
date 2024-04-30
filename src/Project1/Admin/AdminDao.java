package Project1.Admin;

import JDBC.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {
	private DBConnect db;

	public AdminDao() {
		db = DBConnect.getInstance();
	}

	// 추가
	public void insert(Admin a) {
		Connection conn = db.conn();
		String sql = "insert into admin values (?, ?, seq_userid.nextval, ?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getLogid());
			pstmt.setString(2, a.getLogpw());
			pstmt.setInt(3, a.getGrade());
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 추가됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 삭제
	public void delete(int num) {
		Connection conn = db.conn();
		String sql = "delete admin where userid = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 삭제됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 검색
	public Admin select(int num) {
		Connection conn = db.conn();
		String sql = "select * from admin where userid = ?";
		Admin a = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Admin(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public Admin selectId(String id) {
		Connection conn = db.conn();
		String sql = "select * from admin where logid = ?";
		Admin a = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				a = new Admin(rs.getString(1), rs.getNString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public ArrayList<Admin> selectAll() {
		Connection conn = db.conn();
		String sql = "select * from admin order by grade, userid";
		ArrayList<Admin> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Admin(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 수정
	public void update(Admin a, int userid) {
		Connection conn = db.conn();
		String sql = "update admin set logid = ?, logpw = ?, grade = ? where userid =?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getLogid());
			pstmt.setString(2, a.getLogpw());
			pstmt.setInt(3, a.getGrade());
			pstmt.setInt(4, userid);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 수정됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
