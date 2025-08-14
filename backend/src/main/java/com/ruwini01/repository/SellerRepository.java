package com.ruwini01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruwini01.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{

    Seller findByEmail(String email);
}
