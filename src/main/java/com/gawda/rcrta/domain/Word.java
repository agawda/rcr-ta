package com.gawda.rcrta.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@Data
@AllArgsConstructor
public class Word {
    private String value;
    private boolean consumed;

    public Word(String value) {
        this.value = value;
        this.consumed = false;
    }

    public boolean hasSameValue(Word word) {
        return this.value.equals(word.value);
    }
}
