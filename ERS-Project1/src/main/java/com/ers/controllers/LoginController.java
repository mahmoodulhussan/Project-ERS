package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.UserDao;
import com.ers.dao.UserRoleDao;
import com.ers.models.User;
import com.ers.models.UserRole;
import com.ers.service.UserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LoginController {
	
	private static UserDao uDao = new UserDao();
	private static UserRoleDao uRoleDao = new UserRoleDao();
	private static UserService uServ = new UserService(uDao);
	
	public static void doLogin(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
	
		System.out.println("doLogin Hit!!!");
		
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	        buffer.append(System.lineSeparator());
	    }
	    String data = buffer.toString();
	    
	    System.out.println(data);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode parsedObj = mapper.readTree(data);
		
	    String username = parsedObj.get("username").asText();
	    String password = parsedObj.get("password").asText();
	    
	    System.out.println(username);
	    System.out.println(password);
	    
	    try {
			System.out.println("Inside the handler");
	    	User log = uServ.signIn(username, password);
	    	System.out.println(log);
	    	int id = log.getUserId();
	    	int role = log.getUserRole().getUserRoleId();
	    	
			req.getSession().setAttribute("userid", id);
			req.getSession().setAttribute("userRole", role);
			res.setStatus(HttpServletResponse.SC_OK);
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "POST");
			
			ObjectNode user = mapper.createObjectNode();
			
	    	user.put("userId", id);
	    	user.put("userRole", role);
	    	
			res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		}
		catch(Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.getWriter().println("Username or password incorrect");
		}
	}
	  	
}
