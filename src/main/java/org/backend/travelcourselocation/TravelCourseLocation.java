package org.backend.travelcourselocation;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.backend.location.domain.Location;
import org.backend.travelcourse.domain.TravelCourse;

@Entity
public class TravelCourseLocation {

    @EmbeddedId
    private TravelCourseLocationId id;

    @ManyToOne
    @MapsId("travelCourseId")
    @JoinColumn(name = "travel_course_id")
    private TravelCourse travelCourse;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    public TravelCourseLocation() {

    }

    public TravelCourseLocation(TravelCourse travelCourse, Location location) {
        this.travelCourse = travelCourse;
        this.location = location;
        this.id = new TravelCourseLocationId(travelCourse.getId(), location.getId());
    }

    public TravelCourseLocationId getId() {
        return id;
    }

    public TravelCourse getTravelCourse() {
        return travelCourse;
    }

    public Location getLocation() {
        return location;
    }
}
