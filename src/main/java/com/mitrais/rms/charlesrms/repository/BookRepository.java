package com.mitrais.rms.charlesrms.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.entity.Status;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public Set<Book> findAllByOrderByStatusAsc();
	
	public Set<Book> findByTitleIgnoreCaseContaining(String theTitle);
	
	public Set<Book> findByStatus(Status theStatus);

}
