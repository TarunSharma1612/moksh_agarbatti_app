package com.website.mokshagarbatti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.mokshagarbatti.constant.Category;
import com.website.mokshagarbatti.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

	@Query(value = "select * from product_details where category = ?1 and product_name = ?2" ,nativeQuery = true)
	Optional<ProductEntity> findByCategoryAndName(String category, String productName);

	@Query(value = "select * from product_details where category = ?1" ,nativeQuery = true)
	List<ProductEntity> findAllByCategory(String category);

	@Query(value = "select * from product_details where lower(product_name) like ?1" ,nativeQuery = true)
	List<ProductEntity> findByName(String name);

	@Query(value = "select * from product_details order by created_at Limit 3" ,nativeQuery = true)
	List<ProductEntity> findNewlyAddedProduct();


}
