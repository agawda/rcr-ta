package com.gawda.rcrta.application;

/**
 * @author Anna Gawda
 * 22/05/2019
 */
public interface Store {

    boolean hasValue(String key);

    int getValue(String key);
}
