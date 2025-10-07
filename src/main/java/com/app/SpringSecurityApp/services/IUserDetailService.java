package com.app.SpringSecurityApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
