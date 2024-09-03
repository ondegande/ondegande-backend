package org.backend.travelcoursedetail.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.backend.place.domain.Place;
import org.backend.travelcourse.domain.TravelCourse;

@Entity
@Table(name = "travel_course_detail")
public class TravelCourseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelCourseDetailId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "travel_course_id", nullable = false)
    private TravelCourse travelCourse;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    private int courseDay;

    @Column(nullable = false)
    private int orderInDay;

    public TravelCourseDetail(Long travelCourseDetailId, TravelCourse travelCourse, Place place, int courseDay,
                              int orderInDay) {
        this.travelCourseDetailId = travelCourseDetailId;
        this.travelCourse = travelCourse;
        this.place = place;
        this.courseDay = courseDay;
        this.orderInDay = orderInDay;
    }

    public TravelCourseDetail(TravelCourse travelCourse, Place place, int courseDay, int orderInDay) {
        this.travelCourse = travelCourse;
        this.place = place;
        this.courseDay = courseDay;
        this.orderInDay = orderInDay;
    }

    public TravelCourseDetail(int courseDay, int orderInDay) {
        this.courseDay = courseDay;
        this.orderInDay = orderInDay;
    }

    public TravelCourseDetail() {}

    public Long getTravelCourseDetailId() {
        return travelCourseDetailId;
    }

    public TravelCourse getTravelCourse() {
        return travelCourse;
    }

    public Place getPlace() {
        return place;
    }

    public int getCourseDay() {
        return courseDay;
    }

    public int getOrderInDay() {
        return orderInDay;
    }
}
