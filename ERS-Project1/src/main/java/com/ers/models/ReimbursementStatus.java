package com.ers.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
@Table(name="ers_status")
//@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class ReimbursementStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="statusid")
	private int statusId;
	
	
	@Column(name="reimb_status")
	private String status;
	
	@OneToMany(mappedBy="ersStatus", fetch=FetchType.LAZY)
	private List<Reimbursement> ersList;
	
	public ReimbursementStatus() {
		
	}
	
	public ReimbursementStatus(String status) {
		super();
		this.status = status;
	}



	public ReimbursementStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}



	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "ReimbursementStatus [statusId=" + statusId + ", status=" + status + ", ersList=" + ersList + "]";
	}

	
}
