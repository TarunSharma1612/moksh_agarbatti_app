package com.website.mokshagarbatti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.website.mokshagarbatti.constant.Roles;

import lombok.Data;

@Entity
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retailer_id")
    private Long userId;
    
    @NotEmpty(message = "Retailer name can't be empty!")
    private String retailerShopName;
    
    @NotNull(message = "Enter address 1!")
    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @NotNull(message = "Enter city!")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Enter state!")
    @Column(name = "state")
    private String state;

    @NotNull(message = "Enter postal code!")
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull(message = "Select Country!")
    private String country;
    @NotEmpty(message = "Phone number can't be empty!")
    private String phone;
    
    @Column(name = "username")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
    private String role;
    
    private Date createdAt;
    
	private Date modifiedAt;
	
	private String createdBy;
	
	private String modifiedBy;
	
//	private String userPhotoPath;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

//	public String getUserPhotoPath() {
//		return userPhotoPath;
//	}
//
//	public void setUserPhotoPath(String userPhotoPath) {
//		this.userPhotoPath = userPhotoPath;
//	}
	
	

}