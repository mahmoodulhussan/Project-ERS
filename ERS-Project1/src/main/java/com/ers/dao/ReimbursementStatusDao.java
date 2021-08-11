package com.ers.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.utils.HibernateUtil;

public class ReimbursementStatusDao {
	
	public ReimbursementStatusDao() {
		
	}

	public List<ReimbursementStatus> selectAllReimbursementStatus(){
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementStatus> q = ses.createQuery("FROM ReimbursementStatus", ReimbursementStatus.class);
		return q.list();
	}
	
	public ReimbursementStatus selectReimbursementStatusByName(String statusName) {
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementStatus> q = ses.createQuery("FROM ReimbursementStatus WHERE status = '"+statusName+"'", ReimbursementStatus.class);
		List<ReimbursementStatus> rStatusList = q.list();
		if(rStatusList.size() == 0) return null;
		return rStatusList.get(0);
	}
	 
	public void insertReimbursementStatus(ReimbursementStatus status) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(status);
		tx.commit();
	}
}
