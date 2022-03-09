package com.cognixia.jump.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {

	public static enum Role {
		ROLE_USER, ROLE_ADMIN // roles need to be capital and start with ROLE_
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sale> bookSale;


//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "userSales",
//        joinColumns = {@JoinColumn(name = "user_ID")},
//        inverseJoinColumns = {@JoinColumn(name = "sale  _id")}
//    )
//	private Set<Sale> sales;
	
	public User() {
		this("N/A", "N/A", Role.ROLE_USER, true);
	}
	
	public User(String username, String password, Role role, boolean enabled) {
		super();
		this.userID = null;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.bookSale = new ArrayList<Sale>();
	}
	public User(String username, String password) {
		super();
		this.userID = null;
		this.username = username;
		this.password = password;
		this.role = Role.ROLE_USER;
		this.enabled = true;
		this.bookSale = new ArrayList<Sale>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + "]";
	}
	



	
	
}
