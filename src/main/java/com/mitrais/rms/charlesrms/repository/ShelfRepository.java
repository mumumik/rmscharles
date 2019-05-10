package com.mitrais.rms.charlesrms.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.rms.charlesrms.entity.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Integer> {
	
	public Set<Shelf> findAllByOrderByShelfIdAsc();

}
