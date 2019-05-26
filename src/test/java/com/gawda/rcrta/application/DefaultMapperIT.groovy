package com.gawda.rcrta.application


import io.vavr.collection.HashMap
import io.vavr.collection.Map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * @author Anna Gawda
 * 26/05/2019
 */
@SpringBootTest
@ActiveProfiles("test")
class DefaultMapperIT extends Specification {

    @Autowired
    private Mapper mapper

    def "should get non-overlapping values from store"(String input, Map<String, Integer> expected) {
        expect:
            def actual = mapper.mapWordsToSlideCount(input)
            actual == expected

        where:
            input             | expected
            "abc def fde cba" | HashMap.of("abc def fde cba", 0)
    }
}
