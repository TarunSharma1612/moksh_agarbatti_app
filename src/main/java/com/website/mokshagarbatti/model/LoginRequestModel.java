package com.website.mokshagarbatti.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.website.mokshagarbatti.constant.Roles;

import lombok.Data;

public class LoginRequestModel {

//	@NotBlank(message = "username is required")
	private String username;
//	@NotBlank(message = "password is required")
	private String password;
//	@NotNull(message = "password is required")
	private String roles;
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
}
