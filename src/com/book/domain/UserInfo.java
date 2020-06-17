package com.book.domain;

/**
 * 사용자정보 객체
 * @author USER
 *
 */
public class UserInfo {

	private String uid;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String state;
	private String managerYn;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getManagerYn() {
		return managerYn;
	}
	public void setManagerYn(String managerYn) {
		this.managerYn = managerYn;
	}
	@Override
	public String toString() {

		String dataStr = "["
				+ "uid=" + this.getUid() + "\n"
				+ "pw=" + this.getPw() + "\n"
				+ "name=" + this.getName() + "\n"
				+ "phone=" + this.getPhone() + "\n"
				+ "email=" + this.getEmail() + "\n"
				+ "state=" + this.getState() + "\n"
				+ "managerYn=" + this.getManagerYn() + "]";

		return dataStr;
	}
	
}
