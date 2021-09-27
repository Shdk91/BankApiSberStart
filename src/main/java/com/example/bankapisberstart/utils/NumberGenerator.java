package com.example.bankapisberstart.utils;

import org.springframework.stereotype.Component;

@Component
public class NumberGenerator {


    public static String generateCardNumber() {
        StringBuilder builder = new StringBuilder();
        long start = 1000;
        long range = 8999;
        for (int i = 0; i < 4; i++) {
            long generatedValue = getGeneratedValue(start, range);
            builder.append(generatedValue);
        }
        return builder.toString();
    }

    private static long getGeneratedValue(long start, long range) {
        long generatedValue = (long) ((range * Math.random()) + start);
        return generatedValue;
    }

    public Long clientIdGenerator() {
        long start = 1_000_000_000L;
        long range = 8_999_999_999L;
        long id = getGeneratedValue(start, range);
        return id;
    }
}
