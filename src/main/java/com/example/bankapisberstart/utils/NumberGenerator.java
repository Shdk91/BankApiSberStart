package com.example.bankapisberstart.utils;

import org.springframework.stereotype.Component;

@Component
public class NumberGenerator {


    public Long clientIdGenerator() {
        long start = 1_000_000_000L;
        long range = 8_999_999_999L;
        long id = (long) ((range * Math.random()) + start);
        return id;
    }
}
