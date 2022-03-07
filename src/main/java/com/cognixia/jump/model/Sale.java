package com.cognixia.jump.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="sales")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer saleId;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@ManyToMany(mappedBy = "sales", cascade = { CascadeType.ALL })
	private Integer userId;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private Set<BookSale> BookSale;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Set<BookSale> getBookSale() {
		return BookSale;
	}

	public void setBookSale(Set<BookSale> bookSale) {
		BookSale = bookSale;
	}
	
	
}
