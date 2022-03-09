package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookSale;
import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;

@Repository
public interface BookSaleRepository extends JpaRepository<BookSale, Integer> {
	
	public List<BookSale> findByBook(Book book);
	public List<BookSale> findBySale(Sale sale);
	public Optional<BookSale> findByBookAndSale(Book book, Sale sale);
	
}