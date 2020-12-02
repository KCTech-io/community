package io.community.translation.munselvom.auth;

import java.util.Date;

public class UserForm {
	

	public UserForm() {
		
	}
	public UserForm(String userId, String username, String email, String gender, String password,
			String confirmPassword, Date dob, String reason, String self) {

		this.userId = userId;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.dob = dob;
		this.reason = reason;
		this.self = self;
	}
	private String userId;
	private String username;
	private String email;
	private String gender;
	private String password;
	private String confirmPassword;
	private Date   dob;
	private String dobtext;
	private String reason;
	private String self;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;	
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDobtext() {
		return dobtext;
	}
	public void setDobtext(String dobtext) {
		this.dobtext = dobtext;
	}
}