package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Word;
import io.vavr.collection.List;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
public interface WordSplitter {

    List<Word> splitAndMap(String input);

    String[] justSplit(String input);
}
