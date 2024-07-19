package org.backend.travelcourse.application;

import java.util.List;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.domain.TravelCourseRepository;
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
                travelCourse.getSeason(),
                travelCourse.getCompanion(),
                travelCourse.getConcept(),
                travelCourse.getTransport(),
                travelCourse.getDistance(),
                travelCourse.getAccommodation(),
                travelCourse.isShared()
        );
    }

    public TravelCourse findById(Long id) {
        return travelCourseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 여행코스 입니다."));
    }

    public List<TravelCourse> findByMemberId(Long id) {
        return travelCourseRepository.findByMemberId(id);
    }

    public List<TravelCourse> list(Long id) {
        return travelCourseRepository.findByMemberId(id);
    }

    public void deleteById(Long id) {
        travelCourseRepository.deleteById(id);
    }
}
