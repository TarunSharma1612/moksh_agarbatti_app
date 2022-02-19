package com.website.mokshagarbatti.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.mokshagarbatti.constant.OrderStatus;
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
		UserEntity user = userRepo.getByEmailId(userEmail);
		List<MyCartEntity> carts = cartRepo.findAllByUserId(user.getUserId());
		for (MyCartEntity myCartEntity : carts) {
			CartResponse cartModel = new CartResponse();
			Optional<ProductEntity> product = productRepo.findById(myCartEntity.getProductId());
			cartModel.setCartId(myCartEntity.getCartId());
			cartModel.setProductName(product.get().getProductName());
			cartModel.setImgPath(product.get().getProductImagePath());
			cartModel.setQuantity(myCartEntity.getQuantity());
			cartModel.setCartTotal(myCartEntity.getTotalPriceOfProduct());
			
			cartResponses.add(cartModel);
		}
		
		return cartResponses;
	}

	public String addCartDetails(AddCartRequestModel requestModel) {
		Optional<MyCartEntity> existingCart = cartRepo.findCartByDetails(requestModel.getProductId(),
				requestModel.getUserId());
		Optional<ProductEntity> productDetails = productRepo.findById(requestModel.getProductId());
		if (existingCart.isPresent() || productDetails.isEmpty()) {
			return "cart already exists with given details";
		}
		ProductEntity existingProductDetails = productDetails.get();
		Optional<UserEntity> user = userRepo.findByEmail(requestModel.getLoggedIn());
		MyCartEntity newcart = new MyCartEntity();
		Date date = new Date();
		newcart.setProductId(requestModel.getProductId());
		newcart.setQuantity(requestModel.getQuantity());
		newcart.setTotalPriceOfProduct(requestModel.getQuantity() * 
				existingProductDetails.getPrice() * (1-(existingProductDetails.getDiscount()/100)));
		newcart.setStatus(OrderStatus.PENDING_IN_CART);
		newcart.setUserId(user.get().getUserId());
		newcart.setCreatedAt(date);
		newcart.setCreatedBy(requestModel.getLoggedIn());
		cartRepo.save(newcart);
		return "cart details added successfully";
	}

	public String updateCartDetails(AddCartRequestModel requestModel) {
		Optional<MyCartEntity> existingCart = cartRepo.findById(requestModel.getCartId());
		Optional<ProductEntity> productDetails = productRepo.findById(requestModel.getProductId());
		if (existingCart.isPresent() || productDetails.isEmpty()) {
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
