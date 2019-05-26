package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Slide;
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

    private StoreProvider storeProvider;

    private WordProcessor processor;

    public DefaultMapper(@Autowired StoreProvider storeProvider, @Autowired WordProcessor processor) {
        this.storeProvider = storeProvider;
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
                slide -> storeProvider.getValue(slide.getWordsCombined())
        );
    }

    private Function<Slide, Slide> filterIncorrectSlides() {
        return slide -> {
            var isInStore = storeProvider.hasValue(slide.getWordsCombined());
            var hasNoConsumed = slide.hasAllWordsNotConsumed();
            if (isInStore && hasNoConsumed) {
                return slide.markAllConsumed();
            }
            return null;
        };
    }
}
