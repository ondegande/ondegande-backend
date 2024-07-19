package org.backend.travelcourselocation;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TravelCourseLocationId implements Serializable {

    private Long travelCourseId;
    private Long locationId;

    public TravelCourseLocationId() {}

    public TravelCourseLocationId(Long travelCourseId, Long locationId) {
        this.travelCourseId = travelCourseId;
        this.locationId = locationId;
    }

    public Long getTravelCourseId() {
        return travelCourseId;
    }

    public Long getLocationId() {
        return locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCourseLocationId that = (TravelCourseLocationId) o;
        return Objects.equals(travelCourseId, that.travelCourseId) &&
                Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelCourseId, locationId);
    }
}
