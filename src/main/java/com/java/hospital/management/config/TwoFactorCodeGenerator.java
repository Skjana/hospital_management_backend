package com.java.hospital.management.config;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class TwoFactorCodeGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int CODE_LENGTH = 4;

    public static String generateCode() {
        int code = secureRandom.nextInt((int) Math.pow(10, CODE_LENGTH));
        return String.format("%0" + CODE_LENGTH + "d", code);
    }

}
