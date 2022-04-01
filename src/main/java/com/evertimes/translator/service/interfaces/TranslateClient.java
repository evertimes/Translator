package com.evertimes.translator.service.interfaces;

import com.evertimes.translator.model.dto.YandexInput;
import com.evertimes.translator.model.dto.YandexOutput;

public interface TranslateClient {
    YandexOutput translate(YandexInput input);
}
