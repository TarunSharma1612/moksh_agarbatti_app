package com.website.mokshagarbatti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.mokshagarbatti.entity.MyCartEntity;
import com.website.mokshagarbatti.entity.Order;

@Repository
public interface MyCartRepo extends JpaRepository<MyCartEntity, Long> {

	@Query(value = "select * from user_cart_details where user_id = ?1 and status = 'PENDING_IN_CART'" ,nativeQuery = true)
	List<MyCartEntity> findAllByUserId(Long userId);

	@Query(value = "select * from user_cart_details where product_id = ?1 and user_id = ?2 and status = 'PENDING_IN_CART'" ,nativeQuery = true)
	Optional<MyCartEntity> findCartByDetails(long productId, long userId);

	@Query(value = "select * from user_cart_details where cart_id in ?1 and status = 'PENDING_WITH_ADMIN'" ,nativeQuery = true)
	List<Order> findByCartId(List<String> cartId);

	@Query(value = "select * from user_cart_details where cart_id in ?1 and status = 'PENDING_IN_CART'" ,nativeQuery = true)
	List<MyCartEntity> findByIdAndStatus(List<String> cartId);

}
