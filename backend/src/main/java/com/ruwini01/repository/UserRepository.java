package com.ruwini01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruwini01.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
}
