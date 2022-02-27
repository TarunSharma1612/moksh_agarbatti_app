package com.website.mokshagarbatti.model;


import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.website.mokshagarbatti.constant.Roles;

import lombok.Data;

public class AddUserRequestModel {
	
	@NotEmpty(message = "email can't be empty!")
	private String email;
	@NotEmpty(message = "password can't be empty!")
	private String password;
	@NotEmpty(message = "Retailer name can't be empty!")
	private String retailerShopName;
	@NotEmpty(message = "address1 can't be empty!")
	private String address1;
	private String address2;
	@NotEmpty(message = "city name can't be empty!")
	private String city;
	@NotEmpty(message = "state name can't be empty!")
	private String state;
	@NotEmpty(message = "postal address can't be empty!")
	private String postalCode;
	@NotEmpty(message = "country name can't be empty!")
	private String country;
	@NotEmpty(message = "contact number can't be empty!")
	private String phone;
	@NotEmpty(message = "role can't be empty!")
	private String roles;
	@NotEmpty(message = "recover question can't be empty!")
	private String recoverQuestion;
	@NotEmpty(message = "recover answer can't be empty!")
	private String recoverAnswer;
	
	private String loggedInUser;
	
//	private MultipartFile image;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetailerShopName() {
		return retailerShopName;
	}

	public void setRetailerShopName(String retailerShopName) {
		this.retailerShopName = retailerShopName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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

//	public MultipartFile getImage() {
//		return image;
//	}
//
//	public void setImage(MultipartFile image) {
//		this.image = image;
//	}

	
	
}
