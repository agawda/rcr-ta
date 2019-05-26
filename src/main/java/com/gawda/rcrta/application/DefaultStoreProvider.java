package com.gawda.rcrta.application;

import com.gawda.rcrta.persistence.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
@Service
public class DefaultStoreProvider implements StoreProvider {

    private Store store;

    public DefaultStoreProvider(@Autowired Store store) {
        this.store = store;
    }

    @Override
    public boolean hasValue(String key) {
        return store.hasValue(key);
    }

    @Override
    public int getValue(String key) {
        return store.getValue(key);
    }
}
