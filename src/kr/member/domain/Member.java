package kr.member.domain;

import java.sql.Date;

public class Member {
	private String mem_id;
	private String mem_name;
	private String mem_passwd;
	private String mem_cell;
	private String mem_email;
	private String mem_zipcode1;
	private String mem_zipcode2;
	public String getMem_zipcode1() {
		return mem_zipcode1;
	}

	public void setMem_zipcode1(String mem_zipcode1) {
		this.mem_zipcode1 = mem_zipcode1;
	}

	public String getMem_zipcode2() {
		return mem_zipcode2;
	}

	public void setMem_zipcode2(String mem_zipcode2) {
		this.mem_zipcode2 = mem_zipcode2;
	}

	private String mem_addr1;
	private String mem_addr2;
	private Date mem_register;
	private String mem_level;
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd){
		if(mem_passwd.equals(userPasswd)){
			return true;
		}
		return false;
	}
	
	//이메일 일치 여부 체크(ID 또는 비밀번호 찾기)
	public boolean isCheckedEmail(String mem_name){
		if(mem_name.equals(mem_name)){
			return true;
		}
		return false;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_passwd() {
		return mem_passwd;
	}

	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	
	public String getMem_cell() {
		return mem_cell;
	}

	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}



	public String getMem_addr1() {
		return mem_addr1;
	}

	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}

	public String getMem_addr2() {
		return mem_addr2;
	}

	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}

	public Date getMem_register() {
		return mem_register;
	}

	public void setMem_register(Date mem_register) {
		this.mem_register = mem_register;
	}

	public String getMem_level() {
		return mem_level;
	}

	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
}