package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Result;
import com.gawda.rcrta.domain.Slide;
import com.spotify.futures.CompletableFutures;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author Anna Gawda
 * 27/05/2019
 */
@Service
@Profile("dev")
public class ParallelMapper implements Mapper {

    private WordProcessor processor;
    private ParallelStoreProvider storeProvider;

    public ParallelMapper(@Autowired WordProcessor processor, @Autowired ParallelStoreProvider storeProvider) {
        this.processor = processor;
        this.storeProvider = storeProvider;
    }

    @Override
    public Map<String, Integer> mapWordsToSlideCount(String input) throws ExecutionException, InterruptedException {
        var processedWords = processor.process(input);
        var slidesSorted = processedWords.sorted();
        var slidesFilteredNoDuplicatedWords = slidesSorted
                .map(filterIncorrectSlides())
                .filter(Objects::nonNull);

        var resultFutures = slidesFilteredNoDuplicatedWords.map(slide -> storeProvider.getValue(slide.getWordsCombined())).toJavaList();
        var resultFuture = CompletableFutures.allAsList(resultFutures);
        return resultFuture.get().stream()
                .collect(HashMap.collector(Result::getKey, Result::getValue));
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
