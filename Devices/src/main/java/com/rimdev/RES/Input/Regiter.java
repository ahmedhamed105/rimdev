package com.rimdev.RES.Input;

public class Regiter {

	private String username;
	private String password;
	private String confirm_password;
	private String email;
	private String land_phone;
	private String mobile;
	private String postal_code;
	private String ip;
	private String browser;
	
	
	
	
	public Regiter() {
		super();
	}
	public Regiter(String username, String password, String confirm_password, String email, String land_phone,
			String mobile, String postal_code, String ip, String browser) {
		super();
		this.username = username;
		this.password = password;
		this.confirm_password = confirm_password;
		this.email = email;
		this.land_phone = land_phone;
		this.mobile = mobile;
		this.postal_code = postal_code;
		this.ip = ip;
		this.browser = browser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLand_phone() {
		return land_phone;
	}
	public void setLand_phone(String land_phone) {
		this.land_phone = land_phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	
	

}
