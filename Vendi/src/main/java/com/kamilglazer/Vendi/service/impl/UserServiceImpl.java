package com.kamilglazer.Vendi.service.impl;

import com.kamilglazer.Vendi.repository.UserRepository;
import com.kamilglazer.Vendi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


}
