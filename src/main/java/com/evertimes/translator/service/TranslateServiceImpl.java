package com.evertimes.translator.service;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;
import com.evertimes.translator.service.interfaces.LogService;
import com.evertimes.translator.service.interfaces.TranslateClient;
import com.evertimes.translator.service.interfaces.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TranslateServiceImpl implements TranslateService {
    private final TranslateClient translateClient;
    private final LogService logService;
    private final HttpServletRequest httpRequest;
    private final WordTokenizer tokenizer;
    private final IpService ipService;

    @Autowired
    TranslateServiceImpl(TranslateClient translateClient,
                         LogService logService,
                         HttpServletRequest httpRequest,
                         WordTokenizer tokenizer,
                         IpService ipService) {
        this.translateClient = translateClient;
        this.logService = logService;
        this.httpRequest = httpRequest;
        this.tokenizer = tokenizer;
        this.ipService = ipService;
    }

    @Override
    public OutputData translate(InputData inputData) {
        List<String> words = tokenizer.split(inputData.getText());
        List<String> translatedWords = translateClient.translate(
                words, inputData.getSourceLanguageCode(),
                inputData.getTargetLanguageCode());
        String translatedString = tokenizer.concat(translatedWords);
        OutputData outputData = new OutputData(translatedString);
        logService.log(inputData, outputData, words,
                translatedWords, ipService.getIP(httpRequest));
        return outputData;
    }
}
