package com.gawda.rcrta.persistence;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
@Service
@Profile("test")
public class FixedStore implements Store {

    @Override
    public boolean hasValue(String key) {
        return true;
    }

    @Override
    public int getValue(String key) {
        return 0;
    }
}
