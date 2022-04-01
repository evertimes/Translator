package com.evertimes.translator.service;

import com.evertimes.translator.configuration.TranslatorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.evertimes.translator.model.dto.YandexInput;
import com.evertimes.translator.model.dto.YandexOutput;
import com.evertimes.translator.service.interfaces.TranslateClient;

@Service
public class YandexTranslateClient implements TranslateClient {
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private final TranslatorConfiguration configuration;

    @Autowired
    public YandexTranslateClient(TranslatorConfiguration configuration) {
        this.configuration = configuration;
    }

    public YandexOutput translate(YandexInput input)
            throws HttpClientErrorException {
        RestTemplate request = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        header.add(HttpHeaders.AUTHORIZATION, configuration.getApiKey());
        HttpEntity<YandexInput> entity = new HttpEntity<>(input, header);
        return request.postForObject(configuration.getUri(),
                entity, YandexOutput.class);
    }
}
