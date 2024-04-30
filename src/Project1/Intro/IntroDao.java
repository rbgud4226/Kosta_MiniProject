package Project1.Intro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.DBConnect;

public class IntroDao {
	private DBConnect db;

	public IntroDao() {
		db = DBConnect.getInstance();
	}
	
	// 이력서 등록
	public void insert(Intro i, int num) {
		Connection conn = db.conn();

		String sql = "insert into intro values(?,?,?,seq_intro.nextval,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, i.getTitle());
			pstmt.setString(3, i.getContent());
//			pstmt.setInt(4, i.getId());
			pstmt.setInt(4, i.getCnum());

			int cnt = pstmt.executeUpdate();
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
	
	// 이력서 수정 - 제목, 내용, 제출 회사
	public int update(Intro i, int id) {
		Connection conn = db.conn();

		String sql = "update intro set title=?, content=? where unum=? and id=?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getTitle());
			pstmt.setString(2, i.getContent());			
			pstmt.setInt(3, i.getUnum());
			pstmt.setInt(4, id);

			cnt = pstmt.executeUpdate();
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
		return cnt;
	}
	
	// 이력서 삭제
	public void delete(int id, int unum) {
		Connection conn = db.conn();
		String sql = "delete from intro where unum=? and id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, unum);
			pstmt.setInt(2, id);

			int cnt = pstmt.executeUpdate();
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
	// 내 이력서 조회(번호)
	public Intro selectById(int id, int unum) {
		Connection conn = db.conn();
		String sql = "select * from intro where unum=? and id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, unum);
			pstmt.setInt(2, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Intro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
	// 내 이력서 조회(제목)
	public ArrayList<Intro> selectByTitle(String title, int unum) {
		Connection conn = db.conn();
		String sql = "select * from intro where unum=? and title like ? order by id";
		ArrayList<Intro> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, unum);
			pstmt.setString(2, "%" + title + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Intro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
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

	// 내 이력서 조회(기업번호)
	public ArrayList<Intro> selectByCom(int cnum){
		Connection conn = db.conn();
		String sql = "select * from intro where cnum = ?";
		ArrayList<Intro> list = new ArrayList<>();
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new Intro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
    } catch (SQLException e) {
      e.printStackTrace();
    }
		return list;
  }
	
	// 이력서 조회(전체)
	public ArrayList<Intro> selectAll() {
		Connection conn = db.conn();
		String sql = "select * from intro order by id";
		ArrayList<Intro> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Intro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
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
	
	// 내 이력서 조회(전체)
		public ArrayList<Intro> selectMyIntro(int unum) {
			Connection conn = db.conn();
			String sql = "select * from intro where unum =? ";
			ArrayList<Intro> list = new ArrayList<>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, unum);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add(new Intro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
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
}

