package com.laundrygo.shorturl;

import java.util.regex.Pattern;

public class Util {
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE = BASE62.length();

    private static final String URL_REGEX =
            "^(https?|ftp)://[\\w.-]+(?:\\.[\\w\\.-]+)+[/#?]?.*$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    public static String encoding(Integer id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString();
    }

    public static int decode(String param) {
        int sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62.indexOf(param.charAt(i)) * power;
            power *= BASE;
        }
        return sum;
    }

}
