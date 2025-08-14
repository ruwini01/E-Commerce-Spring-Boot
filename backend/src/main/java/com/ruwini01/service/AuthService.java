package com.ruwini01.service;

import com.ruwini01.response.SignupRequest;

public interface AuthService {

    String createUser(SignupRequest req);
}
