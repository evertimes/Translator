package com.evertimes.translator.model.entity;

import com.evertimes.translator.model.dto.InputData;
import com.evertimes.translator.model.dto.OutputData;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer requestId;

    @Column(nullable = false)
    private String inputData;

    @Column(nullable = false)
    private String outputData;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String sourceLanguageCode;

    @Column(nullable = false)
    private String targetLanguageCode;

    @Column(nullable = false)
    private String ip;

    @OneToMany(mappedBy = "request", cascade = {CascadeType.ALL})
    private Set<Words> wordsInRequest = new HashSet<>();

    public Request() {
    }

    public Request(InputData inputData, OutputData outputData,
                   LocalDateTime dateTime, String ip) {
        this.inputData = inputData.getText();
        this.outputData = outputData.getTranslatedString();
        this.dateTime = dateTime;
        this.sourceLanguageCode = inputData.getSourceLanguageCode();
        this.targetLanguageCode = inputData.getTargetLanguageCode();
        this.ip = ip;
    }

    public void addWord(Words word) {
        wordsInRequest.add(word);
        word.setRequest(this);
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getSourceLanguageCode() {
        return sourceLanguageCode;
    }

    public void setSourceLanguageCode(String params) {
        this.sourceLanguageCode = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestId, request.requestId) && Objects.equals(inputData, request.inputData) && Objects.equals(outputData, request.outputData) && Objects.equals(dateTime, request.dateTime) && Objects.equals(sourceLanguageCode, request.sourceLanguageCode) && Objects.equals(targetLanguageCode, request.targetLanguageCode) && Objects.equals(ip, request.ip) && Objects.equals(wordsInRequest, request.wordsInRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, inputData, outputData, dateTime,
                sourceLanguageCode, targetLanguageCode, ip, wordsInRequest);
    }
}
