package com.website.mokshagarbatti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.mokshagarbatti.model.LoginRequestModel;
import com.website.mokshagarbatti.services.LoginLogoutService;

@RestController
@RequestMapping("/admin")
public class LoginLogoutController {

	@Autowired
	private LoginLogoutService logServices;
	
	@PostMapping(value = "/login",consumes = {"application/json"})
	private Map<String, String> loginControllerMethod(@RequestBody @Valid LoginRequestModel request){
		Map<String, String> result= new HashMap<>();
		String status = logServices.checkLoginDetails(request);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/logout")
	private Map<String, String> logoutControllerMethod(){
		Map<String, String> result= new HashMap<>();
		return result;
	}
}
