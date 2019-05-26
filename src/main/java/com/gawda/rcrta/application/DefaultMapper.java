package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Slide;
import com.gawda.rcrta.persistence.Store;
import io.vavr.collection.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
@Service
public class DefaultMapper implements Mapper {

    private Store store;

    private WordProcessor processor;

    public DefaultMapper(@Autowired Store store, @Autowired WordProcessor processor) {
        this.store = store;
        this.processor = processor;
    }

    @Override
    public Map<String, Integer> mapWordsToSlideCount(String input) {
        var processedWords = processor.process(input);
        var slidesSorted = processedWords.sorted();
        var slidesFilteredNoDuplicatedWords = slidesSorted
                .map(filterIncorrectSlides())
                .filter(Objects::nonNull);
        return slidesFilteredNoDuplicatedWords.toMap(
                Slide::getWordsCombined,
                slide -> store.getValue(slide.getWordsCombined())
        );
    }

    private Function<Slide, Slide> filterIncorrectSlides() {
        return slide -> {
            var isInStore = store.hasValue(slide.getWordsCombined());
            var hasNoConsumed = slide.hasAllWordsNotConsumed();
            if (isInStore && hasNoConsumed) {
                return slide.markAllConsumed();
            }
            return null;
        };
    }
}
