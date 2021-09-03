package com.ers.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.models.ReimbursementStatus;
import com.ers.util.HibernateUtil;

public class StatusDao {
	public void insert(ReimbursementStatus status) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(status);
		tx.commit();
	}
	
	public ReimbursementStatus getStatusById(int id) {
		Session session = HibernateUtil.getSession();
		ReimbursementStatus status = session.createQuery("from ReimbursementStatus where id ="+id,ReimbursementStatus.class).uniqueResult();
		return status;
	}
}
