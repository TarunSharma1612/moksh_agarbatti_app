package com.website.mokshagarbatti.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.mokshagarbatti.entity.UserEntity;
import com.website.mokshagarbatti.model.AddUserRequestModel;
import com.website.mokshagarbatti.model.UserDetailsRequest;
import com.website.mokshagarbatti.services.UserService;

@RestController
@RequestMapping("/retailer")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-retailer")
	private Map<String, String> addRetailers(@RequestBody AddUserRequestModel request ) throws IOException {
		Map<String, String> result= new HashMap<>();
		
		String status = userService.addUser(request);

		result.put("status", status);
		return result;
	}
	
	@PostMapping("/update-retailers")
	private Map<String, String> updateRetailerDetails(@RequestBody AddUserRequestModel request) throws IOException{
		Map<String, String> result= new HashMap<>();
		String status = userService.updateUserDetails(request);
		result.put("status", status);
		return result;
	}
	
	@DeleteMapping("/delete-retailer")
	private Map<String, String> deleteReatiler(@RequestBody UserDetailsRequest request){
		Map<String, String> result= new HashMap<>();
		String status = userService.deleteUserDetails(request);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/retailers-list")
	private Map<String, List<UserEntity>> findAllRetailers(@RequestBody UserDetailsRequest request){
		Map<String, List<UserEntity>> result= new HashMap<>();
		List<UserEntity> users = userService.findAllUsers(request.getUserEmail());
		result.put("status", users);
		return result;
	}
	
	@PostMapping("/retailers-details")
	private Map<String, UserEntity> findRetailerById(@RequestBody UserDetailsRequest request){
		Map<String, UserEntity> result= new HashMap<>();
		UserEntity users = userService.findUserByEmail(request.getUserEmail());
		result.put("status", users);
		return result;
	}
	
	@PostMapping("/search-retailers")
	private Map<String, UserEntity> searchRetailerByName(@RequestParam String userName){
		Map<String, UserEntity> result= new HashMap<>();
		UserEntity users = userService.findUserByShopName(userName);
		result.put("status", users);
		return result;
	}
	
	
	
}
