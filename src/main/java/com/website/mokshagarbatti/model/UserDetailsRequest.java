package com.website.mokshagarbatti.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

public class UserDetailsRequest {

	@NotEmpty(message = "user details required")
	private String userEmail;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	
}
