package com.website.mokshagarbatti.model;

public class ForgotPassWordModel {

	
	private String username;
	
	private String password;

	private String recoverQuestion;
	
	private String recoverAnswer;
	

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

}
