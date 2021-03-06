package com.website.mokshagarbatti.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.mokshagarbatti.constant.OrderStatus;
import com.website.mokshagarbatti.controller.MyCartController;
import com.website.mokshagarbatti.entity.MyCartEntity;
import com.website.mokshagarbatti.entity.ProductEntity;
import com.website.mokshagarbatti.entity.UserEntity;
import com.website.mokshagarbatti.model.AddCartRequestModel;
import com.website.mokshagarbatti.model.CartResponse;
import com.website.mokshagarbatti.repository.MyCartRepo;
import com.website.mokshagarbatti.repository.ProductRepo;
import com.website.mokshagarbatti.repository.UserRepo;

@Service
public class MyCartService {
	
	private static Logger Log = LogManager.getLogger(MyCartService.class);

	@Autowired
	private MyCartRepo cartRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	public String deleteCartDetails(Long cartId) {
		Optional<MyCartEntity> existingProductOptional = cartRepo.findById(cartId);
		if (existingProductOptional.isPresent()) {
			cartRepo.deleteById(cartId);
			return "cart item deleted successfully";
		}
		return "cart item not exist";
	}

	public List<CartResponse> allProductDetails(String userEmail) {
		List<CartResponse> cartResponses = new ArrayList<>();
		UserEntity user = userRepo.getByEmailId(userEmail.toLowerCase());
		List<MyCartEntity> carts = cartRepo.findAllByUserId(user.getUserId());
		for (MyCartEntity myCartEntity : carts) {
			CartResponse cartModel = new CartResponse();
			Optional<ProductEntity> product = productRepo.findById(myCartEntity.getProductId());
			cartModel.setCartId(myCartEntity.getCartId());
			cartModel.setCategory(product.get().getCategory());
			cartModel.setProductName(product.get().getProductName());
			cartModel.setQuantity(myCartEntity.getQuantity());
			cartModel.setCartTotal(myCartEntity.getTotalPriceOfProduct());
			
			cartResponses.add(cartModel);
		}
		
		return cartResponses;
	}

	public String addCartDetails(AddCartRequestModel requestModel) {
		Log.info("ADD_CART_SERVICES");
		Log.info("PRODUCT ID "+requestModel.getProductId());
		Optional<MyCartEntity> existingCart = cartRepo.findCartByDetails(requestModel.getProductId(),
				requestModel.getUserId());
		Log.info("existingCart ");
		Optional<ProductEntity> productDetails = productRepo.findById(requestModel.getProductId());
		Log.info("productDetails ");
		if (existingCart.isPresent() || productDetails == null) {
			return "cart already exists with given details";
		}
		ProductEntity existingProductDetails = productDetails.get();
		Log.info("user mail "+requestModel.getLoggedIn());
		Optional<UserEntity> user = userRepo.findByEmail(requestModel.getLoggedIn().toLowerCase());
		Log.info("user present"+user.isPresent());
		MyCartEntity newcart = new MyCartEntity();
		Date date = new Date();
		newcart.setProductId(requestModel.getProductId());
		newcart.setQuantity(requestModel.getQuantity());
		newcart.setTotalPriceOfProduct(requestModel.getQuantity() * 
				existingProductDetails.getPrice() * (1-(existingProductDetails.getDiscount()/100)));
		newcart.setStatus(OrderStatus.PENDING_IN_CART);
		Log.info("set user id");
		newcart.setUserId(user.get().getUserId());
		newcart.setCreatedAt(date);
		newcart.setCreatedBy(requestModel.getLoggedIn());
		Log.info("save cart");
		cartRepo.save(newcart);
		return "cart details added successfully";
	}

	public String updateCartDetails(AddCartRequestModel requestModel) {
		Optional<MyCartEntity> existingCart = cartRepo.findById(requestModel.getCartId());
		Optional<ProductEntity> productDetails = productRepo.findById(requestModel.getProductId());
		if (existingCart.isPresent() || productDetails == null) {
			ProductEntity existingProductDetails = productDetails.get();
			MyCartEntity newcart = existingCart.get();
			Date date = new Date();
			newcart.setQuantity(requestModel.getQuantity());
			newcart.setTotalPriceOfProduct(requestModel.getQuantity() * 
					existingProductDetails.getPrice() * (1-(existingProductDetails.getDiscount()/100)));
			newcart.setUserId(requestModel.getUserId());
			newcart.setModifiedAt(date);
			newcart.setModifiedBy(requestModel.getLoggedIn());
			cartRepo.save(newcart);
			return "cart details updated successfully";
		}
		return "cart details not exists with given details";
	}

}
