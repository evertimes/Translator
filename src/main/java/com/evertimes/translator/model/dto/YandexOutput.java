package com.evertimes.translator.model.dto;

import java.io.Serializable;
import java.util.Arrays;

public class YandexOutput implements Serializable {
    private Translations[] translations;

    public Translations[] getTranslations() {
        return translations;
    }

    public void setTranslations(Translations[] translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "translations=" + Arrays.toString(translations) +
                '}';
    }

    public static class Translations implements Serializable {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Translations{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
}
