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
    private final IpService ipService;

    @Autowired
    public DatabaseLogService(RequestRepo requestRepo,
                              IpService ipService) {
        this.requestRepo = requestRepo;
        this.ipService = ipService;
    }

    @Override
    public void log(InputData input,
                    OutputData output,
                    List<String> originalWords,
                    List<String> translatedWords,
                    LocalDateTime dateTime) {
        Request requestEntity = constuctRequest(input, output, dateTime);
        addWordsToRequest(requestEntity, originalWords, translatedWords);
        requestRepo.save(requestEntity);
    }

    private Request constuctRequest(InputData input, OutputData output,
                                    LocalDateTime dateTime) {
        return new Request(input, output, dateTime, ipService.getCurrentIP());
    }

    private void addWordsToRequest(Request requestEntity,
                                   List<String> originalWords,
                                   List<String> translatedWords) {
        for (int i = 0; i < originalWords.size(); i++) {
            requestEntity.addWord(new Words(originalWords.get(i),
                    translatedWords.get(i)));
        }
    }
}
