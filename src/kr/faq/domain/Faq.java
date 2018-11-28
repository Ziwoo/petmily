package kr.faq.domain;

import java.sql.Date;

public class Faq {
	private int faq_num;
	private String mem_id;
	private String faq_subject;
	private String faq_content;
	private Date faq_reg_date;
	private int faq_readcount;
	private String faq_passwd;
	
	public int getFaq_num() {
		return faq_num;
	}
	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFaq_subject() {
		return faq_subject;
	}
	public void setFaq_subject(String faq_subject) {
		this.faq_subject = faq_subject;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
	public Date getFaq_reg_date() {
		return faq_reg_date;
	}
	public void setFaq_reg_date(Date faq_reg_date) {
		this.faq_reg_date = faq_reg_date;
	}
	public int getFaq_readcount() {
		return faq_readcount;
	}
	public void setFaq_readcount(int faq_readcount) {
		this.faq_readcount = faq_readcount;
	}
	public String getFaq_passwd() {
		return faq_passwd;
	}
	public void setFaq_passwd(String faq_passwd) {
		this.faq_passwd = faq_passwd;
	}
	
	

}
