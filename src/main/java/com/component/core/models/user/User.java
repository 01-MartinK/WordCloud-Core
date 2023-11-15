package com.component.core.models.user;

import jakarta.persistence.*;

import java.util.Objects;

/*

CREATE TABLE user(
 id Long
 accessCode Long
 primary_key(id)
);

 */

@Entity
public class User {
    @Id
    @GeneratedValue
    Long id;

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
