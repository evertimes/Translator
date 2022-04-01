package com.evertimes.translator.service;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;
import com.evertimes.translator.model.entity.Request;
import com.evertimes.translator.model.entity.RequestRepo;
import com.evertimes.translator.model.entity.Words;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class DatabaseLogServiceTest {
    DatabaseLogService logService;
    IpService ipService;
    RequestRepo requestRepo;

    @BeforeEach
    void init() {
        requestRepo = mock(RequestRepo.class);
        ipService = mock(IpService.class);
        logService = new DatabaseLogService(requestRepo, ipService);
    }

    @Test
    void log_whenCorrectInput_thenConstructCorrectEntity() {
        InputData inputData = new InputData("Hello world", "en", "ru");
        OutputData outputData = new OutputData("Привет мир");
        List<String> originalWords = List.of("Hello", "world");
        List<String> translatedWords = List.of("Привет", "мир");
        String ip = "127.0.0.1";
        LocalDateTime datetime = LocalDateTime.now();
        Request requestEntity = new Request(inputData, outputData,datetime,ip);
        requestEntity.addWord(new Words(originalWords.get(0),
                translatedWords.get(0)));
        requestEntity.addWord(new Words(originalWords.get(1),
                translatedWords.get(1)));

        doReturn(ip).when(ipService).getCurrentIP();
        logService.log(inputData,outputData,originalWords,
                translatedWords,datetime);
        verify(requestRepo).save(requestEntity);
    }
}
