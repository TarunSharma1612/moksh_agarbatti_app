package com.website.mokshagarbatti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.mokshagarbatti.model.AddAdminRequestModel;
import com.website.mokshagarbatti.model.UserDetailsRequest;
import com.website.mokshagarbatti.services.LoginLogoutService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LoginLogoutService logServices;
	
	@PostMapping("/add-admin")
	private Map<String, String> addAdmin(@RequestBody @Valid AddAdminRequestModel request){
		Map<String, String> result= new HashMap<>();
		String status = logServices.addNewAdmin(request);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/update-admin")
	private Map<String, String> updateAdminDetails(@RequestBody @Valid AddAdminRequestModel request){
		Map<String, String> result= new HashMap<>();
		String status = logServices.updateAdminDetails(request);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/profile")
	private Map<String, Object> profileDetails(@RequestBody @Valid UserDetailsRequest request){
		Map<String, Object> result= new HashMap<>();
		Object userObject = logServices.findProfileByEmail(request.getUserEmail());
		result.put("status", userObject);
		return result;
	}
	
}
