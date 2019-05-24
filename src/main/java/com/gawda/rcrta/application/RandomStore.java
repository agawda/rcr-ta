package com.gawda.rcrta.application;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Anna Gawda
 * 23/05/2019
 */
@Service
public class RandomStore implements Store {

    private static final int MIN_KEY_VALUE_INCLUSIVE = 1;
    private static final int MAX_KEY_VALUE_EXCLUSIVE = 11;

    @Override
    public boolean hasValue(String key) {
        return false;
    }

    @Override
    public int getValue(String key) {
        return ThreadLocalRandom.current().nextInt(MIN_KEY_VALUE_INCLUSIVE, MAX_KEY_VALUE_EXCLUSIVE);
    }
}
