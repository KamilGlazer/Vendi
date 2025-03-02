package com.kamilglazer.Vendi.mapper;

import com.kamilglazer.Vendi.dto.AddressDto;
import com.kamilglazer.Vendi.model.Address;

public class AddressMapper {

    public static AddressDto toDto(Address address) {
        return BaseMapper.returnNullIfNull(address) == null ? null : AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    public static Address toEntity(AddressDto addressDto) {
        return BaseMapper.returnNullIfNull(addressDto) == null ? null : Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .country(addressDto.getCountry())
                .build();
    }

}
