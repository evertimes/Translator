package com.evertimes.translator.service;

import com.evertimes.translator.configuration.TranslatorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.evertimes.translator.model.dto.YandexInput;
import com.evertimes.translator.model.dto.YandexOutput;
import com.evertimes.translator.service.interfaces.TranslateClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YandexTranslateClient implements TranslateClient {
    private final TranslatorConfiguration configuration;

    @Autowired
    public YandexTranslateClient(TranslatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<String> translate(String[] words,
                                  String sourceLanguageCode,
                                  String targetLanguageCode) {
        var header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, "application/json");
        header.add(HttpHeaders.AUTHORIZATION, configuration.getApiKey());
        YandexInput data = new YandexInput(words, sourceLanguageCode,
                targetLanguageCode);
        HttpEntity<YandexInput> entity = new HttpEntity(data, header);
        RestTemplate request = new RestTemplate();
        YandexOutput output = request.postForObject(configuration.getUri(),
                entity, YandexOutput.class);
        Optional<YandexOutput> outData = Optional.ofNullable(output);
        return Arrays.stream(outData.orElseThrow().getTranslations())
                .map(YandexOutput.Translations::getText)
                .collect(Collectors.toList());
    }
}
