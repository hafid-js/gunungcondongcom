package com.hafidtech.gunungcondongcom.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDerailsService {

    UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException;

    UserDetails loadUserById(Long id);
}
