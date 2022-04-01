package com.evertimes.translator.service;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;
import com.evertimes.translator.model.dto.YandexInput;
import com.evertimes.translator.model.dto.YandexOutput;
import com.evertimes.translator.service.interfaces.LogService;
import com.evertimes.translator.service.interfaces.TranslateClient;
import com.evertimes.translator.service.interfaces.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TranslateServiceImpl implements TranslateService {
    private final TranslateClient translateClient;
    private final LogService logService;
    private final WordTokenizerService tokenizer;

    @Autowired
    TranslateServiceImpl(TranslateClient translateClient,
                         LogService logService,
                         WordTokenizerService tokenizer) {
        this.translateClient = translateClient;
        this.logService = logService;
        this.tokenizer = tokenizer;
    }

    @Override
    public OutputData translate(InputData inputData)
            throws HttpClientErrorException {
        List<String> words = tokenizer.split(inputData.getText());
        YandexInput data = new YandexInput(words, inputData.getSourceLanguageCode(),
                inputData.getTargetLanguageCode());
        YandexOutput response = translateClient
                .translate(data);
        List<String> translatedWords = Objects
                .requireNonNull(response)
                .getTranslations().stream()
                .map(YandexOutput.Translations::getText)
                .collect(Collectors.toList());
        String translatedString = tokenizer.concat(translatedWords);
        OutputData outputData = new OutputData(translatedString);
        logService.log(inputData, outputData, words,
                translatedWords, LocalDateTime.now());
        return outputData;

    }
}
