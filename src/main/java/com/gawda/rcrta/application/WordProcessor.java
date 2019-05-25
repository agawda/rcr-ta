package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Slide;
import io.vavr.collection.List;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
public interface WordProcessor {

    List<Slide> process(String input);
}
