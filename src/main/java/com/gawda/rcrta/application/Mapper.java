package com.gawda.rcrta.application;

import io.vavr.collection.Map;

import java.util.concurrent.ExecutionException;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
public interface Mapper {

    Map<String, Integer> mapWordsToSlideCount(String input) throws ExecutionException, InterruptedException;

}
