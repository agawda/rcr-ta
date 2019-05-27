package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @author Anna Gawda
 * 27/05/2019
 */
@Service
public class ParallelStoreProvider {

    private StoreProvider storeProvider;
    private ExecutorService executorService;

    public ParallelStoreProvider(@Autowired StoreProvider storeProvider, @Autowired ExecutorService executorService) {
        this.storeProvider = storeProvider;
        this.executorService = executorService;
    }

    public boolean hasValue(String key) {
        return storeProvider.hasValue(key);
    }

    public CompletableFuture<Result> getValue(String key) {
        return CompletableFuture.supplyAsync(() -> new Result(key, storeProvider.getValue(key)), executorService);
    }
}
