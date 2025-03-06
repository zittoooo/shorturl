package com.laundrygo.shorturl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Util {
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    // 동일한 oriUrl 요청 시 동일한 shortUrl이 응답됨.
    // 해시 기반이므로 충돌 가능성이 매우 낮음.
    public static String encoding(Integer id) {
        StringBuilder sb = new StringBuilder();
        int base = BASE62.length();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % base)));
            id /= base;
        }
        return sb.reverse().toString();
    }

    public static int decode(String param) {
        int sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62.indexOf(param.charAt(i)) * power;
            power *= 62;
        }
        return sum;
    }

}
