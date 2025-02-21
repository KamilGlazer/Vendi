package com.kamilglazer.Vendi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
