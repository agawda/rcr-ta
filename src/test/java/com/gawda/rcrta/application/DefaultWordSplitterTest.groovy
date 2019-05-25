package com.gawda.rcrta.application

import com.gawda.rcrta.domain.Word
import io.vavr.collection.List
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@SpringBootTest
class DefaultWordSplitterTest extends Specification {

    @Autowired
    private WordSplitter wordSplitter

    def "given input split into words"(String input, String[] expected) {
        expect:
            def actual = wordSplitter.justSplit(input)
            actual == expected

        where:
            input           | expected
            "a b"           | ["a", "b"]
            " a b "         | ["a", "b"]
            "rede fin ed"   | ["rede", "fin", "ed"]
            "rede   fin ed" | ["rede", "fin", "ed"]
    }

    def "given input split words and convert to wrapped objects"(String input, List<Word> expected) {
        expect:
            def actual = wordSplitter.splitAndMap(input)
            actual == expected

        where:
            input  | expected
            "a b"  | List.of(new Word("a"), new Word("b"))
            "a  b" | List.of(new Word("a"), new Word("b"))
    }
}
