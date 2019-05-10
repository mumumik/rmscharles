package com.mitrais.rms.charlesrms.service;

import java.util.Set;

import com.mitrais.rms.charlesrms.entity.Shelf;

public interface ShelfService {
	
	public Set<Shelf> findAll();
	
	public Shelf findById(int theId);
	
	public void save(Shelf theShelf);
	
	public void deleteById(int theId);
	
	public Shelf addBook(int shelfId, int bookId);
	
	public Shelf removeBook(int shelfId, int bookId);

}
