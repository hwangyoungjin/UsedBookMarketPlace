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
	
	public String getPrtInfo() {
		StringBuffer buf =	new StringBuffer();
		if(this.getUid().length() > "\t\t\t\t\t\t".length()) { // 이름의 길이가 6보다 크다면
			 buf.append(this.getUid()).append("\t");
		 }
		 else {
			 buf.append(this.getUid()).append("\t\t"); 
		 }
		 buf.append(this.getPw()).append("\t\t");
		 buf.append(this.getName()).append("\t");
		 buf.append(this.getPhone()).append("\t");
		 if(this.getEmail().length() > "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t".length()) { // 이름의 길이가 15보다 크다면
			 buf.append(this.getEmail()).append("\t");
		 }
		 else {
			 buf.append(this.getEmail()).append("\t\t"); 
		 }
		 buf.append(this.getState()).append("\t");
		 buf.append(this.getManagerYn());
		 return buf.toString();
	}
	
	@Override
	public String toString() {

		String dataStr = "["
				+ "uid=" + this.getUid() + "\t"
				+ "pw=" + this.getPw() + "\t"
				+ "name=" + this.getName() + "\t"
				+ "phone=" + this.getPhone() + "\t"
				+ "email=" + this.getEmail() + "\t"
				+ "state=" + this.getState() + "\t"
				+ "managerYn=" + this.getManagerYn() + "]\n";

		return dataStr;
	}
	
}
