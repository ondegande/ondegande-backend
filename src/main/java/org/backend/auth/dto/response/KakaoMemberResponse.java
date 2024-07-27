package org.backend.auth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoMemberResponse(
        Long id,
        KakaoAcount kakaoAccount
) {

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoAcount(String email) {
    }
}
