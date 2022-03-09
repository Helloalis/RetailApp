package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findBySizeGreaterThan(int size);
	List<Book> findBySizeLessThan(int size);
	Optional<Book> findByTitleContaining(String contain);
	boolean existsByTitle(String title);
	void deleteBookByTitle(String un);
	
}