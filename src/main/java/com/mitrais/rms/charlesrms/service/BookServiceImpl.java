package com.mitrais.rms.charlesrms.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.entity.Status;
import com.mitrais.rms.charlesrms.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository theBookRepository) {
		bookRepository = theBookRepository;
	}

	@Override
	public Set<Book> findAll() {
		
		Set<Book> books = bookRepository.findAllByOrderByStatusAsc();
		
		return books;
	}

	@Override
	public Book findById(int theId) {
		
		Optional<Book> result = bookRepository.findById(theId);
		
		Book theBook = null;
		
		if(result.isPresent()){
			theBook = result.get();
		}else {
			theBook = null;
		}
		
		return theBook;
	}

	@Override
	@Transactional
	public void save(Book theBook) {
		
		if(theBook.getId() == 0) {
			theBook.setId(0);
			theBook.setStatus(Status.NOT_SHELVED);
		}
		
		bookRepository.save(theBook);

	}

	@Override
	public void deleteById(int theId) {
		
		Optional<Book> result = bookRepository.findById(theId);
		
		if(result.isPresent()){
			bookRepository.deleteById(theId);
		}

	}

}
