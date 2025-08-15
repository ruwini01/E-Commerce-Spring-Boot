package com.ruwini01.service;

import com.ruwini01.response.AuthResponse;
import com.ruwini01.response.SignupRequest;

import jakarta.mail.MessagingException;

public interface AuthService {

    void sendLoginOpt(String email) throws MessagingException;

    String createUser(SignupRequest req) throws Exception;
}
