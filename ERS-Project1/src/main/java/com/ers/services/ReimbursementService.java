package com.ers.services;

import java.util.ArrayList;
import java.util.List;

import com.ers.dao.ReimbursementDao;
import com.ers.enums.RType;
import com.ers.enums.Status;
import com.ers.enums.UserRole;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.models.UserRoles;

public class ReimbursementService {
	
	private ReimbursementDao rDao;
	
	public ReimbursementService(ReimbursementDao r) {
		this.rDao = r;
	}
	
	public void addReimbursement(ReimbursementType type, double amount, String submitteddate, String description, ReimbursementStatus status, User employeeid) {
		Reimbursement r = new Reimbursement(type, amount, submitteddate, description, status, employeeid);
		rDao.createReimbursement(r);
	}
	
	public List<Reimbursement> getAllReimbursements(){
		return rDao.getAllReimbursments();
	}
	
	public void updateReimbursement(int id, ReimbursementType type, double amount, String submitteddate, String resolveddate, String description, ReimbursementStatus status, User employeeid, User manager) {
		rDao.updateReimbursementByArgs(id, type, amount, submitteddate, resolveddate, description, status, employeeid, manager);
	}
	
	public void deleteReimbursement(int id){
		rDao.deleteReimbursementById(id);
	}
	
	public List<Reimbursement> getAllPendingReimbursements(){
		return rDao.getAllPendingReimbursments();
	}
	
	public List<Reimbursement> getAllAcceptedReimbursements(){
		return rDao.getAllAcceptedReimbursments();
	}
	
	public List<Reimbursement> getAllDeniedReimbursements(){
		return rDao.getAllDeniedReimbursments();
	}
	
	public List<Reimbursement> getAllReimbursementsForUser(User u){
		List<Reimbursement> superList = new ArrayList<Reimbursement>();
		List<Reimbursement> a = rDao.getAllAcceptedReimbursmentsForUser(u);
		for(Reimbursement re:a) {
			superList.add(re);
		}
		List<Reimbursement> p = rDao.getAllPendingReimbursmentsForUser(u);
		for(Reimbursement r:p) {
			superList.add(r);
		}
		List<Reimbursement> d = rDao.getAllDeniedReimbursmentsForUser(u);
		for(Reimbursement rd:d) {
			superList.add(rd);
		}
		return superList;
	}
	/*
	public Reimbursement approveReimbursement(ReimbursementStatus r, UserRoles id, Reimbursement date) {

		r.setReimbursement_status(Status.APPROVED);
		id.setU_role(UserRole.MANAGER);
		date.setResolveddate(null);
		
		
		return rDao.updateReimbursement(r, id, date);
	}
	*/
	public Reimbursement getReimbursementById(int id) {
			return rDao.getReimbursementById(id);
	}

}