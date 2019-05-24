package com.gawda.rcrta.application


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author Anna Gawda
 * 23/05/2019
 */
@SpringBootTest
class RandomStoreTest extends Specification {

    @Autowired
    private RandomStore store

    def "retrieve count from store for given key"(String key) {
        expect:
            def actualValue = store.getValue("abc")
            actualValue > 0 && actualValue <= 10

        where:
            key   | _
            "abc" | _
            null  | _
            ""    | _
    }
}
