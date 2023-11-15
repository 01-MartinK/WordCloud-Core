package com.component.core.user;

import com.component.core.wordcloud.Wordcloud;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Wordcloud.class)
    @JoinColumn(name = "id")
    private Long accessCode;

    public User(){}

    public void setAccessCode(Long value) {
        this.accessCode = value;
    }

    public Long getAccessCode() {
        return this.accessCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;
        return Objects.equals(this.id, user.id);
    }

    @Override
    public String toString() {
        return ("id: "+this.id+" access-code: "+this.accessCode);
    }
}
