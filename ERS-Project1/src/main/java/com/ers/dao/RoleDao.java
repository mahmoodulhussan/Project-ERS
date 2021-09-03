package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.UserRoles;
import com.ers.util.HibernateUtil;

public class RoleDao {
	public void insert(UserRoles role) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(role);
		tx.commit();
	}
	
	public UserRoles getUserRoleById(int id) {
		Session session = HibernateUtil.getSession();
		UserRoles u = session.createQuery("from UserRoles where id ="+id,UserRoles.class).uniqueResult();
		return u;
	}
}
