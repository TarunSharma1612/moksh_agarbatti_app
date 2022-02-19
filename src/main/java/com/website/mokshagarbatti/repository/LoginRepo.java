package com.website.mokshagarbatti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.website.mokshagarbatti.constant.Roles;
import com.website.mokshagarbatti.entity.LoginEntity;

@Repository
public interface LoginRepo extends JpaRepository<LoginEntity, Long> {

	@Query(value = "select * from login_details where user_name = ?1"
			+ " and password = ?2 and role = ?3" , nativeQuery = true)
	Optional<LoginEntity> checkLoginDetails(String username, String password, String string);

	@Query(value = "select * from login_details where user_name = ?1" , nativeQuery = true)
	Optional<LoginEntity> findByUserName(String username);

}