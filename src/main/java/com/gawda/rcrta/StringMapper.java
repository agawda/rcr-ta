package com.gawda.rcrta;

import com.gawda.rcrta.application.Mapper;
import io.vavr.collection.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author Anna Gawda
 * 26/05/2019
 */
@Component
public class StringMapper {

    private Mapper mapper;

    public StringMapper(@Autowired Mapper mapper) {
        this.mapper = mapper;
    }

    public Map<String, Integer> mapToSlidesCount(String input) throws ExecutionException, InterruptedException {
        return mapper.mapWordsToSlideCount(input);
    }
}
