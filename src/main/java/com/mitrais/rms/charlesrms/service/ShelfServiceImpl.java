package com.mitrais.rms.charlesrms.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.rms.charlesrms.entity.Shelf;
import com.mitrais.rms.charlesrms.repository.BookRepository;
import com.mitrais.rms.charlesrms.repository.ShelfRepository;

@Service
public class ShelfServiceImpl implements ShelfService {
	
	@Autowired
	ShelfRepository shelfRepository;
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public Set<Shelf> findAll() {
		
		Set<Shelf> shelfs = shelfRepository.findAllByOrderByShelfIdAsc();
		return shelfs;
		
	}

	@Override
	public Shelf findById(int theId) {
		
		Optional<Shelf> result = shelfRepository.findById(theId);
		
		Shelf theShelf = null;
		
		if(result.isPresent()){
			theShelf = result.get();
		}else {
			theShelf = null;
		}
		
		return theShelf;
		
	}

	@Override
	public void save(Shelf theShelf) {
		
		if(theShelf.getShelfId() == 0) {
			theShelf.setShelfId(0);
			theShelf.setCurrentCapacity(0);
		}
		
		shelfRepository.save(theShelf);

	}

	@Override
	public void deleteById(int theId) {
		
		Optional<Shelf> result = shelfRepository.findById(theId);
		
		if(result.isPresent()){
			shelfRepository.deleteById(theId);
		}

	}

	@Override
	public Shelf addBook(int shelfId, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelf removeBook(int shelfId, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
