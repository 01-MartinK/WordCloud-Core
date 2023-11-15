package com.component.core.models;

import java.util.List;

public class WorkRequest {
    private int code;
    private List<String> words;
    private List<String> excludedWords;

    public WorkRequest(int code) {
        this.code = code;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setExcludedWords(List<String> excludedWords) {
        this.excludedWords = excludedWords;
    }
}
