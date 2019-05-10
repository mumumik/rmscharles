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

import com.mitrais.rms.charlesrms.entity.Shelf;
import com.mitrais.rms.charlesrms.service.ShelfService;


@Controller
@RequestMapping("shelfs")
public class ShelfController {
	
	private ShelfService shelfService;
	
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

}
