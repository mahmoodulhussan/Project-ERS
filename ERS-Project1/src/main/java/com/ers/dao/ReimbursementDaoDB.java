package com.ers.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.utils.HibernateUtil;

public class ReimbursementDao {

	public ReimbursementDao() {
		
	}
	
	public void insertReimbursement(Reimbursement reimb) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(reimb);
		tx.commit();
	}
	
	public void update(Reimbursement reimb) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(reimb);
		tx.commit();
	}
	
	public Reimbursement selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement reimb = ses.get(Reimbursement.class, id);
		return reimb;
	}
	
	public Reimbursement getBySubmit(String reimbSubmitted) {
		Session ses = HibernateUtil.getSession();
		Reimbursement reimb = ses.get(Reimbursement.class, reimbSubmitted);
		return reimb;
	}
	
	public List<Reimbursement> selectAll(){
		Session ses = HibernateUtil.getSession();
		return ses.createQuery("FROM Reimbursement", Reimbursement.class).list();
	
	}
	
	public List<Reimbursement> selectByAuthorUserName(String userName){
		Session ses = HibernateUtil.getSession();
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE author.username = '"+userName+"'", Reimbursement.class);
		return q.list();
	}
	
	public List<Reimbursement> selectByAuthorUserName(String userName, String filter){
		Session ses = HibernateUtil.getSession();
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE author.username = '"+userName+"' AND ersStatus.status = '"+filter+"'", Reimbursement.class);
		return q.list();
	}
	
	public ReimbursementType getReimbursementType(String ersType) {
		Session ses = HibernateUtil.getSession();
		ReimbursementType r = ses.createQuery("from ReimbursementType where ersType=" + ersType, ReimbursementType.class).uniqueResult();
		return r;
	}
	
	
	public List<Reimbursement> selectByReimbursementType(String type) {
		Session ses = HibernateUtil.getSession();
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE ersType.reimbType = '"+type+"'", Reimbursement.class);
		return q.list();
	}
	
	public List<Reimbursement> selectByReimbursementStatus(String status) {
		Session ses = HibernateUtil.getSession();
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE ersStatus.status = '"+status+"'", Reimbursement.class);
		return q.list();
	}
	
	public List<Reimbursement> selectReimbursementToProcess(User sesUser){
		Session ses = HibernateUtil.getSession();
		int userRoleId;
		try {
			userRoleId = sesUser.getUserRole().getUserRoleId();
		}catch(NullPointerException e) {
			userRoleId = -1;
		}
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE author.userRole.userRoleId < '"+userRoleId+"'"+"AND author.userId <> '"+sesUser.getUserId()+"'", Reimbursement.class);
		return q.list();
	}
	
	public List<Reimbursement> selectReimbursementToProcess(User sesUser, String filter){
		Session ses = HibernateUtil.getSession();
		int userRoleId;
		try {
			userRoleId = sesUser.getUserRole().getUserRoleId();
		}catch(NullPointerException e) {
			userRoleId = -1;
		}
		Query<Reimbursement> q = ses.createQuery("FROM Reimbursement WHERE author.userRole.userRoleId < '"+userRoleId+"'"+"AND author.userId <> '"+sesUser.getUserId()+"' AND ersStatus.status = '"+filter+"'", Reimbursement.class);
		return q.list();		
	}
	
	public ReimbursementType getReimbursementTypeByName(String typeName) {
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementType> q = ses.createQuery("FROM ReimbursementType WHERE reimbType = '"+typeName+"'", ReimbursementType.class);
		List<ReimbursementType> rTypeList = q.list();
		if(rTypeList.size() == 0) return null;
		return rTypeList.get(0);
	}
	
	public ReimbursementStatus getReimbursementStatusByName(String statusName) {
		Session ses = HibernateUtil.getSession();
		Query<ReimbursementStatus> q = ses.createQuery("FROM ReimbursementStatus WHERE status = '"+statusName+"'", ReimbursementStatus.class);
		List<ReimbursementStatus> rStatusList = q.list();
		if(rStatusList.size() == 0) return null;
		return rStatusList.get(0);
	}
	
	public void updateReimbursement(int rInt, User resUser, String resDate, ReimbursementStatus resStatus) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		Reimbursement updateReimb = (Reimbursement)ses.get(Reimbursement.class, rInt);
		updateReimb.setResolver(resUser);
		updateReimb.setReimbResolved(resDate);
		updateReimb.setErsStatus(resStatus);
		tx.commit();
	}
		
}
