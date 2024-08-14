package org.backend.travelcourse.application;

import java.util.List;
import org.backend.global.response.ResponseCode;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.domain.TravelCourseRepository;
import org.backend.travelcourse.exception.TravelCouresNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TravelCourseService {

    private final TravelCourseRepository travelCourseRepository;

    public TravelCourseService(TravelCourseRepository travelCourseRepository) {
        this.travelCourseRepository = travelCourseRepository;
    }

    public TravelCourseResponse save(TravelCourseRequest request) {
        TravelCourse travelCourse = travelCourseRepository.save(request.toEntity());

        return new TravelCourseResponse(
                travelCourse.getId(),
                travelCourse.getSchedule(),
                travelCourse.getConcept(),
                travelCourse.getAccommodation(),
                travelCourse.isShared()
        );
    }

    public TravelCourse findById(Long id) {
        return travelCourseRepository.findById(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND));
    }

    public List<TravelCourse> findByMemberId(Long id) {
        return travelCourseRepository.findByMemberId(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND_MEMBER));
    }

    public void deleteById(Long id) {
        travelCourseRepository.deleteById(id);
    }
}
