package com.evertimes.translator.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Words {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer wordId;
    @Column(nullable = false)
    private String originWord;
    @Column(nullable = false)
    private String resultWord;

    @ManyToOne
    @JoinColumn(name = "request_id",nullable = false)
    private Request request;

    public Words() {
    }

    public Words(String originWord, String resultWord) {
        this.originWord = originWord;
        this.resultWord = resultWord;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getOriginWord() {
        return originWord;
    }

    public void setOriginWord(String originWord) {
        this.originWord = originWord;
    }

    public String getResultWord() {
        return resultWord;
    }

    public void setResultWord(String resultWord) {
        this.resultWord = resultWord;
    }

    @Override
    public String toString() {
        return "Words{" +
                "wordId=" + wordId +
                ", originWord='" + originWord + '\'' +
                ", resultWord='" + resultWord + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return Objects.equals(originWord, words.originWord) && Objects.equals(resultWord, words.resultWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originWord, resultWord);
    }
}
