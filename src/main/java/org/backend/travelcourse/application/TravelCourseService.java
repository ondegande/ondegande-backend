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
        return TravelCourseResponse.toResponseDto(travelCourse);
    }

    public TravelCourseResponse findById(Long id) {
        TravelCourse travelCourse = travelCourseRepository.findById(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND));
        return TravelCourseResponse.toResponseDto(travelCourse);
    }

    public List<TravelCourseResponse> findByMemberId(Long id) {
        return travelCourseRepository.findByMemberId(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND_MEMBER))
                .stream().map(TravelCourseResponse::toResponseDto).toList();
    }

    public void deleteById(Long id) {
        travelCourseRepository.findById(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND));
        travelCourseRepository.deleteById(id);
    }
}
