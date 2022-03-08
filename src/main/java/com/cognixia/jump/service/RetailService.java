package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	//Crud methods
	
	
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
		Long i = (long) id;
		Optional<Book> cha = bookRepo.findById(i);
		return cha;
	}
	public Optional<User> getUserById(int id) {
		Long i = (long) id;
		Optional<User> cha = userRepo.findById(id);
		return cha;
	}
	public Optional<Sale> getSaleById(int id) {
		Long i = (long) id;
		Optional<Sale> cha = saleRepo.findById(i);
		return cha;
	}
	
	//Additional Get methods
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
	public Optional<Book> getByTitleContaining(String contain) {
		Optional<Book> retVal = bookRepo.findByTitle(contain);
		return retVal;
	}
	
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
