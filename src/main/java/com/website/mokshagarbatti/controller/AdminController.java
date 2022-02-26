package com.website.mokshagarbatti.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static Logger Log = LogManager.getLogger(AdminController.class);

	@Autowired
	private LoginLogoutService logServices;
	
	@PostMapping("/add-admin")
	private Map<String, String> addAdmin(@RequestBody @Valid AddAdminRequestModel request){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = logServices.addNewAdmin(request);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("ADD_ADMIN | "+startDate+" | "+endDate +" | "+status);
		return result;
	}
	
	@PostMapping("/update-admin")
	private Map<String, String> updateAdminDetails(@RequestBody @Valid AddAdminRequestModel request){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = logServices.updateAdminDetails(request);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("UPDATE_ADMIN | "+startDate+" | "+endDate+" | "+status);
		return result;
	}
	
	@PostMapping("/profile")
	private Map<String, Object> profileDetails(@RequestBody @Valid UserDetailsRequest request){
		LocalDate startDate = LocalDate.now();
		Map<String, Object> result= new HashMap<>();
		Object userObject = logServices.findProfileByEmail(request.getUserEmail());
		result.put("status", userObject);
		LocalDate endDate = LocalDate.now();
		Log.info("PROFILE | "+startDate+" | "+endDate);
		return result;
	}
	
}
