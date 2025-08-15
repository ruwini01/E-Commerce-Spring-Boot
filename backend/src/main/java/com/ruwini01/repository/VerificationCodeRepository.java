package com.ruwini01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruwini01.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer>{

    VerificationCode findByEmail(String email);
}
