package org.backend.youtubecourse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.backend.travelcourse.domain.TravelCourse;

@Entity
public class YoutubeCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String youtubeLink;
    @OneToOne
    private TravelCourse travelCourse;

    public YoutubeCourse() {

    }

    public YoutubeCourse(String youtubeLink, TravelCourse travelCourse) {
        this.youtubeLink = youtubeLink;
        this.travelCourse = travelCourse;
    }

    public Long getId() {
        return id;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public TravelCourse getTravelCourse() {
        return travelCourse;
    }
}
