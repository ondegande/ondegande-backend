package org.backend.place.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.backend.common.BaseTimeEntity;

@Entity
@Table(name = "places")
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public Place() {
    }

    public Place(Long placeId, String placeName, Double latitude, Double longitude) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(String placeName, Double latitude, Double longitude) {
        this.placeName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
