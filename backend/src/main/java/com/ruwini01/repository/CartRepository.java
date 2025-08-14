package com.ruwini01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruwini01.model.Cart;

public interface CartRepository extends  JpaRepository<Cart, Long>{

    
}
