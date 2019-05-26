package com.gawda.rcrta.application;

import io.vavr.collection.Map;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
public interface Mapper {

    Map<String, Integer> mapWordsToSlideCount(String input);

}
