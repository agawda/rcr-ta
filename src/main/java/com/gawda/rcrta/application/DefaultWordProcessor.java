package com.gawda.rcrta.application;

import com.gawda.rcrta.domain.Slide;
import com.gawda.rcrta.domain.Word;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna Gawda
 * 25/05/2019
 */
@Service
public class DefaultWordProcessor implements WordProcessor {

    private WordSplitter wordSplitter;

    public DefaultWordProcessor(@Autowired WordSplitter wordSplitter) {
        this.wordSplitter = wordSplitter;
    }

    public List<Slide> process(String input) {
        var words = wordSplitter.splitAndMap(input);
        var wordCount = words.length();
        List<Slide> slides = List.empty();
        for (var i = 0; i < wordCount; i++) {
            for (var j = i; j < wordCount; j++) {
                slides = slides.append(createSlide(i, j, words));
            }
        }

        return slides;
    }

    private Slide createSlide(int start, int end, List<Word> words) {
        var weight = end - start;
        List<Word> wordsInRange = List.empty();
        for (int i = start; i <= end; i++) {
            wordsInRange = wordsInRange.append(words.get(i));
        }
        return new Slide(wordsInRange, weight);
    }
}
