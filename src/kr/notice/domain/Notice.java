package kr.notice.domain;

import java.sql.Date;

public class Notice {
	private int nb_num;
	private String mem_id;
	private String nb_subject;
	private String nb_content;
	private String nb_passwd;
	private String filename;
	private Date nb_reg_date;
	private int nb_readcount;
	public int getNb_num() {
		return nb_num;
	}
	public void setNb_num(int nb_num) {
		this.nb_num = nb_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNb_subject() {
		return nb_subject;
	}
	public void setNb_subject(String nb_subject) {
		this.nb_subject = nb_subject;
	}
	public String getNb_content() {
		return nb_content;
	}
	public void setNb_content(String nb_content) {
		this.nb_content = nb_content;
	}
	public String getNb_passwd() {
		return nb_passwd;
	}
	public void setNb_passwd(String nb_passwd) {
		this.nb_passwd = nb_passwd;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getNb_reg_date() {
		return nb_reg_date;
	}
	public void setNb_reg_date(Date nb_reg_date) {
		this.nb_reg_date = nb_reg_date;
	}
	public int getNb_readcount() {
		return nb_readcount;
	}
	public void setNb_readcount(int nb_readcount) {
		this.nb_readcount = nb_readcount;
	}
	
	
	

}
