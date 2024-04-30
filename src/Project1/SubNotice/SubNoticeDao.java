package Project1.SubNotice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.DBConnect;

public class SubNoticeDao {
	private DBConnect db;

	public SubNoticeDao() {
		db = DBConnect.getInstance();
	}

	public void insert(int u, int o) {
		Connection conn = db.conn();
		String sql = "insert into SubNotice values(?,?, seq1.nextval)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u);
			pstmt.setInt(2, o);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 추가됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	// 지원자 전체
	public ArrayList<SubNotice> selectAll() {
		Connection conn = db.conn();
		String sql = "select * from SubNotice order by subnotice_id";
		ArrayList<SubNotice> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next():읽을 줄로 이동을 해서 읽을 값이 있으면 true, 없으면 false
				list.add(new SubNotice(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void delete(int subnotice_id) {
		Connection conn = db.conn();
		String sql = "delete SubNotice where subnotice_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subnotice_id);

			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 삭제됨");
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
