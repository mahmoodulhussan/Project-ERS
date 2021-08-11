package com.ers.models;


import java.sql.Timestamp;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Table(name="ers_reimb")

public class Reimbursement {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimbId;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties("ers_reimb")
	private User user;
	
	@Column(name="reimb_amount", nullable=false)
	private int reimbAmount;
	
	@Column(name="reimb_submit")
	private String reimbSubmitted;
	
	@Column(name="reimb_resolved")
	private String reimbResolved;
	
	@Column(name="reimb_description",  nullable=false)
	private String reimbDescription;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_auth_fk")
	private User author;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_reslvr_fk")
	private User resolver;
	
    
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="reimb_status_fk")
	private ReimbursementStatus ersStatus;
   
    
    
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="reimb_type_fk")
	private ReimbursementType ersType; 
		
	public Reimbursement() {

	}


	public Reimbursement(User user, int reimbAmount, String reimbSubmitted, String reimbDescription, User author,
			ReimbursementType ersType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.author = author;
		this.ersType = ersType;
	}


	public Reimbursement(User user, int reimbAmount, String reimbSubmitted, String reimbDescription, User author) {
		super();
		this.user = user;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.author = author;
	}


	public Reimbursement(int reimbAmount, String reimbSubmitted, String reimbDescription, User author, User resolver) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.author = author;
		this.resolver = resolver;
	}


	public Reimbursement(int reimbAmount, String reimbSubmitted, String reimbDescription, User author, User resolver,
			ReimbursementStatus ersStatus, ReimbursementType ersType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbDescription = reimbDescription;
		this.author = author;
		this.resolver = resolver;
		this.ersStatus = ersStatus;
		this.ersType = ersType;
	}

	public Reimbursement(int reimbAmount, String reimbSubmitted, String reimbResolved, String reimbDescription,
			User author, User resolver, ReimbursementStatus ersStatus, ReimbursementType ersType) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.author = author;
		this.resolver = resolver;
		this.ersStatus = ersStatus;
		this.ersType = ersType;
	}


	public Reimbursement(int reimbId, int reimbAmount, String reimbSubmitted, String reimbResolved,
			String reimbDescription, User author, User resolver, ReimbursementStatus ersStatus,
			ReimbursementType ersType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.author = author;
		this.resolver = resolver;
		this.ersStatus = ersStatus;
		this.ersType = ersType;
	}


	public Reimbursement(int reimbId, int reimbAmount, String reimbSubmitted, String reimbResolved,
			String reimbDescription) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
	}


	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(String reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public String getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(String reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public User getResolver() {
		return resolver;
	}


	public void setResolver(User resolver) {
		this.resolver = resolver;
	}


	public ReimbursementStatus getErsStatus() {
		return ersStatus;
	}


	public void setErsStatus(ReimbursementStatus ersStatus) {
		this.ersStatus = ersStatus;
	}


	public ReimbursementType getErsType() {
		return ersType;
	}


	public void setErsType(ReimbursementType ersType) {
		this.ersType = ersType;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", user=" + user + ", reimbAmount=" + reimbAmount
				+ ", reimbSubmitted=" + reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription="
				+ reimbDescription + ", author=" + author + ", resolver=" + resolver + ", ersStatus=" + ersStatus
				+ ", ersType=" + ersType + "]";
	}
}
