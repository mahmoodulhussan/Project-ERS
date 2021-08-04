package com.ers.models;

import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
@Table(name="user_role")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class UserRole {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "roleid")
		private int userRoleId;
		
		@Column(name="user_role", unique = true)
		@JsonIgnore
		public String userRole;
		
		@OneToMany(mappedBy="userRole", fetch=FetchType.LAZY)
//		@JsonBackReference
		@JsonIgnore
		private List<User> uRoleList = new ArrayList<>();
		
		public UserRole() {
			
		}

		public UserRole(int userRoleId, String userRole) {
			super();
			this.userRoleId = userRoleId;
			this.userRole = userRole;
		}

		public UserRole(String userRole) {
			super();
			this.userRole = userRole;
		}

		public int getUserRoleId() {
			return userRoleId;
		}

		public void setUserRoleId(int userRoleId) {
			this.userRoleId = userRoleId;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}

		public List<User> getuRoleList() {
			return uRoleList;
		}

		public void setuRoleList(List<User> uRoleList) {
			this.uRoleList = uRoleList;
		}

		@Override
		public String toString() {
			return "UserRole [userRoleId=" + userRoleId + ", userRole=" + userRole +"]";
		}
}
