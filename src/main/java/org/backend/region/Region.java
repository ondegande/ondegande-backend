package org.backend.region;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public Region() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
