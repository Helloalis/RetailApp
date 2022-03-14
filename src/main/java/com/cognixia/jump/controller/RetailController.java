package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.IncorrectLoginException;
import com.cognixia.jump.exception.NoSuchUserException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
//import com.cognixia.jump.exception.NoSuchUserException;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.CustomUserDetails;
import com.cognixia.jump.model.MiniUser;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.JwtUserDetailsService;
import com.cognixia.jump.service.RetailService;
//import com.cognixia.jump.util.JwtUtil;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class RetailController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
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
	@GetMapping("/books/author/{author}")
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
	@GetMapping("/users") 
	public List<User> getAllUsers() {
		return serv.getAllUsers();
	} 
	@GetMapping("/users/{user}") 
	public Optional<User> getUserByUsername(@PathVariable String user) throws NoSuchUserException {
		return serv.getByUsername(user);
	}
	@GetMapping("/sales/users")
	public List<Sale> getSalesByUser() throws NoSuchUserException {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		Optional<User> found = serv.getByUsername(username);
		if(found.isEmpty()) {	
			throw new NoSuchUserException("User " + username + " was not found");
		}
		User user = found.get();
		return serv.findSalesByUser(user);
	}
	@GetMapping("/sales/books/{title}")
	public List<Sale> getSalesByBook(@PathVariable String title) {
		Optional<Book> found = serv.getBookByTitle(title);
		if(found.isEmpty()) {	
			return new ArrayList<Sale>();
		}
		Book book = found.get();
		return serv.findSalesByBook(book);
	}
	
	//Sales
	@GetMapping("/sales")
	public List<Sale> getSales() {
		return serv.getAllSales();
	}
//	@GetMapping("/sales/user/{user}")
//	public List<Sale> getSalesByUser(@PathVariable String username) throws NoSuchUserException {
//		Optional<User> found = serv.getByUsername(username);
//		if(found.isEmpty()) {	
//			throw new NoSuchUserException("User " + username + " was not found");
//		}
//		User user = found.get();
//		return serv.getSaleByUser(user);
//	}
//	@GetMapping("/sales/{id}")
//	public ResponseEntity<?> getSaleById(@PathVariable int id) {
//		Optional<Sale> found = serv.getSaleById(id);
//		if(found.isEmpty()) {
//			return ResponseEntity.status(404).body("Sale not found");
//		}
//		Sale chars = found.get();
//		return ResponseEntity.status(200).body(chars); 
//	}
	
	//Delete methods
	//Not sure if you would actually need to delete any of these
	//But I think thats just me being way to reluctant to delete stuff
	@DeleteMapping("/books/{title}")
	public ResponseEntity<?> deleteBook(@PathVariable String title) {
		Optional<Book> temp = serv.getBookByTitle(title);
		int id;
		id = temp.get().getId();		
		if(serv.deleteBookById(id)) {
			return ResponseEntity.status(200).body("Book:" + title + " was deleted");
		}
		return ResponseEntity.status(404).body("Book not found");		
	}
	@DeleteMapping("/users/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		Optional<User> temp = serv.getByUsername(username);
		int id;
		id = temp.get().getUserID();		
		if(serv.deleteUserById(id)) {
			return ResponseEntity.status(200).body("User:" + username + " was deleted");
		}
		return ResponseEntity.status(404).body("User not found");		
	}
	@DeleteMapping("/sales/{id}")
	public ResponseEntity<?> deleteSale(@PathVariable int id) {	
		if(serv.deleteSaleById(id)) {
			return ResponseEntity.status(200).body("Sale ID:" + id + " was deleted");
		}
		return ResponseEntity.status(404).body("Sale not found");		
	}
	
	//Post methods
	@PostMapping("/books")
	public ResponseEntity<?> createBook(@RequestBody Book bo) {
		Book update = serv.createBook(bo);
		return ResponseEntity.status(201).body("Book " + bo.getTitle() + " was created");
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User bo) {
		User update = serv.createUser(bo);
		return ResponseEntity.status(201).body("User " + bo.getUsername() + " was created");
	}
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody MiniUser user) throws Exception {
		User use = new User(user.getUsername(), user.getPassword());
		User newU = serv.createUser(use);
		return ResponseEntity.status(201).body("User " + newU.getUsername() + " was created");
	}
	
//	@PostMapping("/sales/")
//	public ResponseEntity<?> createSale(@RequestBody Sale sal) {
//		System.out.println(sal);
//		Sale update = serv.createSale(sal);
//		return ResponseEntity.status(201).body("Sale " + update.getId() + " was created");
//	}
	
	@PostMapping("/sales/{book}/{quant}")
	public ResponseEntity<?> createSalePath(@PathVariable String book, @PathVariable int quant) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		Sale update = serv.createSale(book, username, quant);
		return ResponseEntity.status(201).body("Sale " + update.getId() + " was created");
	}
	
	//Put methods
	@PutMapping("/books/{title}")
	public ResponseEntity<?> updateBook(@RequestBody Book chars) {
		Book update = serv.updateBookById(chars);
		if(update == null) {
			return ResponseEntity.status(404).body("Book " + chars.getTitle() + " was not found");
		}
		else {
			return ResponseEntity.status(202).body("Book " + chars.getTitle() + " was updated");
		}
	}
	@PutMapping("/user/{username}")
	public ResponseEntity<?> updateUser(@RequestBody User chars) {
		User update = serv.updateUserById(chars);
		if(update == null) {
			return ResponseEntity.status(404).body("User " + chars.getUsername() + " was not found");
		}
		else {
			return ResponseEntity.status(202).body("User " + chars.getUsername() + " was updated");
		}
	}
	@PutMapping("/sales/{id}")
	public ResponseEntity<?> updateSale(@RequestBody Sale chars) {
		Sale update = serv.updateSaleById(chars);
		if(update == null) {
			return ResponseEntity.status(404).body("Sale " + chars.getId() + " was not found");
		}
		else {
			return ResponseEntity.status(202).body("Sale " + chars.getId() + " was updated");
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		// try to catch the exception for bad credentials, just so we can set our own message when this doesn't work
		try {
			// make sure we have a valid user by checking their username and password
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()) );
			
		} catch(BadCredentialsException e) {
			// provide our own message on why login didn't work
			throw new IncorrectLoginException("Incorrect username or password: ");
		}
		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername( request.getUsername() );
		
		// generate the token for that user
		final String jwt = jwtUtil.generateToken(userDetails);
		
		// return the token
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );
	}


	
}