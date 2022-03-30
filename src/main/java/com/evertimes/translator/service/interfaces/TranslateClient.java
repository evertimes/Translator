package com.evertimes.translator.service.interfaces;

import java.util.List;

public interface TranslateClient {
    List<String> translate(String[] words,
                           String sourceLanguageCode,
                           String targetLanguageCode);
}
