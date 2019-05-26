package com.gawda.rcrta.persistence;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Anna Gawda
 * 23/05/2019
 */
@Service
@Profile("dev")
public class RandomStore implements Store {

    private static final int MIN_KEY_VALUE_INCLUSIVE = 1;
    private static final int MAX_KEY_VALUE_EXCLUSIVE = 11;

    private static final int MIN_BOOLEAN_VALUE_INCLUSIVE = 0;
    private static final int MAX_BOOLEAN_VALUE_EXCLUSIVE = 2;

    @Override
    public boolean hasValue(String key) {
        var value = ThreadLocalRandom.current().nextInt(MIN_BOOLEAN_VALUE_INCLUSIVE, MAX_BOOLEAN_VALUE_EXCLUSIVE);
        return value != 0;
    }

    @Override
    public int getValue(String key) {
        return ThreadLocalRandom.current().nextInt(MIN_KEY_VALUE_INCLUSIVE, MAX_KEY_VALUE_EXCLUSIVE);
    }
}
