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

import com.website.mokshagarbatti.model.ForgotPassWordModel;
import com.website.mokshagarbatti.model.LoginRequestModel;
import com.website.mokshagarbatti.services.LoginLogoutService;

@RestController
@RequestMapping("/admin")
public class LoginLogoutController {

	private static Logger Log = LogManager.getLogger(LoginLogoutController.class);
	
	@Autowired
	private LoginLogoutService logServices;
	
	@PostMapping(value = "/login",consumes = {"application/json"})
	private Map<String, String> loginControllerMethod(@RequestBody @Valid LoginRequestModel request){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = logServices.checkLoginDetails(request);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("LOGIN | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
	
	@PostMapping(value = "/forgot",consumes = {"application/json"})
	private Map<String, String> forgotPasswordControllerMethod(@RequestBody @Valid ForgotPassWordModel request){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = logServices.forgotPassword(request);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("FORGOT_PASSWORD | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
	
	
}
