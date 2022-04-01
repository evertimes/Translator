package com.evertimes.translator.controller;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.service.interfaces.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import com.evertimes.translator.model.dto.OutputData;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class TranslateController {
    private final TranslateService translateService;

    @Autowired
    public TranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping(value = "/translate")
    public ResponseEntity<OutputData> translate(@RequestBody InputData data) {
        try {
            OutputData output = translateService.translate(data);
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
