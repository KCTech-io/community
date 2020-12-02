package io.community.translation.munselvom.auth;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_list")
public class UserList {
	
	public UserList() {

	}
	
	public UserList(String username, String emailid, Date dob, String gender, String password,
			String reason, String self,String role,boolean active) {

		this.userName = username;
		this.role = role;
		this.emailId = emailid;
		this.dob = dob;
		this.gender = gender;
		this.password = password;
		this.reason = reason;
		this.self = self;
		this.active = active;
	}
	
	@Id
	@Column(name="userid")
	private String userName;
	@Column(name="username")
	private String userdesc;
	private String emailId;
	private Date   dob;
	private String gender;
	private String password;
	private String reason;
	private String self;
	private String role;
	private boolean active;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getEmailid() {
		return emailId;
	}
	public void setEmailid(String emailid) {
		this.emailId = emailid;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getUserdesc() {
		return userdesc;
	}

	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}
	
}
