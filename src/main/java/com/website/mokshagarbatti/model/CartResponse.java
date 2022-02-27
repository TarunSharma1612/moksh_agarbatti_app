package com.website.mokshagarbatti.model;

public class CartResponse {

	private long cartId;
	private long quantity;
	private float cartTotal;
	private String productName;
	private String category;
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
	public float getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(float cartTotal) {
		this.cartTotal = cartTotal;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
