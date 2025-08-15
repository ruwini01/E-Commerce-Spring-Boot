package com.ruwini01.utils;

import java.util.Random;

public class OptUtil {

    public static String generateOpt() {
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }
}
