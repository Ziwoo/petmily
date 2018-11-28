package kr.breed.domain;

import java.sql.Date;

public class Breed {
	private int br_num;
	private String mem_id;
	private String br_subject;
	private String br_content;
	private String br_passwd;
	private Date br_regdate;
	private String br_pic;
	private int br_readcount;
	
	//비밀번호 일치 여부 확인 메소드
	public boolean isCheckedPasswd(String userPasswd){
		if(br_passwd.equals(userPasswd)){
			return true;
		}
		return false;
	}
	
	public int getBr_num() {
		return br_num;
	}
	public void setBr_num(int br_num) {
		this.br_num = br_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getBr_subject() {
		return br_subject;
	}
	public void setBr_subject(String br_subject) {
		this.br_subject = br_subject;
	}
	public String getBr_content() {
		return br_content;
	}
	public void setBr_content(String br_content) {
		this.br_content = br_content;
	}
	public String getBr_passwd() {
		return br_passwd;
	}
	public void setBr_passwd(String br_passwd) {
		this.br_passwd = br_passwd;
	}
	public Date getBr_regdate() {
		return br_regdate;
	}
	public void setBr_regdate(Date br_regdate) {
		this.br_regdate = br_regdate;
	}
	public String getBr_pic() {
		return br_pic;
	}
	public void setBr_pic(String br_pic) {
		this.br_pic = br_pic;
	}
	public int getBr_readcount() {
		return br_readcount;
	}
	public void setBr_readcount(int br_readcount) {
		this.br_readcount = br_readcount;
	}
	
	
}
