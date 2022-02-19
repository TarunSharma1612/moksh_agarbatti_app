package com.website.mokshagarbatti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.website.mokshagarbatti.entity.Order;
import com.website.mokshagarbatti.model.AddOrderRequestModel;
import com.website.mokshagarbatti.model.OrderRequestModel;
import com.website.mokshagarbatti.model.OrderStatusResponse;
import com.website.mokshagarbatti.model.UserDetailsRequest;
import com.website.mokshagarbatti.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/add-order")
	private Map<String, String> addOrder(@RequestBody AddOrderRequestModel requestModel){
		Map<String, String> result= new HashMap<>();
		String status = orderService.addOrderDetails(requestModel);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/all-order")
	private Map<String, List<OrderStatusResponse>> getAllOrderForAdmin(@RequestParam String orderStatus){
		Map<String, List<OrderStatusResponse>> result= new HashMap<>();
		List<OrderStatusResponse> status = orderService.allOrderDetails(orderStatus);
		result.put("status", status);
		return result;
	}
	
//	@GetMapping("/cancel-order")
//	private Map<String, String> cancelOrderByUser(@RequestParam List<Long> orderId,@RequestParam String loggedIn){
//		Map<String, String> result= new HashMap<>();
//		String status = orderService.cancelOrderByUser(orderId,loggedIn);
//		result.put("status", status);
//		return result;
//	}
	
//	@GetMapping("/reject-order")
//	private Map<String, String> rejectOrderByAdmin(@RequestParam List<Long> orderId,@RequestParam String loggedIn){
//		Map<String, String> result= new HashMap<>();
//		String status = orderService.rejectOrderByUser(orderId,loggedIn);
//		result.put("status", status);
//		return result;
//	}
	
	@PostMapping("/complete-order")
	private Map<String, String> completeOrder(@RequestBody OrderRequestModel request){
		Map<String, String> result= new HashMap<>();
		String status = orderService.orderCompletedByAdmin(request.getOrderId(),request.getLoggedInString());
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/my-order")
	private Map<String, List<OrderStatusResponse>> getMyOrderHistory(@RequestBody UserDetailsRequest request){
		Map<String, List<OrderStatusResponse>> result= new HashMap<>();
		List<OrderStatusResponse> status = orderService.myOrderDetails(request.getUserEmail());
		result.put("status", status);
		return result;
	}
	
	
	
}
