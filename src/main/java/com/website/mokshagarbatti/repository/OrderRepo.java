package com.website.mokshagarbatti.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.mokshagarbatti.entity.Order;
import com.website.mokshagarbatti.model.OrderStatusResponse;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	@Query(value = "select * from order_details where status <> 'PENDING_IN_CART'" ,nativeQuery = true)
	List<Order> findAllOrders();

	@Query(value = "select * from order_details where user_id = ?1 and status <> 'PENDING_IN_CART'" ,nativeQuery = true)
	List<Order> findMyOrders(long userId);

	@Query(value = "select * from order_details where order_id = ?1 and status in ('PENDING_WITH_ADMIN')" ,nativeQuery = true)
	Order findByOrderId(Long orderId);

	@Query(value = "SELECT o.order_id as orderId,p.category as category,p.product_name as productName,uc.quantity as quantity, "
			+ "uc.total_price_of_product as total,o.created_at as orderDate,u.retailer_shop_name as orderBy,"
			+ "o.status as status FROM moksh_agarbatti_db.order_details o inner join moksh_agarbatti_db.cart_order co "
			+ "on o.order_id=co.order_id inner join moksh_agarbatti_db.user_cart_details uc "
			+ "on co.cart_id=uc.cart_id inner join moksh_agarbatti_db.product_details p on uc.product_id=p.product_id "
			+ "inner join moksh_agarbatti_db.user_details u on uc.user_id=u.retailer_id "
			+ "where o.status = ?1 order by o.created_at desc",nativeQuery = true)
	List<OrderStatusResponse> findAllOrdersByStatus(String status);
	
	@Query(value = "SELECT o.order_id as orderId,p.category as category,p.product_name as productName,uc.quantity as quantity, "
			+ "uc.total_price_of_product as total,o.created_at as orderDate,u.retailer_shop_name as orderBy,"
			+ "o.status as status FROM moksh_agarbatti_db.order_details o inner join moksh_agarbatti_db.cart_order co "
			+ "on o.order_id=co.order_id inner join moksh_agarbatti_db.user_cart_details uc "
			+ "on co.cart_id=uc.cart_id inner join moksh_agarbatti_db.product_details p on uc.product_id=p.product_id "
			+ "inner join moksh_agarbatti_db.user_details u on uc.user_id=u.retailer_id "
			+ "where u.retailer_id = ?1 order by o.created_at desc",nativeQuery = true)
	List<OrderStatusResponse> findOrderByUserId(Long userId);

}
