package com.ers.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.UserRole;
import com.ers.utils.HibernateUtil;

public class UserRoleDao {
	
	public UserRoleDao() {
		
	}
	
	public List<UserRole> selectAllUserRoles(){
		Session ses = HibernateUtil.getSession();
		Query<UserRole> q = ses.createQuery("FROM UserRole", UserRole.class);
		return q.list();
	}
	
	public void insertUserRole(UserRole uRole) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(uRole);
		tx.commit();
	}
	
	public UserRole findUserRoleByName(String uRole) {
		Session ses = HibernateUtil.getSession();
		Query<UserRole> q = ses.createQuery("FROM UserRole WHERE userRole = '"+uRole+"'", UserRole.class);
		List<UserRole> uRoleList = q.list();
		if(uRoleList.size() == 0) return null;
		return uRoleList.get(0);
	}
	
	public String findUserRoleById(int id) {
		Session ses = HibernateUtil.getSession();
		Query<UserRole> q = ses.createQuery("FROM UserRole WHERE id = '"+id+"'", UserRole.class);
		List<UserRole> uRoleList = q.list();
		if(uRoleList.size() == 0) return "Not Authorized!!";
		return uRoleList.get(0).getUserRole();
	}
}
 
