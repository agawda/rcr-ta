package com.gawda.rcrta.domain;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@Data
@AllArgsConstructor
public class Slide {

    private List<Word> words;
    private int weight;
}
