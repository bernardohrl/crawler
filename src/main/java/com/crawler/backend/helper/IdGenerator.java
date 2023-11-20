package com.crawler.backend.helper;

import java.util.Random;

public class IdGenerator {
    private static final int leftLimit = 48; // number '0'
    private static final int rightLimit = 122; // letter 'z'
    private static final int targetStringLength = 8;
    static private final Random random = new Random();

    public static String getId() {
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}