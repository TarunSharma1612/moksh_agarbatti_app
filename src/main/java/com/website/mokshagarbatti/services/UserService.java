package com.website.mokshagarbatti.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.website.mokshagarbatti.constant.Roles;
import com.website.mokshagarbatti.entity.LoginEntity;
import com.website.mokshagarbatti.entity.UserEntity;
import com.website.mokshagarbatti.exceptions.NotAuthenticatedUser;
import com.website.mokshagarbatti.exceptions.UserNotFoundException;
import com.website.mokshagarbatti.model.AddUserRequestModel;
import com.website.mokshagarbatti.model.UserDetailsRequest;
import com.website.mokshagarbatti.repository.LoginRepo;
import com.website.mokshagarbatti.repository.UserRepo;
import com.website.mokshagarbatti.utility.FileUploadUtil;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private LoginRepo loginRepo;
	
	public String addUser(AddUserRequestModel request) throws IOException {
		Optional<LoginEntity> loginDetails = loginRepo.findByUserName(request.getEmail());
		Optional<UserEntity> userDetails = userRepo.findByEmail(request.getEmail());
		if (loginDetails.isPresent() || userDetails.isPresent()) {
			return "user already exists with given details";
		}
		LoginEntity newAdmin = new LoginEntity();
		Date createdDate = new Date();
		newAdmin.setUserName(request.getEmail());
		newAdmin.setPassword(request.getPassword());
		newAdmin.setRole(request.getRoles());
		newAdmin.setRecoverPassQuestion(request.getRecoverQuestion());
		newAdmin.setRecoverPassAnswer(request.getRecoverAnswer());
		newAdmin.setCreatedBy(request.getLoggedInUser());
		newAdmin.setCreatedAt(createdDate);
		
		UserEntity newUser = new UserEntity();
		newUser.setEmail(request.getEmail());
		newUser.setRetailerShopName(request.getRetailerShopName());
		newUser.setRole(request.getRoles());
		newUser.setAddress1(request.getAddress1());
		newUser.setAddress2(request.getAddress2());
		newUser.setCity(request.getCity());
		newUser.setCountry(request.getCountry());
		newUser.setCreatedBy(request.getLoggedInUser());
		newUser.setCreatedAt(createdDate);
		newUser.setIsActive(true);
		newUser.setPhone(request.getPhone());
		newUser.setPostalCode(request.getPostalCode());
		newUser.setState(request.getState());
		String fileName =StringUtils.cleanPath(request.getImage().getOriginalFilename());
		
		
		String fileLocation = new File("src\\main\\resources\\static\\asset\\user\\") + "\\" 
				+ request.getEmail()+"_"+ fileName;
		
		newUser.setUserPhotoPath("asset\\user\\"+ request.getEmail()+"_"+ fileName);
		
		FileOutputStream output = new FileOutputStream(fileLocation);

		output.write(request.getImage().getBytes());

		output.close();
		
        loginRepo.save(newAdmin);
		userRepo.save(newUser);
        
		return "user added successfully";
		
		
		
	}

	public String updateUserDetails(AddUserRequestModel request) throws IOException {
		Optional<LoginEntity> loginDetails = loginRepo.findByUserName(request.getEmail());
		Optional<UserEntity> userDetails = userRepo.findByEmail(request.getEmail());
		if (loginDetails.isPresent() || userDetails.isPresent()) {
			LoginEntity newAdmin = loginDetails.get();
			Date createdDate = new Date();
			newAdmin.setUserName(request.getEmail());
			newAdmin.setPassword(request.getPassword());
			newAdmin.setRecoverPassQuestion(request.getRecoverQuestion());
			newAdmin.setRecoverPassAnswer(request.getRecoverAnswer());
			newAdmin.setModifiedBy(request.getLoggedInUser());
			newAdmin.setModifiedAt(createdDate);
			
			UserEntity newUser = userDetails.get();
			newUser.setEmail(request.getEmail());
			newUser.setRetailerShopName(request.getRetailerShopName());
			newUser.setAddress1(request.getAddress1());
			newUser.setAddress2(request.getAddress2());
			newUser.setCity(request.getCity());
			newUser.setCountry(request.getCountry());
			newUser.setModifiedBy(request.getLoggedInUser());
			newUser.setModifiedAt(createdDate);
			newUser.setPhone(request.getPhone());
			newUser.setPostalCode(request.getPostalCode());
			newUser.setState(request.getState());
			
			String fileName =StringUtils.cleanPath(request.getImage().getOriginalFilename());
			
			
			String fileLocation = new File("src\\main\\resources\\static\\asset\\user\\") + "\\" 
					+ request.getEmail()+"_"+ fileName;
			
			newUser.setUserPhotoPath("asset\\user\\"+ request.getEmail()+"_"+ fileName);
			
			FileOutputStream output = new FileOutputStream(fileLocation);

			output.write(request.getImage().getBytes());

			output.close();
			
			loginRepo.save(newAdmin);
			userRepo.save(newUser);
			
			
			return "user details updated successfully";
		}
		
		return "user not exists with given details";
	}

	public String deleteUserDetails(UserDetailsRequest request) {
		Optional<LoginEntity> loginDetails = loginRepo.findByUserName(request.getUserEmail());
		Optional<UserEntity> userDetails = userRepo.findByEmail(request.getUserEmail());
		if (loginDetails.isPresent() || userDetails.isPresent()) {
			LoginEntity existingAdmin = loginDetails.get();
			UserEntity existingUser = userDetails.get();
			
			loginRepo.delete(existingAdmin);
			userRepo.delete(existingUser);
			return "user details deleted successfully";
		}
		
		return "user not available with given details";
	}

	public List<UserEntity> findAllUsers(String email) {
		Optional<LoginEntity> loginDetails = loginRepo.findByUserName(email);
		if (loginDetails.isPresent()) {
			LoginEntity loggedInUser = loginDetails.get();
			if (loggedInUser.getRole().equals(Roles.ADMIN.toString())) {
				return userRepo.findAll();
			}else {
				throw new NotAuthenticatedUser(email);
			}
		}else {
			throw new UserNotFoundException(email);
		}
	}

	public UserEntity findUserByEmail(String userEmail) {
		Optional<UserEntity> userOptional = userRepo.findByEmail(userEmail);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}else {
			throw new UserNotFoundException(userEmail);
		}
	}

	public UserEntity findUserByShopName(String userName) {
		String name = "%" + userName.toLowerCase() + "%";
		Optional<UserEntity> userOptional = userRepo.findByUserShopName(name);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}else {
			throw new UserNotFoundException(userName);
		}
	}

	
}
