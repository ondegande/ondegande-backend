package org.backend.travelcourse.controller;

import java.net.URI;
import java.util.List;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.application.TravelCourseService;
import org.backend.travelcourse.domain.TravelCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TravelCourseController {

    private final TravelCourseService travelCourseService;

    public TravelCourseController(TravelCourseService travelCourseService) {
        this.travelCourseService = travelCourseService;
    }

    @PostMapping("/travel-courses")
    public ResponseEntity<TravelCourseResponse> create(@RequestBody TravelCourseRequest travelCourseRequest) {
        if (travelCourseRequest.schedule() == null
        || travelCourseRequest.concept() == null
        || travelCourseRequest.companion() == null
        || travelCourseRequest.accommodation() == null
        || travelCourseRequest.distance() == null
        || travelCourseRequest.season() == null
        || travelCourseRequest.transport() == null) {
            return ResponseEntity.badRequest().build();
        }

        TravelCourseResponse travelCourseResponse = travelCourseService.save(travelCourseRequest);

        return ResponseEntity.created(URI.create("/travel-courses/" + travelCourseResponse.id())).body(travelCourseResponse);
    }

    @GetMapping("/travel-courses/{id}")
    public ResponseEntity<TravelCourse> myTravelCourse(@PathVariable Long id) {
        return ResponseEntity.ok().body(travelCourseService.findById(id));
    }

    @GetMapping("/travel-courses/members/{id}")
    public List<TravelCourse> myTravelCourseList(@PathVariable Long id) {
        return travelCourseService.findByMemberId(id);
    }

    @DeleteMapping("/travel-courses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        travelCourseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
