package com.ers.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
@Table(name="reimb_type")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class ReimbursementType {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="typeid")
		private int typeId;
		
		@Column(name="reimb_type")
		private String reimbType;
		
		@OneToMany(mappedBy="ersType", fetch=FetchType.LAZY)
		@JsonIgnore
		private List<Reimbursement> ersList;
		
		public ReimbursementType() {
			
		}

		public ReimbursementType(int typeId, String reimbType) {
			super();
			this.typeId = typeId;
			this.reimbType = reimbType;
		}

		public ReimbursementType(String reimbType) {
			super();
			this.reimbType = reimbType;
		}

		public int getTypeId() {
			return typeId;
		}

		public void setTypeId(int typeId) {
			this.typeId = typeId;
		}

		public String getReimbType() {
			return reimbType;
		}

		public void setReimbType(String reimbType) {
			this.reimbType = reimbType;
		}

		@Override
		public String toString() {
			return "ReimbursementType [typeId=" + typeId + ", reimbType=" + reimbType +"]";
		}
}
