package Project1.Nuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.DBConnect;

public class NuserDao {
  DBConnect db;
  public NuserDao(){
    db = DBConnect.getInstance();
  }

  /**
   * 개인정보 추가
   * int userid, int unum, String name, String edu, String id, String tell
   * String email, String career, String license
   */
  public void insert(Nuser n, int id){
    Connection conn =db.conn();
    String sql = "insert into nuser values(?, seq_unum.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
    int cnt = 0;
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id); // userid
      pstmt.setString(2, n.getName()); // name
      pstmt.setString(3, n.getEdu()); // edu
      pstmt.setString(4, n.getId()); // id
      pstmt.setString(5, n.getTell()); // tell
      pstmt.setString(6, n.getEmail()); // email
      pstmt.setString(7, n.getAddr()); // addr
      pstmt.setString(8, n.getCareer()); // career
      pstmt.setString(9, n.getLicense()); // license
      cnt = pstmt.executeUpdate();
      System.out.println(cnt + " 줄 추가됨.");
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

  // 개인정보 수정
  public void update(Nuser n, int userid){
    Connection conn =db.conn();
    String sql = "update nuser set name = ?, edu = ?, tell = ?, email = ?, addr = ?, career = ?, license = ? where userid = ?";
    int cnt = 0;
    try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, n.getName()); // name
      pstmt.setString(2, n.getEdu()); // edu
      pstmt.setString(3, n.getTell()); // tell
      pstmt.setString(4, n.getEmail()); // email
      pstmt.setString(5, n.getAddr()); // addr
      pstmt.setString(6, n.getCareer()); // career
      pstmt.setString(7, n.getLicense()); // license
      pstmt.setInt(8, userid);
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

  // 개인정보 삭제
  public void delete(int num){
    Connection conn =db.conn();
    String sql = "delete nuser where unum = ?";
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

  // 개인정보 검색 (번호로 검색)
  public Nuser select(int num){
    Connection conn = db.conn();
    String sql = "select * from nuser where userid = ?";
    Nuser n = null;
    try {
      PreparedStatement prtmt = conn.prepareStatement(sql);
      prtmt.setInt(1, num);
      ResultSet rs = prtmt.executeQuery();
      if(rs.next()) {
        return new Nuser(rs.getInt(1), rs.getInt(2), rs.getString(3),
            rs.getString(4),rs.getString(5), rs.getString(6),
            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
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
    return n;
  }

  // 개인정보 검색 unum
  public Nuser selectByUnum(int num){
    Connection conn = db.conn();
    String sql = "select * from nuser where unum = ?";
    Nuser n = null;
    try {
      PreparedStatement prtmt = conn.prepareStatement(sql);
      prtmt.setInt(1, num);
      ResultSet rs = prtmt.executeQuery();
      if(rs.next()) {
        return new Nuser(rs.getInt(1), rs.getInt(2), rs.getString(3),
            rs.getString(4),rs.getString(5), rs.getString(6),
            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
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
    return n;
  }

  //개인정보 검색 (번호로 검색)
 public Nuser selectByUserid(int num){
   Connection conn = db.conn();
   String sql = "select * from nuser where userid = ?";
   Nuser n = null;
   try {
     PreparedStatement prtmt = conn.prepareStatement(sql);
     prtmt.setInt(1, num);
     ResultSet rs = prtmt.executeQuery();
     if(rs.next()) {
       return new Nuser(rs.getInt(1), rs.getInt(2), rs.getString(3),
           rs.getString(4),rs.getString(5), rs.getString(6),
           rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
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
   return n;
 }
}
