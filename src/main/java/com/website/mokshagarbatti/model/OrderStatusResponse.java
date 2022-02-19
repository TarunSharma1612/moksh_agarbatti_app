package com.website.mokshagarbatti.model;

import java.util.Date;

public interface OrderStatusResponse {
	
	 long getOrderId();
	 String getCategory();
	 String getProductName();
	 long getQuantity();
	 float getTotal();
	 Date getOrderDate();
	 String getOrderBy();
	 String getStatus();
	
	
}
