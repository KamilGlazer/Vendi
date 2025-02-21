package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
