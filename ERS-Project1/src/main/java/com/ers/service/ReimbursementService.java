package com.ers.service;

//import java.sql.Timestamp;
import java.util.List;

import com.ers.dao.ReimbursementDao;
import com.ers.dao.ReimbursementStatusDao;
import com.ers.dao.UserDao;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;

public class ReimbursementService {

	private ReimbursementDao rDao;
	private UserDao uDao;
	private ReimbursementStatusDao rStatusDao;
	
	public ReimbursementService() {
		
	}
	
	public ReimbursementService(ReimbursementDao rDao, UserDao uDao) {
		this.rDao = rDao;
		this.uDao = uDao;
	}
	public ReimbursementService(ReimbursementDao rDao, UserDao uDao, ReimbursementStatusDao rStatusDao) {
		this.rDao = rDao;
		this.uDao = uDao;
		this.rStatusDao = rStatusDao;
	}
	
	public ReimbursementType getReimbursementType(String ersType) {
		return rDao.getReimbursementType(ersType);
	}
	
	public List<Reimbursement> selectByReimbursementStatus(String status) {
		return rDao.selectByReimbursementStatus(status);
	}
	
	public Reimbursement addReimbursement(User u, int reimbAmount, String reimbSubmitted, String reimbDescription, User author, ReimbursementType ersType) {
		Reimbursement r = new Reimbursement(u, reimbAmount, reimbSubmitted, reimbDescription, author, ersType);
		rDao.insertReimbursement(r);
		List<Reimbursement> rList = u.getReimb();
		rList.add(r);
		uDao.updateUser(u);
		return r;
	}
	
	public List<Reimbursement> selectAllReimbursements(){
		return rDao.selectAll();
	}
	
	public List<Reimbursement> selectAllReimbursementsByUserName(String username, String filter){
		return rDao.selectByAuthorUserName(username, filter);
	}
	
	public List<Reimbursement> selectAllReimbursementsByUserName(String username){
		return rDao.selectByAuthorUserName(username);
	}
	
	public List<Reimbursement> selectReimbursementToResolveBySesUser(String username){
		User sesUser = uDao.selectByUserName(username);
		return rDao.selectReimbursementToProcess(sesUser);
	}
	
	public List<Reimbursement> selectReimbursementToResolveBySesUser(String username, String filter){
		User sesUser = uDao.selectByUserName(username);
		return rDao.selectReimbursementToProcess(sesUser, filter);
	}
	
	
	
//	public boolean initReq(String username, String reimbSubmitted, String reimbType, String reimbAmount, String reimbDesc) {
//		Integer reimbInt;
//		try {
//			reimbInt = Integer.parseInt(reimbAmount);
//		}catch (NumberFormatException e) {
//			return false;
//		}
//		ReimbursementType reqType = rDao.getReimbursementTypeByName(reimbType);
//		Reimbursement submitTime = rDao.getBySubmit(reimbSubmitted);
//		User reqUser = uDao.selectByUserName(username);
//		ReimbursementStatus reqStatus = rDao.getReimbursementStatusByName("Pending");
//		Reimbursement reimbReq = new Reimbursement(reimbInt, reimbSubmitted, null, reimbDesc, null, reqUser, null, null);
//		rDao.insertReimbursement(reimbReq);
//		return rDao != null;
//	}
	
//	public boolean updateReq(String username, String reqId, String reqStatus) {
//		Integer reqInt;
//		try {
//			reqInt = Integer.parseInt(reqId);
//		}catch(NumberFormatException e) {
//			return false;
//		}
////		String resTime = new Timestamp(System.currentTimeMillis());
//		User resUser = uDao.selectByUserName(username);
//		ReimbursementStatus resStatus = rStatusDao.selectReimbursementStatusByName(reqStatus);
////		rDao.updateReimbursement(reqInt, resUser, resTime, resStatus);
//		return true;
//	}
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

}
