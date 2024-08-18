package org.backend.travelcourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.backend.common.BaseTimeEntity;
import org.backend.member.domain.Member;

@Entity
public class TravelCourse extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private Concept concept;

    @Column(nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    private boolean isShared;

    @ManyToOne
    private Member member;

    public TravelCourse(Long id,
                        Schedule schedule,
                        Concept concept,
                        Accommodation accommodation,
                        boolean isShared,
                        Member member) {
        this.id = id;
        this.schedule = schedule;
        this.concept = concept;
        this.accommodation = accommodation;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(Schedule schedule,
                        Concept concept,
                        Accommodation accommodation,
                        boolean isShared,
                        Member member) {
        this.schedule = schedule;
        this.concept = concept;
        this.accommodation = accommodation;
        this.isShared = isShared;
        this.member = member;
    }

    public TravelCourse(Schedule schedule,
                        Concept concept,
                        Accommodation accommodation,
                        boolean isShared) {
        this.schedule = schedule;
        this.concept = concept;
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

    public Concept getConcept() {
        return concept;
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
