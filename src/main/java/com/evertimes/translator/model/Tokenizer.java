package com.evertimes.translator.model;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    public List<String> splitToWords(String input){
        return Arrays.asList(input.split(" "));
    }

    public String concatToString(List<String> words){
        StringBuilder builder = new StringBuilder();
        for (String word : words){
            builder.append(word).append(" ");
        }
        return builder.toString().trim();
    }
}
