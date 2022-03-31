package com.evertimes.translator.service;

import com.evertimes.translator.model.entity.Request;
import com.evertimes.translator.model.entity.RequestRepo;
import com.evertimes.translator.model.entity.Words;
import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;
import com.evertimes.translator.service.interfaces.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DatabaseLogService implements LogService {
    private final RequestRepo requestRepo;

    @Autowired
    public DatabaseLogService(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public void log(InputData input,
                    OutputData output,
                    List<String> originalWords,
                    List<String> translatedWords,
                    String ip) {
        Request requestEntity = new Request(input.getText(),
                output.getTranslatedString(),
                LocalDateTime.now(),
                input.getSourceLanguageCode(),
                input.getTargetLanguageCode(),
                ip);
        for (int i = 0; i < originalWords.size(); i++) {
            requestEntity.addWord(new Words(originalWords.get(i),
                    translatedWords.get(i)));
        }
        requestRepo.save(requestEntity);
    }
}
