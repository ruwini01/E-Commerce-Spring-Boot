package com.ruwini01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruwini01.domain.USER_ROLE;
import com.ruwini01.model.User;
import com.ruwini01.repository.UserRepository;
import com.ruwini01.response.AuthResponse;
import com.ruwini01.response.SignupRequest;
import com.ruwini01.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req){
        
        String jwt=authService.createUser(req);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Registration Successfull!");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);


        return ResponseEntity.ok(res);
    }
}
