package com.ers.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.UserDao;
import com.ers.models.User;
import com.ers.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetUserController {

	private static UserDao uDao = new UserDao();
	private static UserService uServ = new UserService();
	
	public static void getUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		int userId = Integer.parseInt(req.getParameter("userid"));
		
		User u = uServ.getUserById(userId);
		
		res.getWriter().write((new ObjectMapper().writeValueAsString(u)));
		
	}

} 
