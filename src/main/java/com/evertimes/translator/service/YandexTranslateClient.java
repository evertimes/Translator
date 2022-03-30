package com.evertimes.translator.service;

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
    public List<String> translate(String[] words,
                                  String sourceLanguageCode,
                                  String targetLanguageCode){
        var header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, "application/json");
        header.add("Authorization","Api-Key AQVNx9UB8F04CLIMeCVlOFmX61JODPtchHUGtsPc");
        YandexInput data = new YandexInput(words,sourceLanguageCode,targetLanguageCode);
        HttpEntity<YandexInput> entity = new HttpEntity(data, header);
        RestTemplate request = new RestTemplate();
        Optional<YandexOutput> outData = Optional.ofNullable(request.postForObject("https://translate.api.cloud.yandex.net/translate/v2/translate", entity, YandexOutput.class));
        return Arrays.stream(outData.orElseThrow().getTranslations())
                .map(YandexOutput.Translations::getText).collect(Collectors.toList());
    }
}
