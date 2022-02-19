package com.website.mokshagarbatti.model;

import javax.validation.constraints.NotBlank;


public class AddAdminRequestModel {

	@NotBlank(message = "username is required")
	private String username;
	@NotBlank(message = "password is required")
	private String password;
	@NotBlank(message = "role is required")
	private String role;
	@NotBlank(message = "recoverQuestion is required")
	private String recoverQuestion;
	@NotBlank(message = "recoverAnswer is required")
	private String recoverAnswer;
	@NotBlank(message = "createdBy is required")
	private String loggedInUser;
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
	public String getRole() {
		return role;
	}
	public void setString(String role) {
		this.role = role;
	}
	public String getRecoverQuestion() {
		return recoverQuestion;
	}
	public void setRecoverQuestion(String recoverQuestion) {
		this.recoverQuestion = recoverQuestion;
	}
	public String getRecoverAnswer() {
		return recoverAnswer;
	}
	public void setRecoverAnswer(String recoverAnswer) {
		this.recoverAnswer = recoverAnswer;
	}
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	
}
