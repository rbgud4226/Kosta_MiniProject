package Project1.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.DBConnect;

public class CompanyDao {

	private DBConnect db;

	public CompanyDao() {
		db = DBConnect.getInstance();
	}
	

	// 기업 정보 등록
	public int insertCompany(Company c, int num) {
		Connection conn = db.conn();
		int cnt = 0;
		String sql = "insert into company values(?,company_seq.nextval,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, c.getCname());
			ps.setString(3, c.getField());
			ps.setString(4, c.getId());
			ps.setString(5, c.getTell());
			ps.setString(6, c.getEmail());
			ps.setString(7, c.getAddr());
			cnt = ps.executeUpdate();
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

	// 기업 번호로 정보 수정 - 기업이름, 기업분야, 기업전화번호, 기업 이메일, 기업주소
	public int updateCompany(Company c, int num) {
		Connection conn = db.conn();
		int cnt = 0;
		String sql = "update company set cname=?, field=?, tell=?, email=?, addr=? where userid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCname());
			ps.setString(2, c.getField());
			ps.setString(3, c.getTell());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getAddr());
			ps.setInt(6, num);
			cnt = ps.executeUpdate();
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

	// 기업 정보 삭제
	public int deleteCompany(int cnum) {
		Connection conn = db.conn();
		int cnt = 0;
		String sql = "delete from company where cnum=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			cnt = ps.executeUpdate();
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

	// 기업 정보 조회 (분야)
	public ArrayList<Company> selectCompanyByField(String field) {
		ArrayList<Company> list = new ArrayList<>();
		Connection conn = db.conn();
		String sql = "select * from company where field =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, field);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Company(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
			return list;
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

	// 기업 정보 전체조회
	public ArrayList<Company> selectAllCompany() {
		ArrayList<Company> list = new ArrayList<>();
		Connection conn = db.conn();
		String sql = "select * from company order by cnum";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Company(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
			return list;
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

	// 기업정보검색(기업번호) admin
	public Company selectCompany(int userid) {
		Connection conn = db.conn();
		Company c = null;
		String sql = "select * from company where userid =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Company(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				return c;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return c;
	}

	// 기업정보검색 cnum
	public Company selectCompanyByCnum(int cnum) {
		Connection conn = db.conn();
		Company c = null;
		String sql = "select * from company where cnum =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Company(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
}
