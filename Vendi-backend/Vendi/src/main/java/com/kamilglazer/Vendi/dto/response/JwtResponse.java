package com.kamilglazer.Vendi.dto.response;

import com.kamilglazer.Vendi.domain.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
