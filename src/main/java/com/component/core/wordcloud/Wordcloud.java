package com.component.core.wordcloud;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Wordcloud {
    @Id
    @GeneratedValue
    Long id;

    private String WordList;

    private Short status;

    public Long getId(){
        return this.id;
    }

    public void setWordList(String List) {
        this.WordList = List;
    }
    public String getWordList() {
        return this.WordList;
    }

    public void setStatus(Short value){
        this.status = value;
    }

    public Short getStatus(){
        return this.status;
    }

    public String toString() {
        return ("id: " + this.id);
    }
}
