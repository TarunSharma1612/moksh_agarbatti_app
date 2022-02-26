package com.website.mokshagarbatti.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.website.mokshagarbatti.model.AddCartRequestModel;
import com.website.mokshagarbatti.model.CartResponse;
import com.website.mokshagarbatti.model.UserDetailsRequest;
import com.website.mokshagarbatti.services.MyCartService;

@RestController
@RequestMapping("/cart")
public class MyCartController {

	private static Logger Log = LogManager.getLogger(MyCartController.class);
	
	@Autowired
	private MyCartService cartService;
	
	@PostMapping("/add-cart")
	private Map<String, String> addProductInCart(@RequestBody AddCartRequestModel requestModel){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = cartService.addCartDetails(requestModel);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("ADD_CART | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
	
	@PostMapping("/update-cart")
	private Map<String, String> updateProductInCart(@RequestBody AddCartRequestModel requestModel){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = cartService.updateCartDetails(requestModel);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("UPDATE_CART | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
	
	@PostMapping("/delete-cart")
	private Map<String, String> deleteProductFromCart(@RequestParam Long cartId){
		LocalDate startDate = LocalDate.now();
		Map<String, String> result= new HashMap<>();
		String status = cartService.deleteCartDetails(cartId);
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("DELETE_CART | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
	
	@PostMapping("/check-cart")
	private Map<String, List<CartResponse>> getCartDetails(@RequestBody UserDetailsRequest request ){
		LocalDate startDate = LocalDate.now();
		Map<String, List<CartResponse>> result= new HashMap<>();
		List<CartResponse> status = cartService.allProductDetails(request.getUserEmail());
		result.put("status", status);
		LocalDate endDate = LocalDate.now();
		Log.info("CHECK_CART | "+startDate+" | "+endDate +" | "+ status);
		return result;
	}
}
