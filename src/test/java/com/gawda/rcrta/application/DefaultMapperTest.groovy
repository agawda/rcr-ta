package com.gawda.rcrta.application

import com.gawda.rcrta.domain.Slide
import com.gawda.rcrta.domain.Word
import com.gawda.rcrta.persistence.Store
import io.vavr.collection.List
import spock.lang.Specification

/**
 * @author Anna Gawda
 * 26/05/2019
 */
class DefaultMapperTest extends Specification {

    def storeMock = Mock(Store)
    def processorMock = Mock(WordProcessor)

    private Mapper mapper

    def setup() {
        mapper = new DefaultMapper(storeMock, processorMock)
    }

    def "should map non-overlapping values with proper counts"() {
        setup:
            def input = "abc def abc"

            storeMock.hasValue("abc def") >> true // the biggest slide
            storeMock.hasValue("abc") >> true // the only slide that's left

            def abc1 = new Word("abc")
            def def1 = new Word("def")
            def abc2 = new Word("abc")

            processorMock.process(_) >> List.of(
                    new Slide(List.of(abc1, def1, abc2), 2),
                    new Slide(List.of(abc1, def1), 1),
                    new Slide(List.of(def1, abc2), 1),
                    new Slide(List.of(abc1), 0),
                    new Slide(List.of(def1), 0),
                    new Slide(List.of(abc2), 0)
            )

        when:
            def actual = mapper.mapWordsToSlideCount(input)

        then:
            actual.size() == 2
    }
}
