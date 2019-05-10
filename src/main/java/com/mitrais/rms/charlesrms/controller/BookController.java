package com.mitrais.rms.charlesrms.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.service.BookService;



@Controller
@RequestMapping("books")
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService theBookService) {
		bookService = theBookService;
	}
	
	@GetMapping
	public String listBook(Model theModel) {
		
		Set<Book> theBooks = bookService.findAll();
		
		theModel.addAttribute("books", theBooks);
		
		return "books/index";
	}
	
	@GetMapping("add")
	public String addBook(Model theModel) {
		
		Book theBook = new Book();
		
		theModel.addAttribute("book", theBook);
		
		return "books/form";
	}
	
	@PostMapping("save")
	public String saveBook(@ModelAttribute("book") Book theBook) {
		
		//save the book
		bookService.save(theBook);
		
		//use redirect to prevent duplicate subimissions
		return "redirect:/books";
		
	}
	
	@GetMapping("delete/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		
		bookService.deleteById(bookId);
		
		return "redirect:/books";
	}
	
	@GetMapping("edit/{bookId}")
	public String editBook(@PathVariable int bookId, Model theModel) {
		
		Book theBook = bookService.findById(bookId);
		
		theModel.addAttribute("book", theBook);
		
		return "books/form";
		
	}

}
