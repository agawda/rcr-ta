package com.gawda.rcrta.application;

import com.gawda.rcrta.persistence.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Anna Gawda
 * 27/05/2019
 */
@Service
public class CachingStoreProvider implements StoreProvider {

    private Store store;

    public CachingStoreProvider(@Autowired Store store) {
        this.store = store;
    }

    @Override
    public boolean hasValue(String key) {
        return store.hasValue(key);
    }

    @Override
    @Cacheable("store-values")
    public int getValue(String key) {
        return store.getValue(key);
    }
}
