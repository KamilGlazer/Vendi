package com.kamilglazer.Vendi.dto.response;

import com.kamilglazer.Vendi.domain.USER_ROLE;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
