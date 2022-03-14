package com.cognixia.jump.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sales")
public class Sale {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookID")
    private Book book;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "saleID")
//    private Sale sale;
    
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID")
    private User user;
    
	@Column
	String bookTitle;
	
	@Column
	String userName;
    //how many copies of a book are being bought
    @Column
    private int quantity;
	
	public Sale() {
		this(null, null, 0);
	}

	public Sale(Book boo, User use, int quantity) {
		super();
		this.book = boo;
		this.user = use;
		this.quantity = quantity;
		if(boo == null) {
			
		}
		else {
			this.bookTitle = boo.getTitle();
		}
		if(use == null) {
			
		}
		else {
			this.userName = use.getUsername();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book boo) {
		this.setBookTitle(boo.getTitle());
		this.book = boo;
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User use) {
		this.user = use;
		this.setUserName(use.getUsername());
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
    
    
}
