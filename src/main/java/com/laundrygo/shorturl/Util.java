package com.laundrygo.shorturl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Util {
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    // 동일한 oriUrl 요청 시 동일한 shortUrl이 응답됨.
    // 해시 기반이므로 충돌 가능성이 매우 낮음.
    public static String encoding(String originUrl) {
        try {
            // 1. SHA-256 해시 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(originUrl.getBytes(StandardCharsets.UTF_8));

            // 2. 해시 값을 숫자로 변환 후 Base62 인코딩
            BigInteger hashInt = new BigInteger(1, hash);
            String base62Encoded = encodeBase62(hashInt);

            // 3. 8자리까지만 사용
            return base62Encoded.substring(0, 8);
        } catch (Exception e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }

    private static String encodeBase62(BigInteger value) {
        StringBuilder sb = new StringBuilder();
        BigInteger base = BigInteger.valueOf(BASE62.length());
        while (value.compareTo(BigInteger.ZERO) > 0) {
            sb.append(BASE62.charAt(value.mod(base).intValue()));
            value = value.divide(base);
        }
        return sb.reverse().toString();
    }
}
