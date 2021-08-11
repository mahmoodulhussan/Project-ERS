package com.ers.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.models.Reimbursement;
import com.ers.models.User;
import com.ers.utils.HibernateUtil;

public class UserDao {
	public UserDao() {
		
	} 
	public User selectByUserName(String username) {
		Session ses = HibernateUtil.getSession();
		Query<User> q = ses.createQuery("FROM User WHERE username = '"+username+"'", User.class);
		List<User> userList = q.list();
		if(userList.size() == 0) return null;
		return userList.get(0);
	}
	
	public User selectByUserPassword(String password) {
		Session ses = HibernateUtil.getSession();
		Query<User> q = ses.createQuery("FROM User WHERE password = '"+password+"'", User.class);
		List<User> userList = q.list();
		if(userList.size() == 0) return null;
		return userList.get(0);
	}
	
	public List<User> selectAllUsers(){
		Session ses = HibernateUtil.getSession();
		Query<User> q = ses.createQuery("FROM User", User.class);
		return q.list();
	}
	
	public void inserUser(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(user);
		tx.commit();
	}
	
	public User getAuthor(int author) {
		Session ses = HibernateUtil.getSession();
		User r = ses.createQuery("FROM Reimbursement WHERE author=" + author, User.class).uniqueResult();
		return r;
	}
	
	public void updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(u);
		tx.commit();
	}
	
	public User selectByUsernameAndPassword(String username, String password) {
		Session ses = HibernateUtil.getSession();
		Query<User> q = ses.createQuery("FROM User WHERE username = '"+username+"'"+"AND password = '"+password+"'", User.class);
		List<User> users = q.list();
		if(users.size() == 0) return null;
		return users.get(0);
	}
	
public User getUserByUserName(String username) {
		
		Session ses = HibernateUtil.getSession();
		System.out.println("In get user by username");
		User user = ses.createQuery("from User where username=:username", User.class).setString("username", username).uniqueResult();
		System.out.println(user);
		//ses.close();
		return user;
	}

	public User getUserById(int userId) {
		Session ses = HibernateUtil.getSession();
		Query<User> q = ses.createQuery("FROM User WHERE userId = '"+userId+"'", User.class);
		List<User> users = q.list();
		if(users.size() == 0) return null;
		return users.get(0);
	}
}
