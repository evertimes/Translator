package com.evertimes.translator.service;

import com.evertimes.translator.model.Tokenizer;
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

    @Autowired
    TranslateServiceImpl(TranslateClient translateClient,
                         LogService logService,
                         HttpServletRequest httpRequest) {
        this.translateClient = translateClient;
        this.logService = logService;
        this.httpRequest = httpRequest;
    }

    @Override
    public OutputData translate(InputData inputData) {
        Tokenizer tokenizer = new Tokenizer();
        List<String> words = tokenizer.splitToWords(inputData.getText());
        List<String> translatedWords = translateClient.translate(
                words.toArray(new String[0]),
                inputData.getSourceLanguageCode(),
                inputData.getTargetLanguageCode());
        String translatedString = tokenizer.concatToString(translatedWords);
        OutputData outputData = new OutputData(translatedString);
        logService.log(inputData, outputData, words,
                translatedWords, getIP(httpRequest));
        return outputData;
    }

    private String getIP(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

}
