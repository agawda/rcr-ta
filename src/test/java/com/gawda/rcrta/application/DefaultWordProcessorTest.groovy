package com.gawda.rcrta.application

import com.gawda.rcrta.domain.Slide
import com.gawda.rcrta.domain.Word
import io.vavr.collection.List
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@SpringBootTest
@ActiveProfiles("test")
class DefaultWordProcessorTest extends Specification {

    @Autowired
    private WordProcessor wordProcessor

    def "should create a list of all possible slides"(String input, List<Slide> expected) {
        expect:
            def actual = wordProcessor.process(input)
            actual == expected
        where:
            input    << ["a b"]
            expected << [
                    List.of(
                            new Slide(List.of(new Word("a")), 0),
                            new Slide(List.of(new Word("a"), new Word("b")), 1),
                            new Slide(List.of(new Word("b")), 0))
            ]
    }
}
