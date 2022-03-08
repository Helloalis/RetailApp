package com.cognixia.jump.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	//Books can have the same title, since book names can't be trademarked unless they're a brand, as far as I know. You can definitely have books or series with similar titles, e.g The Inheritance Trilogy(my hold for this came in 2 days ago, I set it last month, can't wait to start this weekend) vs The Inheritance Cycle. Howeer, naming a book the same as another book doesn't happen very often, since it confuses everyone and screws up marketing
	@Column(nullable = false, unique = true)
	private String title;
	
	//The author of a book.
	@Column(nullable = false)
	private String author;
	
//	//Who published the book. Not currently active field
//	@Column
//	private String publisher;
	
	@Column(nullable = false)
	@Min(value = 0)	
	private double price;
	
	//how many copies of each book are in storage
	//Shouldn't be zero, but can be if user attempts to purchase more than exists in storage. Means the company promises to acquire more copies and will send as soon as acquired. 
	@Column(nullable = false)
	private int qty;
	
	//Stores page count. So much easier than for progress tracker, (incoming tangent) tracking progress for how far in a book a reader is, ... is complicated. The obvious one is page count, but page counts vary by edition. With an ebook, you can change how many pages there are by adjusting the font size. Can't rely on chapters for a percentage progress, because there's no consistent chapter size. In one of my books, I can find a 50 page chapter, and a 2 paragraph chapter. Publishes, as far as I know, judge length by word count, but readers won't know what word they're on. Therefore, tracking book progress in any mathematical reliable manner requires keeping track of every edition, and how many page count each one has. And for ebooks, its impossible unless the ereader is built into the progress tracker, since the app won't know what font size is being used, and so how many pages there are
	@Column
	@Min(value = 0)	
	private int size;
	
	//I needed a many to many relationship with bookSales, but the join table need a quantity column, or else it would only keep track of what books were being sold, but not how many were being sold. Someone buys 23 copies of moby dick for English class, and the system won't track that. So instead, I set up the many to many tables manually, with two one to many relationships
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<BookSale> BookSale;

	//keeps track of what genre books fall into. Would enable users to find all books that fit in one genre, or to see books that match multiple genres. Extension, unimplemented
//  @ManyToMany
//  @JoinTable(name = "bookGenre", 
//  	joinColumns = @JoinColumn(name = "bookID", referencedColumnName = "bookID"), 
//  	inverseJoinColumns = @JoinColumn(name = "genreID", referencedColumnName = "genreID"))
//	private Set<Genre> genre;
	
	public Book() {
		this(null, "","", -1, -1);
	}

	public Book(Integer id, String title, String author, double price, int qty) {
		super();
		this.bookId = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.qty = qty;
	}

	public Integer getId() {
		return bookId;
	}

	public void setId(Integer id) {
		this.bookId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Books [id=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price + ", qty=" + qty
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + qty;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (bookId == null) {
			if (other.bookId != null) {
				return false;
			}
		} else if (!bookId.equals(other.bookId)) {
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
			return false;
		}
		if (qty != other.qty) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
	
	
	
}
