package com.policene.url_shortener.utils;

import java.security.SecureRandom;

public class SlugGenerator {

    private static final String ALPHABET =
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789";

    private static final int SLUG_LENGTH = 7;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateSlug () {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < SLUG_LENGTH; i++) {
            int charChoosen = RANDOM.nextInt(ALPHABET.length());
            result.append(ALPHABET.charAt(charChoosen));
        }

        return result.toString();

    }

}
