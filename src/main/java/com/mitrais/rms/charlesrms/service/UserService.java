package com.mitrais.rms.charlesrms.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mitrais.rms.charlesrms.entity.User;

public interface UserService  extends UserDetailsService {
	
	Optional<User> findByUserName(String username);

}
