package com.model;

public class User {
	private String userid;
	private String password;
	private String password_Confirm;
	private String email;
	private String sex;
	private String PINcode;
	private String code;
	private int sum;
	
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPINcode() {
		return PINcode;
	}
	public void setPINcode(String pINcode) {
		PINcode = pINcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword_Confirm() {
		return password_Confirm;
	}
	public void setPassword_Confirm(String password_Confirm) {
		this.password_Confirm = password_Confirm;
	}

}
