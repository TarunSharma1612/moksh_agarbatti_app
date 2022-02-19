package com.website.mokshagarbatti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.mokshagarbatti.entity.CartOrder;

public interface OrderCartRepo extends JpaRepository<CartOrder, Long> {

}
