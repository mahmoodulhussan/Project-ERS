package com.ers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;


import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.util.HibernateUtil;

public class ReimbursementDaoDB implements ReimbursementDao{

	@Override
	public List<Reimbursement> getAllReimbursments() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement", Reimbursement.class).list();
		return rList;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		Reimbursement r = session.get(Reimbursement.class, id);
		return r;
	}

	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(reimbursement);
		tx.commit();
	}

	@Override
	public void updateReimbursement(Reimbursement reimbursement) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.merge(reimbursement);
		tx.commit();
	}

	@Override
	public void deleteReimbursement(Reimbursement reimbursement) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(reimbursement);
		tx.commit();
	}

	@Override
	public void deleteReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		Reimbursement r = session.get(Reimbursement.class, id);
		Transaction tx = session.beginTransaction();
		session.delete(r);
		tx.commit();
	}

	@Override
	public void updateReimbursementByArgs(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employeeId, User manager) {
		Session session = HibernateUtil.getSession();
		Reimbursement r = new Reimbursement(id, type, amount, submitteddate, resolveddate, description, status, employeeId, manager);
		Transaction tx = session.beginTransaction();
		session.merge(r);
		tx.commit();
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursments() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 3", Reimbursement.class).list();
		return rList;
	}

	@Override
	public List<Reimbursement> getAllAcceptedReimbursments() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 4", Reimbursement.class).list();
		return rList;
	}
	
	@Override
	public List<Reimbursement> getAllDeniedReimbursments() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 5", Reimbursement.class).list();
		return rList;
	}
	
	@Override
	public List<Reimbursement> getAllPendingReimbursmentsForUser(User u) {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 3", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}
	
	@Override
	public List<Reimbursement> getAllAcceptedReimbursmentsForUser(User u) {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 4", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}
	
	@Override
	public List<Reimbursement> getAllDeniedReimbursmentsForUser(User u) {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> rList = session.createQuery("from Reimbursement where re_status_id = 5", Reimbursement.class).list();
		List<Reimbursement> result = new ArrayList<Reimbursement>();
		for(Reimbursement curr:rList) {
			if(curr.getEmployee()==u) {
				result.add(curr);
			}
		}
		return result;
	}
}
