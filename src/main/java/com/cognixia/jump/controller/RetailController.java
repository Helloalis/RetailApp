package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.RetailService;

@RequestMapping("/api")
@RestController
public class RetailController {
	
	@Autowired
	RetailService serv;
	
	//Get Methods
	//Books
	@GetMapping("/books")
	public List<Book> getBooks() {
		return serv.getAllBooks();
	}
	@GetMapping("/books/{title}")
	public ResponseEntity<?> getBook(@PathVariable String title) {
		Optional<Book> found = serv.getBookByTitle(title);
		if(found.isEmpty()) {
			
			return ResponseEntity.status(404).body("Book not found");
		}
		Book chars = found.get();
		return ResponseEntity.status(200).body(chars); 
	}
	@GetMapping("/books/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
		return serv.getBookByAuthor(author);
	}
	@GetMapping("/books/longer/{length}")
	public List<Book> getBooksLarger(@PathVariable int length) {
		return serv.getBySizeGreaterThan(length);
	}
	@GetMapping("books/shorter/{length}")
	public List<Book> getBooksShorter(@PathVariable int length) {
		return serv.getBySizeLessThan(length);
	}
	
	
	//Sales
	@GetMapping("/sales")
	public List<Sale> getSales() {
		return serv.getAllSales();
	}
	@GetMapping("/sales/{user}")
	public List<Sale> getSalesByUser(@PathVariable String username) {
		Optional<User> found = serv.getByUsername(username);
		if(found.isEmpty()) {	
			return null;
		}
		User user = found.get();
		return serv.getSaleByUser(user);
	}
	//@GetMapping("/")
	
	//Delete methods
	
	
	//Post methods
	
	
	
	//Put methods
	
	
	
}
