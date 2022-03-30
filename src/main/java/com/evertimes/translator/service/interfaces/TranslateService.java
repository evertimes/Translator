package com.evertimes.translator.service.interfaces;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;

public interface TranslateService {
    OutputData translate(InputData data);
}
