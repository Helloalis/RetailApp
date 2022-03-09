//package com.cognixia.jump.model;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Past;
//
//
//@Entity
//@Table(name="sales")
//public class Sale {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer saleId;
//	
//	//total price of each sales. Should be bookSale.price * bookSale.quantity for each bookSale object, then sum all the bookSale attacked to this sale
//	@Column(nullable = false)
//	@Min(value = 0)	
//	private double totalPrice;
//	
////	//This just need to be a simple many to many between user and sales
////	@ManyToMany(mappedBy = "sales", cascade = { CascadeType.ALL })
////	private Set<User> users;
//	
////	@ManyToOne(fetch = FetchType.LAZY, optional = false)
////    @JoinColumn(name = "userID")
////	private User user;
//	
////	@Past 
////	@Temporal(TemporalType.DATE)
////	private Date date;
//	
//	//I needed a many to many relationship with bookSales, but the join table need a quantity column, or else it would only keep track of what books were being sold, but not how many were being sold. Someone buys 23 copies of moby dick for English class, and the system won't track that. So instead, I set up the many to many tables manually, with two one to many relationships
//	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
//	private List<BookSale> bookSale;
//	
//	public Sale() {
//		this.bookSale = new ArrayList<BookSale>();
//	}
//	
//	public Sale(User use) {
//		super();
//		this.saleId = null;
//		this.totalPrice = 0;
//		this.user = use;
//		this.bookSale = new ArrayList<BookSale>();
//		
//	}
//
//	public Integer getSaleId() {
//		return saleId;
//	}
//
//	public void setSaleId(Integer saleId) {
//		this.saleId = saleId;
//	}
//
//	public double getTotalPrice() {
//		return totalPrice;
//	}
//
//	public void setTotalPrice() {
//		double retVal = 0;
//		for(BookSale i : bookSale) {
//			retVal += i.getBook().getPrice() * i.getQuantity();
//		}
//		this.totalPrice = retVal;
//	}
//
//	public List<BookSale> getBookSale() {
//		return bookSale;
//	}
//
//	public void setBookSale(List<BookSale> bookSale) {
//		this.bookSale = bookSale;
//	}
//	
//	public void addBookSale(BookSale bs) {
//		bookSale.add(bs);
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User users) {
//		this.user = users;
//	}
//	
//	
//	
//}
