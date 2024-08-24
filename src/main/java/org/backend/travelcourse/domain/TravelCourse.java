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
    private Long travelCourseId;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(nullable = false)
    private boolean isShared;

    @ManyToOne
    private Member member;

    public TravelCourse(Long travelCourseId, String name, boolean isShared, Member member) {
        this.travelCourseId = travelCourseId;
        this.name = name;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(String name, boolean isShared, Member member) {
        this.name = name;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(String name, boolean isShared) {
        this.name = name;
        this.isShared = isShared;
    }

    public String getName() {
        return name;
    }

    public TravelCourse() {}

    public Long getTravelCourseId() {
        return travelCourseId;
    }

    public boolean isShared() {
        return isShared;
    }

    public Member getMember() {
        return member;
    }
}
