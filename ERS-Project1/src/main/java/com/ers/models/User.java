package com.ers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
@Table(name="ers_users")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="ers_username", nullable=false, unique=true)
	private String username;
	
	@Column(name="ers_password", nullable=false)
	private String password;
	
	@Column(name="user_first_name", nullable=false)
	private String firstName;
	
	@Column(name="user_last_name", nullable=false)
	private String lastName;
	
	@Column(name="user_email", nullable=false, unique=true)
	private String email;
	
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_role_fk")
    @JsonManagedReference
	public UserRole userRole;
    
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
//    @JsonBackReference
    @JsonIgnore
	private List<Reimbursement> reimb;
    
    @OneToMany(mappedBy="author", fetch=FetchType.LAZY)
    @JsonIgnore
	private List<Reimbursement> authorList = new ArrayList<>();
    @JsonIgnore
	@OneToMany(mappedBy="resolver", fetch=FetchType.LAZY)
	private List<Reimbursement> resolverList = new ArrayList<>();
	
	public User() {
		
	}

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			UserRole userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public User(int userId, String username, String password, UserRole userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String firstName, String lastName, String email, UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Reimbursement> getReimb() {
		return reimb;
	}

	public void setReimb(List<Reimbursement> reimb) {
		this.reimb = reimb;
	}

	public List<Reimbursement> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Reimbursement> authorList) {
		this.authorList = authorList;
	}

	public List<Reimbursement> getResolverList() {
		return resolverList;
	}

	public void setResolverList(List<Reimbursement> resolverList) {
		this.resolverList = resolverList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}

}
