package com.evertimes.translator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WordTokenizerServiceTest {
    WordTokenizerService tokenizer = new WordTokenizerService();

    @Test
    void split_whenCorrectInput_thenReturnsStringList(){
        String given = "Most amazing string";
        List<String> expected = List.of("Most", "amazing", "string");
        List<String> actual = tokenizer.split(given);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void concat_whenCorrectInput_thenReturnsString(){
        List<String> given = List.of("Hello,","world");
        String expected = "Hello, world";
        String actual = tokenizer.concat(given);
        Assertions.assertEquals(expected,actual);
    }
}
