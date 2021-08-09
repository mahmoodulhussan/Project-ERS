package com.ers.service;

import java.util.List;

import com.ers.dao.UserDao;
import com.ers.dao.UserRoleDao;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.logging.Logging;
import com.ers.models.Reimbursement;
import com.ers.models.User;

public class UserService {

	private UserDao uDao;
	private UserRoleDao uRoleDao;
	
	public UserService() {
		
	}
	
	public UserService(UserDao uDao) {
		this.uDao = uDao;
	}
	
	public UserService(UserDao uDao, UserRoleDao uRoleDao) {
		this.uDao = uDao;
		this.uRoleDao = uRoleDao;
	}
	
	public List<User> getAllUsers(){
		return uDao.selectAllUsers();
	}
	
	public User getAuthor(int author) {
		return uDao.getAuthor(author);
	}
	
	
	public User getUserById(int userId) {
		return uDao.getUserById(userId);
	}
	
	public User selectUserByUserName(String username) {
		return uDao.selectByUserName(username);
	}
	
	public boolean verifyUserName(String username) {
		if(uDao.selectByUserName(username) == null) return false;
		return true;
	}
	
	public boolean verifySignIn(String username, String password) {
		if(uDao.selectByUsernameAndPassword(username, password) == null) return false;
		return true;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		System.out.println("In uServ");
		System.out.println(username);
		System.out.println(password);
		User u = uDao.getUserByUserName(username);
		System.out.println(u);
		if(u.getUserId() == 0) {
			Logging.logger.warn("User tried logging in that does not exist");
			throw new UserDoesNotExistException();
		}
		if(!u.getPassword().equals(password)) {
			Logging.logger.warn("User tried to login with invalid credentials");
			throw new InvalidCredentialsException();
		}
		else {
			Logging.logger.info("User was logged in");
			return u;
		}
	}
	
//	public String getUserRoleByUserName(String username) {
//		User getUser = selectUserByUserName(username);
//		try {
//			return uRoleDao.findUserRoleById(getUser.getUserRole().getUserRoleId());
//		}catch(NullPointerException e) {
//			return "User Is Not Authorized";
//		}
//	}



	
}
