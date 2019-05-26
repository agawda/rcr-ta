package com.gawda.rcrta.domain;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.stream.Collectors;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@Data
@AllArgsConstructor
public class Slide implements Comparable<Slide> {

    private List<Word> words;
    private int weight;

    public String getWordsCombined() {
        return words.map(Word::getValue).collect(Collectors.joining(" "));
    }

    public boolean hasAllWordsNotConsumed() {
        return words.toJavaStream().noneMatch(Word::isConsumed);
    }

    public Slide markAllConsumed() {
        words.forEach(word -> word.setConsumed(true));
        return new Slide(words, weight);
    }

    @Override
    public int compareTo(Slide o) {
        return o.weight - this.weight;
    }
}
