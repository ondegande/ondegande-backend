package org.backend.travelcourse.application;

import java.util.List;
import org.backend.global.response.ResponseCode;
import org.backend.travelcourse.domain.CreatorType;
import org.backend.travelcourse.dto.TravelCourseListResponse;
import org.backend.travelcourse.dto.TravelCourseRequest;
import org.backend.travelcourse.dto.TravelCourseResponse;
import org.backend.travelcourse.domain.TravelCourse;
import org.backend.travelcourse.domain.TravelCourseRepository;
import org.backend.travelcourse.exception.TravelCouresNotFoundException;
import org.backend.travelcoursedetail.domain.TravelCourseDetailRepository;
import org.backend.travelcoursedetail.dto.TravelCourseDetailResponse;
import org.backend.travelcoursedetail.excetion.TravelCourseDetailNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TravelCourseService {

    private final TravelCourseRepository travelCourseRepository;
    private final TravelCourseDetailRepository travelCourseDetailRepository;

    public TravelCourseService(TravelCourseRepository travelCourseRepository, TravelCourseDetailRepository travelCourseDetailRepository) {
        this.travelCourseRepository = travelCourseRepository;
        this.travelCourseDetailRepository = travelCourseDetailRepository;
    }

    public TravelCourse save(TravelCourseRequest request) {
        return travelCourseRepository.save(request.toEntity());
    }

    public TravelCourseResponse findById(Long id) {
        TravelCourse travelCourse = travelCourseRepository.findById(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND));

        List<TravelCourseDetailResponse> travelCourseDetailResponses = travelCourseDetailRepository.findTravelCourseDetailsByTravelCourse(travelCourse)
                .orElseThrow(() -> new TravelCourseDetailNotFoundException(ResponseCode.COURSE_DETAIL_NOT_FOUND))
                .stream().map(TravelCourseDetailResponse::toResponseDto)
                .toList();

        return TravelCourseResponse.toResponseDto(travelCourse, travelCourseDetailResponses);
    }

    public List<TravelCourseListResponse> findByMemberId(Long id) {
        return travelCourseRepository.findByMemberId(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND_MEMBER))
                .stream().map(TravelCourseListResponse::toResponseListDto).toList();
    }

    public List<TravelCourseListResponse> findByCreatorType() {
        return travelCourseRepository.findTravelCoursesByCreatorType(CreatorType.YOUTUBER)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND_YOUTUBER))
                .stream().map(TravelCourseListResponse::toResponseListDto).toList();
    }

    public void deleteById(Long id) {
        travelCourseRepository.findById(id)
                .orElseThrow(() -> new TravelCouresNotFoundException(ResponseCode.COURSE_NOT_FOUND));
        travelCourseRepository.deleteById(id);
    }
}
