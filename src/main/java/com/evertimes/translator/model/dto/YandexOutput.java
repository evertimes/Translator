package com.evertimes.translator.model.dto;

import java.io.Serializable;

public class YandexOutput implements Serializable {
    private Translations[] translations;

    public Translations[] getTranslations() {
        return translations;
    }

    public static class Translations implements Serializable {
        private String text;

        public String getText() {
            return text;
        }
    }
}
