package org.backend.travelcourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.backend.common.BaseTimeEntity;
import org.backend.member.domain.Member;

@Entity
@Table(name = "travel_course")
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

    @Column(nullable = true)
    private String creatorName;

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private CreatorType creatorType;

    @Column(nullable = true, name = "youtube_url")
    private String youtubeURL;

    @Column(nullable = true, name = "youtuber_image_url")
    private String youtubeImageUrl;

    @ManyToOne
    private Member member;

    public TravelCourse(Long travelCourseId, String courseName, boolean isShared, int days, String creatorName,
                        CreatorType creatorType, String youtubeURL, String youtubeImageUrl, Member member) {
        this.travelCourseId = travelCourseId;
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.youtubeURL = youtubeURL;
        this.youtubeImageUrl = youtubeImageUrl;
        this.member = member;
    }

    public TravelCourse(String courseName, boolean isShared, int days, String creatorName, CreatorType creatorType,
                        String youtubeURL, Member member) {
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.youtubeURL = youtubeURL;
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

    public TravelCourse(String courseName, boolean isShared, int days) {
        this.courseName = courseName;
        this.isShared = isShared;
        this.days = days;
    }

    public TravelCourse() {}

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setCreatorType(CreatorType creatorType) {
        this.creatorType = creatorType;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public String getYoutubeImageUrl() {
        return youtubeImageUrl;
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
