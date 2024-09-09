package org.backend.travelcourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.backend.common.BaseTimeEntity;

@Entity
@Table(name = "travel_course")
public class TravelCourse extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelCourseId;

    @Column(nullable = false, length = 500)
    private String courseName;

    @Column(nullable = false)
    private int days;

    @Column(nullable = true, length = 500)
    private String creatorName;

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private CreatorType creatorType;

    @Column(nullable = true, name = "youtube_url", length = 1000)
    private String youtubeUrl;

    @Column(nullable = true, name = "youtube_image_url", length = 1000)
    private String youtubeImageUrl;

    @Column(nullable = true, name = "view_count")
    private Long viewCount;

    public TravelCourse(Long travelCourseId,
                        String courseName,
                        int days,
                        String creatorName,
                        CreatorType creatorType,
                        String youtubeUrl,
                        String youtubeImageUrl,
                        Long viewCount) {
        this.travelCourseId = travelCourseId;
        this.courseName = courseName;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.youtubeUrl = youtubeUrl;
        this.youtubeImageUrl = youtubeImageUrl;
        this.viewCount = viewCount;
    }

    public TravelCourse(String courseName,
                        int days,
                        String creatorName,
                        CreatorType creatorType,
                        String youtubeUrl) {
        this.courseName = courseName;
        this.days = days;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.youtubeUrl = youtubeUrl;
    }

    public TravelCourse(String courseName, int days) {
        this.courseName = courseName;
        this.days = days;
    }

    public TravelCourse() {}

    public Long getViewCount() {
        return viewCount;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
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

    public int getDays() {
        return days;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public CreatorType getCreatorType() {
        return creatorType;
    }
}
