package com.website.mokshagarbatti.model;

import com.website.mokshagarbatti.constant.OrderStatus;

public class AddCartRequestModel {

	private long cartId;
	
	private long quantity;
	
	private long productId;
	
	private float totalPriceOfProduct;
	
	private OrderStatus status;
	
	private String loggedIn;
	
	private long userId;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public float getTotalPriceOfProduct() {
		return totalPriceOfProduct;
	}

	public void setTotalPriceOfProduct(float totalPriceOfProduct) {
		this.totalPriceOfProduct = totalPriceOfProduct;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
