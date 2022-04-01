package com.evertimes.translator.model.dto;

import java.io.Serializable;

public class OutputData implements Serializable {
    private String translatedString;

    public OutputData() {
    }

    public OutputData(String translatedString) {
        this.translatedString = translatedString;
    }

    public String getTranslatedString() {
        return translatedString;
    }
}
