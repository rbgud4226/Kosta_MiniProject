package Project1.Admin;

public class Admin {
	private String logid;
	private String logpw;
	private int userid;
	private int grade;

	public Admin() {
	}

	public Admin(String logid, String logpw, int userid, int grade) {
		this.logid = logid;
		this.logpw = logpw;
		this.userid = userid;
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "[" + "회원유형=" + grade + ", 아이디='" + logid + '\'' + ", 비밀번호='" + logpw + '\'' + ", 회원번호=" + userid + "]";
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getLogpw() {
		return logpw;
	}

	public void setLogpw(String logpw) {
		this.logpw = logpw;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
