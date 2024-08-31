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
    private String courseName;

    @Column(nullable = false)
    private boolean isShared;

    @Column(nullable = false)
    private int days;

    @Column(nullable = false)
    private String creatorName;

    @Column(nullable = false)
    private CreatorType creatorType;

    @ManyToOne
    private Member member;


    public TravelCourse(Long travelCourseId, String courseName, boolean isShared, int days, String creatorName,
                        CreatorType creatorType, Member member) {
        this.travelCourseId = travelCourseId;
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.member = member;
    }

    public TravelCourse(String courseName,
                        boolean isShared,
                        int days,
                        String creatorName,
                        CreatorType creatorType,
                        Member member) {
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.member = member;
    }

    public TravelCourse(String courseName,
                        boolean isShared,
                        int days,
                        String creatorName,
                        CreatorType creatorType) {
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
    }

    public Long getTravelCourseId() {
        return travelCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean isShared() {
        return isShared;
    }

    public int getDays() {
        return days;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public CreatorType getCreatorType() {
        return creatorType;
    }

    public Member getMember() {
        return member;
    }
}
