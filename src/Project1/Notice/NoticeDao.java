package Project1.Notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import JDBC.DBConnect;

public class NoticeDao {
	// 추가, 검색, 수정, 삭제

	private DBConnect db;

	public NoticeDao() {
		db = DBConnect.getInstance();

	}

	public void insert(Notice n,int id) {
		Connection conn = db.conn();
		String sql = "insert into notice values(?, seq_no.nextval, sysdate, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, n.getSalary());
			pstmt.setString(3, n.getJob());
			pstmt.setDate(4, n.getDeadLine());
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
	
	public void update(Notice n, int num) {
		Connection conn = db.conn();
		String sql = "update notice set salary=?,job =? ,DeadLine=? where com_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n.getSalary());
			pstmt.setString(2, n.getJob());
			pstmt.setDate(3, n.getDeadLine());
			pstmt.setInt(4, num);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 수정됨");
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
	
	public Notice select(int com_id) {
		Connection conn = db.conn();
		String sql = "select * from notice where com_id=? order by com_id";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,com_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Notice(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getDate(6));
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
		return null;
	}
	
	//직무 이름으로 검색.
	public ArrayList<Notice> selectByJob(String job) {
		Connection conn = db.conn();
		String sql = "select * from notice where job like ? order by com_id";
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+ job + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next():읽을 줄로 이동을 해서 읽을 값이 있으면 true, 없으면 false
				list.add(new Notice(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getDate(6)));
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
	
	public ArrayList<Notice> selectAll(){
		Connection conn = db.conn();
		String sql = "select * from notice order by period";
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {// rs.next():읽을 줄로 이동을 해서 읽을 값이 있으면 true, 없으면 false
				list.add(new Notice(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getDate(6)));
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

	public void delete(int com_id) {
		Connection conn = db.conn();
		String sql = "delete notice where com_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_id);

			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + " 줄 삭제됨");
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
	
	public void updateDate(Date sqldate,int com_id) {
	    Connection conn = db.conn();
	    String sql = "update notice set period = ? where com_id=?";
	    try {
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        // 오늘 날짜를 가져와서 SQL Date로 변환
	        Date today = Date.valueOf(LocalDate.now());
	        pstmt.setDate(1, today);
	        pstmt.setInt(2, com_id);
	        int cnt = pstmt.executeUpdate();
	        System.out.println(cnt + " 줄 수정됨");
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
