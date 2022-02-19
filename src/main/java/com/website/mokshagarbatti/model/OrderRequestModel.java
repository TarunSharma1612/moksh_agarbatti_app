package com.website.mokshagarbatti.model;

public class OrderRequestModel {

	private String loggedInString;
	private long orderId;
	public String getLoggedInString() {
		return loggedInString;
	}
	public void setLoggedInString(String loggedInString) {
		this.loggedInString = loggedInString;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
