package com.kamilglazer.Vendi.config;

import java.security.SecureRandom;
import org.apache.commons.codec.binary.Hex;

public class KeyGenerator {
    private static final int KEY_SIZE = 256;

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_SIZE / 8];
        secureRandom.nextBytes(key);
        return Hex.encodeHexString(key);
    }
}
