package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Word;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@Service
public class DefaultWordSplitter implements WordSplitter {

    @Override
    public List<Word> splitAndMap(String input) {
        return Pattern.compile(" +").splitAsStream(input).map(Word::new).collect(List.collector());
    }

    @Override
    public String[] justSplit(String input) {
        return input.trim().split(" +");
    }
}
