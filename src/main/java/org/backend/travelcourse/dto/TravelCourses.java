package org.backend.travelcourse.dto;

import java.util.List;

public class TravelCourses {

    private List<TravelCourseListResponse> travelCourses;

    public TravelCourses(){}

    public TravelCourses(List<TravelCourseListResponse> travelCourses) {
        this.travelCourses = travelCourses;
    }

    public List<TravelCourseListResponse> getTravelCourses() {
        return travelCourses;
    }
}
