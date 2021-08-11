package com.ers.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.utils.HibernateUtil;

public class ReimbursementTypeDao {

	public ReimbursementTypeDao() {
		
	}
	public List<ReimbursementType> selectAllReimbTypes(){
		
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementType> q = ses.createQuery("FROM ReimbursementType", ReimbursementType.class);
		List<ReimbursementType> rTypeList = q.list();
		if(rTypeList.size() == 0) return null;
		return rTypeList;
	}
	
	public void insertReimbursementType(ReimbursementType retype) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(retype);
		tx.commit();
	}
	 
	public ReimbursementStatus selectReimbursementStatusByName(String statusName) {
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementStatus> q = ses.createQuery("FROM ReimbursementStatus WHERE status = '"+statusName+"'", ReimbursementStatus.class); 
		List<ReimbursementStatus> rStatusList = q.list();
		if(rStatusList.size() == 0) return null;
		return rStatusList.get(0);
	}
}
