package com.ruwini01.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ruwini01.config.JwtProvider;
import com.ruwini01.domain.USER_ROLE;
import com.ruwini01.model.Cart;
import com.ruwini01.model.User;
import com.ruwini01.model.VerificationCode;
import com.ruwini01.repository.CartRepository;
import com.ruwini01.repository.UserRepository;
import com.ruwini01.repository.VerificationCodeRepository;
import com.ruwini01.response.SignupRequest;
import com.ruwini01.service.AuthService;
import com.ruwini01.service.EmailService;
import com.ruwini01.utils.OptUtil;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    @Override
    public void sendLoginOpt(String email) throws MessagingException {
        String SIGNIN_PREFIX = "signin_";

        if (email.startsWith(SIGNIN_PREFIX)) {
            email = email.substring(SIGNIN_PREFIX.length());
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("User not exist with provided email");
            }
        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist != null) {
            verificationCodeRepository.delete(isExist);
        }

        String otp = OptUtil.generateOpt();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);

        verificationCodeRepository.save(verificationCode);

        String subject = "Login/Signup OTP for Celone";
        String text = "Your login/signup OTP is - "+otp;

        emailService.sendVerificationOptEmail(email, otp, subject, text);
    }

    @Override
    public String createUser(SignupRequest req) {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if (verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new IllegalArgumentException("Wrong OTP");
        }

        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("0776584734");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

}
