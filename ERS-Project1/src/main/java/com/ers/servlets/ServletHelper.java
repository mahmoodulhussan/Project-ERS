package com.ers.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.ers.controllers.GetUserController;
import com.ers.controllers.LoginController;
import com.ers.controllers.LogoutController;
import com.ers.controllers.ReimbursementController;
import com.ers.controllers.SessionController;

public class ServletHelper {

public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
		
		case "/ERS-Project1/login":
			LoginController.doLogin(req, res);
			break;
		case "/project1/register":
			RegisterController.registerUser(req, res);
			break;
		case "/ERS-Project1/getSession":
			SessionController.getSession(req,res);
			break;
		case "/ERS-Project1/getUser":
			GetUserController.getUser(req, res);
			break;
		case "/ERS-Project1/doLogout":
			LogoutController.doLogout(req, res);
			break;
		case "/ERS-Project1/getAllReimbursements":
			ReimbursementController.getAllReimbursements(req, res);
			break;
		case "/ERS-Project1/getAllById":
			ReimbursementController.getAllById(req,res);
			break;
		case "/ERS-Project1/filterByStatus":
			ReimbursementController.filterByStatus(req, res);
			break;
		case "/ERS-Project1/addNewReimbursement":
			ReimbursementController.addNewReimbursement(req, res);
			break;
		case "/project1/approveReimbursement":
			ReimbursementController.acceptReimbursement(req, res);
			break;
		case "/project1/denyReimbursement":
			ReimbursementController.denyReimbursement(req, res);
			break;
		}
	}
}
