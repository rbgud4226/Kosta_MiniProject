package Project1.Notice;

import java.util.Date;

public class Notice {
	private int cNum;
	private int com_id;
	private Date period;
	private int salary;
	private String job;
	private java.sql.Date deadLine;

	public Notice() {
	}

	public Notice(int cNum, int com_id, Date period, int salary, String job, java.sql.Date deadLine) {
		super();
		this.cNum = cNum;
		this.com_id = com_id;
		this.period = period;
		this.salary = salary;
		this.job = job;
		this.deadLine = deadLine;
	}

	public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public java.sql.Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(java.sql.Date deadLine) {
		this.deadLine = deadLine;
	}

	@Override
	public String toString() {
		return "Notice [cNum=" + cNum + ", com_id=" + com_id + ", period=" + period + ", salary=" + salary + ", job="
				+ job + ", deadLine=" + deadLine + "]";
	}



	

	

	

	

	
}
