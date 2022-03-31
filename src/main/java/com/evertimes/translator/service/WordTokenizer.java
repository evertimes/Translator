package com.evertimes.translator.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WordTokenizer {
    public List<String> split(String input){
        return Arrays.asList(input.split(" "));
    }

    public String concat(List<String> words){
        StringBuilder builder = new StringBuilder();
        for (String word : words){
            builder.append(word).append(" ");
        }
        return builder.toString().trim();
    }
}
