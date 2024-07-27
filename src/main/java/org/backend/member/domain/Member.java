package org.backend.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;
import org.backend.common.BaseTimeEntity;

@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String name;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Member(String email, String name, String refreshToken, Role role) {
        this.email = email;
        this.name = name;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public Member(String email,
                  String name,
                  Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Member() {

    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Role getRole() {
        return role;
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

    public Member update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
