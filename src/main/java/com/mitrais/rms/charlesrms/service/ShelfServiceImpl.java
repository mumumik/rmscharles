package com.mitrais.rms.charlesrms.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.entity.Shelf;
import com.mitrais.rms.charlesrms.entity.Status;
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

		Optional<Shelf> result = shelfRepository.findById(shelfId);
		
		Shelf theShelf = null;
		
		if(result.isPresent()) {
			theShelf = result.get();
			Book theBook = null;
			Optional<Book> book = bookRepository.findById(bookId);
			if(book.isPresent()) {
				
				theBook = book.get();
				
				if(theBook.getStatus().equals(Status.SHELVED)) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This book is already in a shelf");
				}
				
				int shelfCurrentCapacity;
				int shelfMaximumCapacity;
				
				shelfCurrentCapacity = theShelf.getCurrentCapacity();
				shelfMaximumCapacity = theShelf.getMaxCapacity();
				shelfCurrentCapacity++;
				
				if (shelfCurrentCapacity > shelfMaximumCapacity) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shelf Current Capacity cannot exceed the Maximum Capacity");
				}
				
				theShelf.setCurrentCapacity(shelfCurrentCapacity);
				theBook.setStatus(Status.SHELVED);
				theShelf.addBook(theBook);
				
				shelfRepository.save(theShelf);
				
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Id not found - " + bookId);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelf Id not found - " + shelfId);
		}
		
		return theShelf;
		
	}

	@Override
	public Shelf removeBook(int shelfId, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
