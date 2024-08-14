package org.backend.place.domain;

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
@Table(name = "places")
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    @Column(nullable = false, unique = true)
    private String contentid;  // API에서 제공되는 placeId와 대응

    @Column(nullable = false, length = 100)
    private String title;  // API의 name과 대응

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaceType contentTypeId;  // API의 type과 대응

    @Column(length = 255)
    private String addr1;  // API의 address와 대응

    private Double mapx;  // API의 latitude와 대응

    private Double mapy;  // API의 longitude와 대응

    @Column(length = 255)
    private String firstimage;  // API의 imageUrl과 대응

    @Column(columnDefinition = "TEXT")
    private String description;

    public Place() {
    }

    public Place(String contentid,
                 String title,
                 PlaceType contentTypeId,
                 String addr1,
                 Double mapx,
                 Double mapy,
                 String firstimage,
                 String description) {
        this.contentid = contentid;
        this.title = title;
        this.contentTypeId = contentTypeId;
        this.addr1 = addr1;
        this.mapx = mapx;
        this.mapy = mapy;
        this.firstimage = firstimage;
        this.description = description;
    }

    public Place(Long placeId,
                 String contentid,
                 String title,
                 PlaceType contentTypeId,
                 String addr1,
                 Double mapx,
                 Double mapy,
                 String firstimage,
                 String description) {
        this.placeId = placeId;
        this.contentid = contentid;
        this.title = title;
        this.contentTypeId = contentTypeId;
        this.addr1 = addr1;
        this.mapx = mapx;
        this.mapy = mapy;
        this.firstimage = firstimage;
        this.description = description;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getContentid() {
        return contentid;
    }

    public String getTitle() {
        return title;
    }

    public PlaceType getContentTypeId() {
        return contentTypeId;
    }

    public String getAddr1() {
        return addr1;
    }

    public Double getMapx() {
        return mapx;
    }

    public Double getMapy() {
        return mapy;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public String getDescription() {
        return description;
    }
}
