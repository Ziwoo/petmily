package kr.data.domain;

//ÀÚ¹Ùºó»ý¼º
import java.sql.Date;

public class Data {
	private int pd_num;
	private String mem_id;
	private String pd_passwd;
	private String pd_content;
	private String pd_title;
	private Date pd_regdate;
	private String pd_pic;
	
	public boolean isCheckedPasswd(String userPasswd) {
		if(pd_passwd.equals(userPasswd)){
			return true;
		}
		return false;
	}

	public int getPd_num() {
		return pd_num;
	}

	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getPd_passwd() {
		return pd_passwd;
	}

	public void setPd_passwd(String pd_passwd) {
		this.pd_passwd = pd_passwd;
	}

	public String getPd_content() {
		return pd_content;
	}

	public void setPd_content(String pd_content) {
		this.pd_content = pd_content;
	}

	public String getPd_title() {
		return pd_title;
	}

	public void setPd_title(String pd_title) {
		this.pd_title = pd_title;
	}

	public Date getPd_regdate() {
		return pd_regdate;
	}

	public void setPd_regdate(Date pd_regdate) {
		this.pd_regdate = pd_regdate;
	}

	public String getPd_pic() {
		return pd_pic;
	}

	public void setPd_pic(String pd_pic) {
		this.pd_pic = pd_pic;
	}
}