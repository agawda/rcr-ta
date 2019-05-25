package com.gawda.rcrta.persistence;

/**
 * @author Anna Gawda
 * 22/05/2019
 */
public interface Store {

    boolean hasValue(String key);

    int getValue(String key);
}
