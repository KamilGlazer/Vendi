package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.AddressDto;
import com.kamilglazer.Vendi.model.Address;

public class AddressMapper {

    private static <T> T returnNullIfNull(T object) {
        return object;
    }

    public static AddressDto toDto(Address address) {
        return returnNullIfNull(address) == null ? null : AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    public static Address toEntity(AddressDto addressDto) {
        return returnNullIfNull(addressDto) == null ? null : Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .country(addressDto.getCountry())
                .build();
    }

}
