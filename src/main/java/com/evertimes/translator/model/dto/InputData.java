package com.evertimes.translator.model.dto;

import java.io.Serializable;

public class InputData implements Serializable {
    private String text;
    private String sourceLanguageCode;
    private String targetLanguageCode;


    public InputData(String text, String sourceLanguageCode,
                     String targetLanguageCode) {
        this.text = text;
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
    }

    public String getText() {
        return text;
    }

    public String getSourceLanguageCode() {
        return sourceLanguageCode;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }
}
