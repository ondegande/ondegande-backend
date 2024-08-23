package org.backend.travelcourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.backend.common.BaseTimeEntity;
import org.backend.member.domain.Member;

@Entity
public class TravelCourse extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(nullable = false)
    private boolean isShared;

    @ManyToOne
    private Member member;

    public TravelCourse(Long id, String name, boolean isShared, Member member) {
        this.id = id;
        this.name = name;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(String name, boolean isShared) {
        this.name = name;
        this.isShared = isShared;
    }

    public TravelCourse() {}

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public boolean isShared() {
        return isShared;
    }

    public Member getMember() {
        return member;
    }
}
