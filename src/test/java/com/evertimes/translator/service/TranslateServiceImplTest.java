package com.evertimes.translator.service;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.YandexOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class TranslateServiceImplTest {
    YandexTranslateClient yandexTranslateClientMock;
    DatabaseLogService databaseLogServiceMock;
    TranslateServiceImpl translateService;

    @BeforeEach
    void init() {
        yandexTranslateClientMock = mock(YandexTranslateClient.class);
        databaseLogServiceMock = mock(DatabaseLogService.class);
        translateService = new TranslateServiceImpl(yandexTranslateClientMock,
                databaseLogServiceMock, new WordTokenizerService());
    }

    @Test
    void translate_whenCorrectInput_thenCorrectTranslatedString() {
        InputData given = new InputData("Hello, world", "en", "ru");

        YandexOutput yandexOutput = new YandexOutput(
                List.of(new YandexOutput.Translations("Привет,"),
                        new YandexOutput.Translations("мир")));
        doReturn(yandexOutput).when(yandexTranslateClientMock)
                .translate(any());

        String expected = "Привет, мир";
        String actual = translateService.translate(given)
                .getTranslatedString();
        Assertions.assertEquals(expected, actual);
    }

}
