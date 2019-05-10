package com.mitrais.rms.charlesrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.rms.charlesrms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);

}
