package com.mitrais.rms.charlesrms.service;

import java.util.Set;

import com.mitrais.rms.charlesrms.entity.Book;


public interface BookService {
	
	public Set<Book> findAll();
	
	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);

}
