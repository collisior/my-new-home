package net.springbootapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.springbootapp.model.User;
import net.springbootapp.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
