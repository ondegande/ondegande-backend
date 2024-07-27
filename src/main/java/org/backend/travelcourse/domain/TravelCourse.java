package org.backend.travelcourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.backend.common.BaseTimeEntity;
import org.backend.courseinfo.Accommodation;
import org.backend.courseinfo.Companion;
import org.backend.courseinfo.Concept;
import org.backend.courseinfo.Distance;
import org.backend.courseinfo.Schedule;
import org.backend.courseinfo.Season;
import org.backend.courseinfo.Transport;
import org.backend.member.domain.Member;

@Entity
public class TravelCourse extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private Season season;

    @Column(nullable = false)
    private Companion companion;

    @Column(nullable = false)
    private Concept concept;

    @Column(nullable = false)
    private Transport transport;

    @Column(nullable = false)
    private Distance distance;

    @Column(nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    private boolean isShared;

    @ManyToOne
    private Member member;

    public TravelCourse(Schedule schedule,
                        Season season,
                        Companion companion,
                        Concept concept,
                        Transport transport,
                        Distance distance,
                        Accommodation accommodation,
                        boolean isShared,
                        Member member) {
        this.schedule = schedule;
        this.season = season;
        this.companion = companion;
        this.concept = concept;
        this.transport = transport;
        this.distance = distance;
        this.accommodation = accommodation;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(Schedule schedule,
                        Season season,
                        Companion companion,
                        Concept concept,
                        Transport transport,
                        Distance distance,
                        Accommodation accommodation,
                        boolean isShared) {
        this.schedule = schedule;
        this.season = season;
        this.companion = companion;
        this.concept = concept;
        this.transport = transport;
        this.distance = distance;
        this.accommodation = accommodation;
        this.isShared = isShared;
    }

    public TravelCourse() {}

    public Long getId() {
        return id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Season getSeason() {
        return season;
    }

    public Companion getCompanion() {
        return companion;
    }

    public Concept getConcept() {
        return concept;
    }

    public Transport getTransport() {
        return transport;
    }

    public Distance getDistance() {
        return distance;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public boolean isShared() {
        return isShared;
    }

    public Member getMember() {
        return member;
    }
}
