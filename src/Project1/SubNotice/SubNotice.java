package Project1.SubNotice;

public class SubNotice {
	private int unum; // 포린키 유저넘버
	private int com_id; // 포린키 노티스 넘버
	private int subNotice_id; // 프라이머리키 
	
	
	public SubNotice(int unum, int com_id, int subNotice_id) {
		super();
		this.unum = unum;
		this.com_id = com_id;
		this.subNotice_id = subNotice_id;
	}


	public int getUnum() {
		return unum;
	}


	public void setUnum(int unum) {
		this.unum = unum;
	}


	public int getCom_id() {
		return com_id;
	}


	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}


	public int getSubNotice_id() {
		return subNotice_id;
	}


	public void setSubNotice_id(int subNotice_id) {
		this.subNotice_id = subNotice_id;
	}


	@Override
	public String toString() {
		return "SubNotice [unum=" + unum + ", com_id=" + com_id + ", subNotice_id=" + subNotice_id + "]";
	}
}
