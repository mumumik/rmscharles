package com.mitrais.rms.charlesrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.rms.charlesrms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Optional<Role> findByName(String roleName);

}
