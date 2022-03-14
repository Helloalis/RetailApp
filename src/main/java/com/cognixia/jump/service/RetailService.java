package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.BookRepository;
import com.cognixia.jump.repository.SaleRepository;
import com.cognixia.jump.repository.UserRepository;

@Service
public class RetailService {
	
	@Autowired
	BookRepository bookRepo;
	

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SaleRepository saleRepo;

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
		ret.setRole(chars.getRole());
		User created = userRepo.save(ret);
		return created;
	}

	
	
	public Sale createSale(String title, String username, int quantity) {
//		Department dept = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
//        if (null == dept) {
//            dept = new Department();
//        }
//        dept.setDeptName(employee.getDepartment().getDeptName());
//        employee.setDepartment(dept);
//        return employeeRepository.save(employee);
		Book bo = bookRepo.findByTitle(title).orElse(null);
		User us = userRepo.findByUsername(username).orElse(null);
		if (us == null) {
			us = new User();
			us.setUsername(username);
		}
		if (bo == null) {
			bo = new Book();
			bo.setTitle(title);
		}
		
		Sale ret = new Sale();
		ret.setBook(bo);
		ret.setUser(us);
		ret.setQuantity(quantity);
		Sale created = saleRepo.save(ret);
		return created;
	}
	
//	public Sale createSale(Sale bs) {
////		Department dept = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
////        if (null == dept) {
////            dept = new Department();
////        }
////        dept.setDeptName(employee.getDepartment().getDeptName());
////        employee.setDepartment(dept);
////        return employeeRepository.save(employee);
//		Book bo = bookRepo.findByTitle(bs.getBookTitle()).orElse(null);
//		User us = userRepo.findByUsername(bs.getUserName()).orElse(null);
//		if (us == null) {
//			us = new User();
//			us.setUsername(bs.getUser().getUsername());
//		}
//		if (bo == null) {
//			bo = new Book();
//			bo.setTitle(bs.getBook().getTitle());
//		}
//		
//		Sale ret = new Sale();
//		ret.setBook(bo);
//		ret.setUser(us);
//		ret.setQuantity(bs.getQuantity());
//		Sale created = saleRepo.save(bs);
//		return created;
//	}
	
	//Read all
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public List<User> getAllUsers() {
		return userRepo.findAll();  
	}
	
	public List<Sale> getAllSales() {
		return saleRepo.findAll();
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
	public List<Sale> findSalesByUser(User user) {
		return saleRepo.findByUser(user);
	}
	public List<Sale> findSalesByBook(Book book) {
		return saleRepo.findByBook(book);
	}

//	public Optional<Sale> getSaleById(int id) {
//		Optional<Sale> cha = saleRepo.findById(id);
//		return cha;
   //	}
	
	//Additional Read methods
		public Optional<Book> getBookByTitle(String title) {
			Optional<Book> retVal = bookRepo.findByTitle(title);
			return retVal; 
		}
		public Optional<User> getByUsername(String username) {
			Optional<User> retVal = userRepo.findByUsername(username);
			return retVal;
		}
		public Optional<Sale> getSaleByBookAndSale(Book book, User user) {
			Optional<Sale> retVal = saleRepo.findByBookAndUser(book, user);
			return retVal;
		}
		public List<Book> getBookByAuthor(String auth) {
			List<Book> retVal = bookRepo.findByAuthor(auth);
			return retVal;
		}
//		public List<Sale> getSalesByUser(String auth) {
//			List<Sale> retVal = saleRepo.findByUser(userRepo.findByUsername(auth).get());
//			return retVal;
//		}
//		public List<Sale> getSaleByUser(User user) {
//			List<Sale> retVal = saleRepo.findSaleByUser(user);
//			return retVal;
//		}
//	
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
//	public Sale updateSaleById(Sale sale) {
//		if(saleRepo.existsById(sale.getSaleId())) {
//			Sale update = saleRepo.save(sale);
//			return update;
//		}
//		return null;
//	}
	public User updateUserById(User user) {
		if(userRepo.existsByUsername(user.getUsername())) {
			User update = userRepo.save(user);
			return update;
		}
		return null;
	}
	public Sale updateSaleById(Sale bs) {
		if(saleRepo.existsById(bs.getId())) {
			Sale update = saleRepo.save(bs);
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
//	public boolean deleteSaleById(int id) {
//		if(saleRepo.existsById(id)) {
//			saleRepo.deleteById(id);
//			return true;
//		}
//		return false;
//	}
	public boolean deleteUserById(int id) {

		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
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
	public boolean deleteBookByTitle(String un) {
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
	public List<Sale> findByBook(Book book) {
		List<Sale> retVal = saleRepo.findByBook(book);
		return retVal;
	}
	public List<Sale> findByUser(User user) {
		List<Sale> retVal = saleRepo.findByUser(user);
		return retVal;
	}

}