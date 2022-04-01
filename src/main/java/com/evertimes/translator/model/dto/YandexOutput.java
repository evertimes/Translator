package com.evertimes.translator.model.dto;

import java.io.Serializable;
import java.util.List;

public class YandexOutput implements Serializable {
    private List<Translations> translations;

    public YandexOutput() {
    }

    public YandexOutput(List<Translations> translations) {
        this.translations = translations;
    }

    public List<Translations> getTranslations() {
        return translations;
    }

    public static class Translations implements Serializable {
        private String text;

        public Translations() {
        }

        public Translations(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
