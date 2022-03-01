package com.website.mokshagarbatti.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.website.mokshagarbatti.entity.LoginEntity;
import com.website.mokshagarbatti.entity.UserEntity;
import com.website.mokshagarbatti.model.AddAdminRequestModel;
import com.website.mokshagarbatti.model.ForgotPassWordModel;
import com.website.mokshagarbatti.model.LoginRequestModel;
import com.website.mokshagarbatti.repository.LoginRepo;
import com.website.mokshagarbatti.repository.UserRepo;

@Service
public class LoginLogoutService {

	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	public String checkLoginDetails(@Valid LoginRequestModel request) {
		Optional<LoginEntity> entity = loginRepo.checkLoginDetails(request.getUsername().toLowerCase(),
				request.getPassword(),request.getRoles());
		
		if (entity.isPresent()) {
			return "Login Successful";
		}
		return "Username/Password not valid";
	}

	public String addNewAdmin(@Valid AddAdminRequestModel request) {
		Optional<LoginEntity> entity = loginRepo.findByUserName(request.getUsername().toLowerCase());
		if (entity.isPresent()) {
			return "User with this email id already exist";
		}
		
		LoginEntity newAdmin = new LoginEntity();
		Date createdDate = new Date();
		newAdmin.setUserName(request.getUsername());
		newAdmin.setPassword(request.getPassword());
		newAdmin.setRole("ADMIN");
		newAdmin.setRecoverPassQuestion(request.getRecoverQuestion());
		newAdmin.setRecoverPassAnswer(request.getRecoverAnswer());
		newAdmin.setCreatedBy(request.getLoggedInUser());
		newAdmin.setCreatedAt(createdDate);
		
		loginRepo.save(newAdmin);
		return "New Admin added successfully";
		
	}

	public String updateAdminDetails(@Valid AddAdminRequestModel request) {
		
		Optional<LoginEntity> entity = loginRepo.findByUserName(request.getUsername().toLowerCase());
		if (entity.isPresent()) {
			LoginEntity newAdmin = entity.get();
			Date createdDate = new Date();
			newAdmin.setUserName(request.getUsername());
			newAdmin.setPassword(request.getPassword());
			newAdmin.setRole("ADMIN");
			newAdmin.setRecoverPassQuestion(request.getRecoverQuestion());
			newAdmin.setRecoverPassAnswer(request.getRecoverAnswer());
			newAdmin.setModifiedBy(request.getLoggedInUser());
			newAdmin.setModifiedAt(createdDate);
			
			loginRepo.save(newAdmin);
			return "Admin details updated successfully";
			
		}
		
		return "User is not available with given details";
		
	}

	public Object findProfileByEmail(String userEmail) {
		Optional<UserEntity> userOptional = userRepo.findByEmailAndShop(userEmail.toLowerCase());
		if (userOptional.isPresent()) {
			return userOptional.get();
		}else {
			Optional<LoginEntity> admin = loginRepo.findByUserName(userEmail.toLowerCase());
			if (admin.isPresent()) {
				return admin.get();
			}
		}
		return null;
		
	}

	public String forgotPassword(@Valid ForgotPassWordModel request) {
		
		 Optional<LoginEntity> checkUser = loginRepo.forgotPassword(request.getUsername().toLowerCase(),request.getRecoverQuestion().toLowerCase()
				 ,request.getRecoverAnswer().toLowerCase());
		 
		 if (checkUser.isPresent()) {
			 LoginEntity login = checkUser.get();
			 
			 login.setPassword(request.getPassword());
			 login.setModifiedAt(new Date());
			 login.setModifiedBy(request.getUsername());
			 loginRepo.save(login);
			 
			 return "Password changed successfully !";
		} else {
			 return "User not Present with given details!";
		}
		 
		
	}

}
