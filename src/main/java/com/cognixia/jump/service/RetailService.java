package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookSale;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.BookRepository;
import com.cognixia.jump.repository.BookSaleRepository;
import com.cognixia.jump.repository.SaleRepository;
import com.cognixia.jump.repository.UserRepository;

public class RetailService {
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	SaleRepository saleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BookSaleRepository bookSaleRepo;

	//Crud methods --------------------------------------------
	//Create 
	public Book createBook(Book chars) {
		Book ret = new Book();
		ret.setTitle(chars.getTitle());
		ret.setAuthor(chars.getAuthor());
		ret.setPrice(chars.getPrice());
		ret.setSize(chars.getSize());
		ret.setQty(chars.getQty());
		Book created = bookRepo.save(ret);
		return created;
	}
	public User createUser(User chars) {
		User ret = new User();
		ret.setPassword(chars.getPassword());
		ret.setUsername(chars.getUsername());
		User created = userRepo.save(ret);
		return created;
	}

	public BookSale createBookSale(BookSale bs) {
		BookSale ret = new BookSale();
		ret.setBook(bs.getBook());
		ret.setSale(bs.getSale());
		ret.setQuantity(bs.getQuantity());
		BookSale created = bookSaleRepo.save(ret);
		return created;
	}
	
	public Sale createSale(Sale sal) {
		Sale ret = new Sale();
		ret.setUser(sal.getUser());
		ret.setBookSale(sal.getBookSale());
		ret.setTotalPrice();
		Sale salRet = saleRepo.save(ret);
		return salRet;
	}
	
	//Read all
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public List<Sale> getAllSales() {
		return saleRepo.findAll();
	}
	
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public List<BookSale> getAllBookSales() {
		return bookSaleRepo.findAll();
	}
	
	//Read each by ID
	public Optional<Book> getBookById(int id) {
		Optional<Book> cha = bookRepo.findById(id);
		return cha;
	}
	public Optional<User> getUserById(int id) {
		Optional<User> cha = userRepo.findById(id);
		return cha;
	}
	public Optional<Sale> getSaleById(int id) {
		Optional<Sale> cha = saleRepo.findById(id);
		return cha;
	}
	
	//Additional Read methods
		public Optional<Book> getBookByTitle(String title) {
			Optional<Book> retVal = bookRepo.findByTitle(title);
			return retVal;
		}
		public Optional<User> getByUsername(String username) {
			Optional<User> retVal = userRepo.findByUsername(username);
			return retVal;
		}
		public Optional<BookSale> getBookSaleByBookAndSale(Book book, Sale sale) {
			Optional<BookSale> retVal = bookSaleRepo.findByBookAndSale(book, sale);
			return retVal;
		}
		public List<Book> getBookByAuthor(String auth) {
			List<Book> retVal = bookRepo.findByAuthor(auth);
			return retVal;
		}
		public List<Sale> getSaleByUser(User user) {
			List<Sale> retVal = saleRepo.findSaleByUser(user);
			return retVal;
		}
	
	//End of Read methods
	//Update Methods
	public Book updateBookById(Book book) {
		//save() -> 
		if(bookRepo.existsByTitle(book.getTitle())) {
			Book update = bookRepo.save(book);
			return update;
		}
		return null;
	}
	public Sale updateSaleById(Sale sale) {
		if(saleRepo.existsById(sale.getSaleId())) {
			Sale update = saleRepo.save(sale);
			return update;
		}
		return null;
	}
	public User updateUserById(User user) {
		if(userRepo.existsById(user.getId())) {
			User update = userRepo.save(user);
			return update;
		}
		return null;
	}
	public BookSale updateBookSaleById(BookSale bs) {
		if(bookSaleRepo.existsById(bs.getId())) {
			BookSale update = bookSaleRepo.save(bs);
			return update;
		}
		return null;
	}
	
	
	
	//Delete Methods
	public boolean deleteBookById(int id) {

		if(bookRepo.existsById(id)) {
			bookRepo.deleteById(id);
			return true;
		}
		return false;
	}
	public boolean deleteSaleById(int id) {
		if(saleRepo.existsById(id)) {
			saleRepo.deleteById(id);
			return true;
		}
		return false;
	}
	public boolean deleteUserById(int id) {

		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
	public boolean deleteBookSaleById(int id) {
		if(bookSaleRepo.existsById(id)) {
			bookSaleRepo.deleteById(id);
			return true;
		}
		return false;
	}
	public boolean deleteBookByUsername(String un) {
		if (bookRepo.existsByTitle(un)) {
			bookRepo.deleteBookByTitle(un);
			return true;
		}
		return false;
	}
	
	//End CRUD --------
	
	
	//Book Methods

	public List<Book> getByAuthor(String author) {
		List<Book> retVal = bookRepo.findByAuthor(author);
		return retVal;
	}
	public List<Book> getBySizeGreaterThan(int size) {
		List<Book> retVal = bookRepo.findBySizeGreaterThan(size);
		return retVal;
	}
	public List<Book> getBySizeLessThan(int size) {
		List<Book> retVal = bookRepo.findBySizeLessThan(size);
		return retVal;
	}
//	public List<Book> getByTitleContaining(String contain) {
//		List<Book> retVal = bookRepo.findByTitleContaining(contain);
//		return retVal;
//	}
	
	//User Methods

	public Role getUserRole(String username) {
		Optional<User> retVal = this.getByUsername(username);
		User ret = retVal.get();
		return ret.getRole();
	}
	
	//Sales methods
//	public Optional
	
	//Book Sales method. Only accessible by Admins
	public List<BookSale> findByBook(Book book) {
		List<BookSale> retVal = bookSaleRepo.findByBook(book);
		return retVal;
	}
	public List<BookSale> findBySale(Sale sale) {
		List<BookSale> retVal = bookSaleRepo.findBySale(sale);
		return retVal;
	}


}
