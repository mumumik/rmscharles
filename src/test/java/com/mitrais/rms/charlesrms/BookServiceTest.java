package com.mitrais.rms.charlesrms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.entity.Status;
import com.mitrais.rms.charlesrms.repository.BookRepository;
import com.mitrais.rms.charlesrms.service.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookRepository bookRepository;
	
	@Test
public void findById_ReturnsOk() {
		
		//setup
		when(bookRepository.findById(anyInt())).thenReturn(
				Optional.of(new Book(1, "978-979-2763-37-9","ROBIN HOOD","Paul Creswick",Status.NOT_SHELVED))
		);
				
		
		//action
		Book actual = bookService.findById(1);
		
		//assertion
		Book book = actual;
		assertEquals("ROBIN HOOD", book.getTitle());
		verify(bookRepository, times(1)).findById(anyInt());
		
	}
	
	

}
