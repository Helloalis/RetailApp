package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	public List<Sale> findByBook(Book book);
	public List<Sale> findByUser(User user);
	public Optional<Sale> findByBookAndUser(Book book, User usesr);
	
}