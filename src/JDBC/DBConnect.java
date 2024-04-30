package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//싱글톤
//
//승훈 테스트용 커밋 합닌다.
//규형 테스트용 
public class DBConnect {
	private static DBConnect dbconn = new DBConnect();
	private String url = "jdbc:oracle:thin:@192.168.0.35:1521/xe"; // localhost 명을 ip 주소로
	
	private DBConnect() {}
	
	public Connection conn() {		
		try {
			//드라이버 로드. 클래스 이름으로 찾아서 메모리에 얹음
			Class.forName("oracle.jdbc.OracleDriver");
			//db에 로그인. 세션 수립하고 생성된 connection 객체 반환
			return DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch 
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DBConnect getInstance() {
		return dbconn;
	}
	
}


