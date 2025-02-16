package com.kamilglazer.Vendi.repository;

import com.kamilglazer.Vendi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
