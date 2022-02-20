package com.website.mokshagarbatti.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.mokshagarbatti.constant.OrderStatus;
import com.website.mokshagarbatti.entity.CartOrder;
import com.website.mokshagarbatti.entity.MyCartEntity;
import com.website.mokshagarbatti.entity.Order;
import com.website.mokshagarbatti.entity.UserEntity;
import com.website.mokshagarbatti.exceptions.UserNotFoundException;
import com.website.mokshagarbatti.model.AddOrderRequestModel;
import com.website.mokshagarbatti.model.OrderStatusResponse;
import com.website.mokshagarbatti.repository.MyCartRepo;
import com.website.mokshagarbatti.repository.OrderCartRepo;
import com.website.mokshagarbatti.repository.OrderRepo;
import com.website.mokshagarbatti.repository.ProductRepo;
import com.website.mokshagarbatti.repository.UserRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private MyCartRepo cartRepo;
	
	@Autowired
	private OrderCartRepo orderCartRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public List<OrderStatusResponse> allOrderDetails(String status) {
		
		return orderRepo.findAllOrdersByStatus(status);
	}

	public List<OrderStatusResponse> myOrderDetails(String userEmail) {
		Optional<UserEntity> userOptional = userRepo.findByEmail(userEmail);
		if (userOptional.isPresent()) {
			return orderRepo.findOrderByUserId(userOptional.get().getUserId());
		}else {
			throw new UserNotFoundException(userEmail);
		}
		
	}

	public String addOrderDetails(AddOrderRequestModel requestModel) {
		List<String> cartIdString = new ArrayList<String>(Arrays.asList(requestModel.getCartId().split(",")));
		List<Long> cartIds = new ArrayList<>();
		for (String cartId : cartIdString) {
			cartIds.add(Long.parseLong(cartId));
		}
		List<Order> existingOrder = cartRepo.findByCartId(cartIds);
		
		if (!existingOrder.isEmpty()) {
			return "order already exists with given details";
		}
		List<MyCartEntity> cartList = cartRepo.findByIdAndStatus(cartIds);
		Order newOrder = new Order();
		Date date = new Date();
		newOrder.setStatus(OrderStatus.PENDING_WITH_ADMIN);
		
		int totalAmount = 0;
		for (MyCartEntity myCartEntity : cartList) {
			 totalAmount += myCartEntity.getTotalPriceOfProduct();
		}
		
		
		newOrder.setTotalAmount(totalAmount);
		newOrder.setCreatedAt(date);
		newOrder.setCreatedBy(requestModel.getLoggedIn());
		
		Order order = orderRepo.save(newOrder);
		
		
		for (MyCartEntity myCartEntity : cartList) {
			CartOrder newCartOrder = new CartOrder();
			System.out.println(myCartEntity.getCartId());
			newCartOrder.setCartId(myCartEntity.getCartId());
			newCartOrder.setOrderId(order.getOrderId());
			orderCartRepo.save(newCartOrder);
			myCartEntity.setStatus(OrderStatus.PENDING_WITH_ADMIN);
			myCartEntity.setModifiedAt(date);
			myCartEntity.setModifiedBy(requestModel.getLoggedIn());
			cartRepo.save(myCartEntity);
		}
		
		return "orders details added successfully";
	}

//	public String cancelOrderByUser(List<Long> orderId, String loggedIn) {
//		List<Order> orders = orderRepo.findByOrderId(orderId);
//		for(Order entity : orders) {
//			Order existing = entity;
//			Date date = new Date();
//			existing.setStatus(OrderStatus.CANCELLED_BY_USER);
//			existing.setModifiedAt(date);
//			existing.setModifiedBy(loggedIn);
//			orderRepo.save(existing);
//			
//		}
//		return "order cancelled successfully";
//	}

//	public String rejectOrderByUser(List<Long> orderId, String loggedIn) {
//		List<Order> orders = orderRepo.findByOrderId(orderId);
//		for(Order entity : orders) {
//			Order existing = entity;
//			Date date = new Date();
//			existing.setStatus(OrderStatus.CANCELLED_BY_ADMIN);
//			existing.setModifiedAt(date);
//			existing.setModifiedBy(loggedIn);
//			orderRepo.save(existing);
//			
//		}
//		return "order cancelled successfully";
//	}

	public String orderCompletedByAdmin(long orderId, String loggedIn) {
			Order order = orderRepo.findByOrderId(orderId);
			Date date = new Date();
			order.setStatus(OrderStatus.COMPLETED);
			order.setModifiedAt(date);
			order.setModifiedBy(loggedIn);
			orderRepo.save(order);
			
		return "order completed successfully";
	}

}
