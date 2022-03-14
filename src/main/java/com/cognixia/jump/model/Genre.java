package com.cognixia.jump.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//@Entity
public class Genre {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer genreId;
	
// 	@Column
	String name;
	
//	@ManyToMany(mappedBy = "genre")
	Set<Book> books;

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	
}
