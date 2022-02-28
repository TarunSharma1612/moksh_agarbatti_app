package com.website.mokshagarbatti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.mokshagarbatti.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

	@Query(value = "Select * from user_details where lower( username )= ?1 OR lower(retailer_shop_name) = ?1 limit 1",nativeQuery = true)
	Optional<UserEntity> findByEmailAndShop(String email);

	@Query(value = "Select * from user_details where lower(retailer_shop_name) like : ?1",nativeQuery = true)
	Optional<UserEntity> findByUserShopName(String userName);
	
	@Query(value = "Select * from user_details where username = ?1",nativeQuery = true)
	UserEntity getByEmailId(String userEmail);

}
