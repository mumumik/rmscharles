package com.mitrais.rms.charlesrms.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mitrais.rms.charlesrms.entity.Book;
import com.mitrais.rms.charlesrms.entity.PayloadBookShelf;
import com.mitrais.rms.charlesrms.entity.Shelf;
import com.mitrais.rms.charlesrms.entity.Status;
import com.mitrais.rms.charlesrms.service.BookService;
import com.mitrais.rms.charlesrms.service.ShelfService;


@Controller
@RequestMapping("shelfs")
public class ShelfController {
	
	private ShelfService shelfService;
	@Autowired
	private BookService bookService;
	
	@Autowired
	public ShelfController(ShelfService theShelfService) {
		shelfService = theShelfService;
	}
	
	@GetMapping
	public String listShelf(Model theModel){
		
		Set<Shelf> theShelfs = shelfService.findAll();
		
		theModel.addAttribute("shelfs", theShelfs);
		
		return "shelfs/index";
		
	}
	
	@GetMapping("add")
	public String addShelf(Model theModel) {
		
		Shelf theShelf = new Shelf();
		
		theModel.addAttribute("shelf", theShelf);
		
		return "shelfs/form";
	}
	
	@PostMapping("save")
	public String saveShelf(@ModelAttribute("shelf") Shelf theShelf) {
		
		//save the book
		shelfService.save(theShelf);
		
		//use redirect to prevent duplicate subimissions
		return "redirect:/shelfs";
		
	}
	
	@GetMapping("delete/{shelfId}")
	public String deleteShelf(@PathVariable int shelfId) {
		
		shelfService.deleteById(shelfId);
		
		return "redirect:/shelfs";
	}
	
	@GetMapping("edit/{shelfId}")
	public String editShelf(@PathVariable int shelfId, Model theModel) {
		
		Shelf theShelf = shelfService.findById(shelfId);
		
		theModel.addAttribute("shelf", theShelf);
		
		return "shelfs/form";
		
	}
	
	@GetMapping("view/{shelfId}")
	public String viewShelf(@PathVariable int shelfId, Model theModel) {
		
		Shelf theShelf = shelfService.findById(shelfId);
		
		theModel.addAttribute("shelf", theShelf);
		
		return "shelfs/view";
		
	}
	
	@GetMapping("addBook/{shelfId}")
	public String addBookToShelf(@PathVariable int shelfId, Model theModel) {
		
		Shelf theShelf = shelfService.findById(shelfId);
		Set<Book> books = bookService.findByStatus(Status.NOT_SHELVED);
		
		theModel.addAttribute("shelf", theShelf);
		theModel.addAttribute("books", books);
		
		return "shelfs/add";
		
	}
	
	@PostMapping("saveBook")
	public String saveBookToShelf(@ModelAttribute("saveBook") PayloadBookShelf payloadBookShelf) {
		
		//save the book
		System.out.println("Shelf Id "+payloadBookShelf.getShelfId());
		System.out.println("Book Id "+payloadBookShelf.getBookId());
		shelfService.addBook(payloadBookShelf.getShelfId(),payloadBookShelf.getBookId());
		
		//use redirect to prevent duplicate subimissions
		return "redirect:/shelfs/view"+payloadBookShelf.getShelfId();
		
	}
	
	@DeleteMapping(params = {"shelfId", "bookId"})
	public Shelf removeBook(@RequestParam int shelfId, @RequestParam int bookId) {
		
		return shelfService.removeBook(shelfId, bookId);
		
	}

}
