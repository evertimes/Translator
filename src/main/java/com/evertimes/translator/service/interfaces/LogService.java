package com.evertimes.translator.service.interfaces;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;

import java.util.List;

public interface LogService {
    void log(InputData input,
             OutputData output,
             List<String> originalWords,
             List<String> translatedWords,
             String ip);
}
