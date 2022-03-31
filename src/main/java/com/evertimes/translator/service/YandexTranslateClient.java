package com.evertimes.translator.service;

import com.evertimes.translator.configuration.TranslatorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.evertimes.translator.model.dto.YandexInput;
import com.evertimes.translator.model.dto.YandexOutput;
import com.evertimes.translator.service.interfaces.TranslateClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class YandexTranslateClient implements TranslateClient {
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private final TranslatorConfiguration configuration;

    @Autowired
    public YandexTranslateClient(TranslatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<String> translate(List<String> words,
                                  String sourceLanguageCode,
                                  String targetLanguageCode) {
        RestTemplate request = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        header.add(HttpHeaders.AUTHORIZATION, configuration.getApiKey());
        YandexInput data = new YandexInput(words, sourceLanguageCode,
                targetLanguageCode);
        HttpEntity<YandexInput> entity = new HttpEntity<>(data, header);
        ResponseEntity<YandexOutput> output = request
                .postForEntity(configuration.getUri(),
                        entity, YandexOutput.class);
        try {
            return Arrays.stream(Objects
                            .requireNonNull(output.getBody())
                            .getTranslations())
                    .map(YandexOutput.Translations::getText)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new HttpClientErrorException(output.getStatusCode());
        }
    }
}
