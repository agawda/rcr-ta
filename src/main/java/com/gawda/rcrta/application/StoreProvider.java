package com.gawda.rcrta.application;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
public interface StoreProvider {

    boolean hasValue(String key);

    int getValue(String key);
}
