package org.backend.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private LocalTime createdTime;

    public Member(String email,
                  String name,
                  LocalTime createdTime) {
        this.email = email;
        this.name = name;
        this.createdTime = createdTime;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }
}
