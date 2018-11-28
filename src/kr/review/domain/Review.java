package kr.review.domain;
//ÀÚ¹Ùºó »ý¼º
import java.sql.Date;

public class Review {
	private int rv_num;
	private String rv_subject;
	private String rv_content;
	private String rv_pet_picture;
	private Date rv_now_date;
	private int rv_readcount;
	private String ip;
	private String mem_id;
	
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public String getRv_subject() {
		return rv_subject;
	}
	public void setRv_subject(String rv_subject) {
		this.rv_subject = rv_subject;
	}
	public String getRv_content() {
		return rv_content;
	}
	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}
	public String getRv_pet_picture() {
		return rv_pet_picture;
	}
	public void setRv_pet_picture(String rv_pet_picture) {
		this.rv_pet_picture = rv_pet_picture;
	}
	public Date getRv_now_date() {
		return rv_now_date;
	}
	public void setRv_now_date(Date rv_now_date) {
		this.rv_now_date = rv_now_date;
	}
	public int getRv_readcount() {
		return rv_readcount;
	}
	public void setRv_readcount(int rv_readcount) {
		this.rv_readcount = rv_readcount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
}