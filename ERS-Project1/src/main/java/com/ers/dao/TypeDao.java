package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.util.HibernateUtil;

public class TypeDao {
	public void insert(ReimbursementType t) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
	}
	
	public ReimbursementType getTypeById(int id) {
		Session session = HibernateUtil.getSession();
		ReimbursementType tx = session.createQuery("from ReimbursementType where id ="+id,ReimbursementType.class).uniqueResult();
		return tx;
	}
}
