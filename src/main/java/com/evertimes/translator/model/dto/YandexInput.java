package com.evertimes.translator.model.dto;

import java.io.Serializable;
import java.util.List;

public class YandexInput implements Serializable {
    private List<String> texts;
    private String sourceLanguageCode;
    private String targetLanguageCode;

    public YandexInput(List<String> texts,
                       String sourceLanguageCode,
                       String targetLanguageCode) {
        this.texts = texts;
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
    }

    public List<String> getTexts() {
        return texts;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public String getSourceLanguageCode() {
        return sourceLanguageCode;
    }
}
