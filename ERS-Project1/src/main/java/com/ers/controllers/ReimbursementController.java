package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.ReimbursementDao;
import com.ers.dao.UserDao;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.service.ReimbursementService;
import com.ers.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReimbursementController {

	private static Reimbursement reimb = new Reimbursement();
	private static ReimbursementDao reimbDao = new ReimbursementDao();
	private static UserDao uDao = new UserDao();
	private static ReimbursementService rServ = new ReimbursementService(reimbDao, uDao);
	private static UserService uServ = new UserService(uDao);
	private static ReimbursementStatus rStatus = new ReimbursementStatus();
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		if(req.getMethod().equals("GET")) {
			
			List<Reimbursement> reimb = rServ.selectAllReimbursements();
			System.out.println(reimb);
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "GET");
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}		
	
	public static void getAllById(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {

		Integer id = Integer.parseInt(req.getParameter("id"));
		List<Reimbursement> rList = rServ.selectAllReimbursements();
		System.out.println(rList);
		List<Reimbursement> retList = new ArrayList<>();
		for (int i = 0; i < rList.size(); i++) {
			Reimbursement ret = rList.get(i);
			
			if (ret.getAuthor().getUserId() == id ) {
				retList.add(ret);
			}
		}
		res.getWriter().write((new ObjectMapper().writeValueAsString(retList)));
	}
		public static void addNewReimbursement(HttpServletRequest req, HttpServletResponse res)
				throws JsonProcessingException, IOException {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode parsedObj = mapper.readTree(data);
			
			int userId = Integer.parseInt(parsedObj.get("userId").asText());
			int reimbAmount = Integer.parseInt(parsedObj.get("reimbAmount").asText());
			String reimbSubmitted = parsedObj.get("reimbSubmitted").asText();
			String reimbDescription = parsedObj.get("reimbDescription").asText();
			int author = Integer.parseInt(parsedObj.get("author").asText());

			String ersType = String.valueOf(parsedObj.get("ersType").asText());
			User u = uServ.getUserById(userId);			
			User a = uServ.getAuthor(author);
		

			ReimbursementType type = rServ.getReimbursementType(ersType);
			
			rServ.addReimbursement(u, reimbAmount, reimbSubmitted, reimbDescription, a, type);
			
			ObjectNode ret = mapper.createObjectNode();
			ret.put("message", "successfully submitted a new reimbursment");
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "POST");
			res.getWriter().write(new ObjectMapper().writeValueAsString(ret));
		
	}
	
		public static void filterByStatus(HttpServletRequest req, HttpServletResponse res)
				throws JsonProcessingException, IOException {
			System.out.println("inside the status filter");
			String status = String.valueOf(req.getParameter("status"));
			List<Reimbursement> sList = rServ.selectByReimbursementStatus(status);
			System.out.println(sList);
			List<Reimbursement> retList = new ArrayList<>();
			for (int i = 0; i < sList.size(); i++) {
				Reimbursement ret = sList.get(i);
				if (ret.getErsStatus().equals(status)) {
					retList.add(ret);
				}
			}
			res.getWriter().write((new ObjectMapper().writeValueAsString(sList)));
		}
		
	public static void getAllPendingReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllPendingReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllAcceptedReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllAcceptedReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllDeniedReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllDeniedReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void getAllReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> re = rServ.getAllReimbursements();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET");
		res.getWriter().write(new ObjectMapper().writeValueAsString(re));
	}
	
	public static void acceptReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String today = parsedObj.get("date").asText();
		int rint = Integer.parseInt(parsedObj.get("reid").asText());
		Reimbursement r = rDao.getReimbursementById(rint);
		int managerId = Integer.parseInt(parsedObj.get("userid").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus rs = sDao.getStatusById(4);
				
		rServ.updateReimbursement(r.getId(), r.getType(), r.getAmount(), r.getSubmitteddate(), today, r.getDescription(), rs, r.getUserConnection(), manager);
	}
	
	public static void denyReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
		System.out.println(data);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String today = parsedObj.get("date").asText();
		int rint = Integer.parseInt(parsedObj.get("reid").asText());
		Reimbursement r = rDao.getReimbursementById(rint);
		int managerId = Integer.parseInt(parsedObj.get("userid").asText());
		User manager = uDao.getUserById(managerId);
		ReimbursementStatus status = sDao.getStatusById(5);
				
		rServ.updateReimbursement(r.getId(), r.getType(), r.getAmount(), r.getSubmitteddate(), today, r.getDescription(), status, r.getUserConnection(), manager);
	}
}
		
}
