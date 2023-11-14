package com.component.core.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id @GeneratedValue Long id;
    @GeneratedValue int accessCode;

    public User(){}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id);
    }

    @Override
    public String toString() {
        return ("id: "+id+" access-code: "+accessCode);
    }
}
