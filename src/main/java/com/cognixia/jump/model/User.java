package com.cognixia.jump.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	public static enum Role {
		ROLE_USER, ROLE_ADMIN // roles need to be capital and start with ROLE_
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "userSales",
        joinColumns = {@JoinColumn(name = "user_ID")},
        inverseJoinColumns = {@JoinColumn(name = "sale  _id")}
    )
	private Integer saleID;
	
	public User() {
		this(null, "N/A", "N/A", Role.ROLE_USER, true);
	}
	
	public User(Integer id, String username, String password, Role role, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSaleID() {
		return saleID;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}
	
	
	
}
