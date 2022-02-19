package com.website.mokshagarbatti.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "login_details")
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginId;
	@NotEmpty(message = "Email can't be empty!")
	private String userName;
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	private String password;
	@NotEmpty(message = "Role type can't be empty!")
	private String role;
	@NotEmpty(message = "Select password recover question!")
	private String recoverPassQuestion;
	@NotEmpty(message = "Select password recover answer!")
	private String recoverPassAnswer;
	private Date createdAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public void setRole(String role) {
		this.role = role;
	}
	public String getRecoverPassQuestion() {
		return recoverPassQuestion;
	}
	public void setRecoverPassQuestion(String recoverPassQuestion) {
		this.recoverPassQuestion = recoverPassQuestion;
	}
	public String getRecoverPassAnswer() {
		return recoverPassAnswer;
	}
	public void setRecoverPassAnswer(String recoverPassAnswer) {
		this.recoverPassAnswer = recoverPassAnswer;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
}
