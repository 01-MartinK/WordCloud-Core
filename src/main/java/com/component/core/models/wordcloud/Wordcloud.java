package com.component.core.models.wordcloud;

import jakarta.persistence.*;
import org.hibernate.Length;

@Entity
@Table(name = "wordcloud")
public class Wordcloud {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(length = Length.LONG32)
    private String wordList;

    @Column
    private int status = 0;

    public Long getId(){
        return this.id;
    }

    public void setWordList(String List) {
        this.wordList = List;
    }
    public String getWordList() {
        return this.wordList;
    }

    public void setStatus(int value){
        this.status = value;
    }

    public int getStatus(){
        return this.status;
    }

    public String toString() {
        return ("id: " + this.id);
    }
}
