import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.ers.controllers.LoginController;
import com.ers.dao.ReimbursementDao;
import com.ers.dao.ReimbursementTypeDao;
import com.ers.dao.UserDao;
import com.ers.dao.UserRoleDao;
import com.ers.models.Reimbursement;
import com.ers.models.ReimbursementStatus;
import com.ers.models.ReimbursementType;
import com.ers.models.User;
import com.ers.models.UserRole;
import com.ers.service.UserService;
import com.ers.utils.HibernateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {
	
	private static UserDao uDao = new UserDao();
	private static UserService uServ = new UserService();
	private static UserRoleDao uRoleDao = new UserRoleDao();
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM-DD-YYYY HH:mm:ss");

		
	public static void main(String[] args) {
		
		LoginController.doLogin();
		
		String jbdcUrl = "jdbc:postgresql:/-------------------Link-----------------";
		String user = "";
		String pass = "";
		
		try {
			System.out.println("Connecting to database: " +jbdcUrl);
			Connection con = DriverManager.getConnection(jbdcUrl, user, pass);
			System.out.println("Connection was successful");
		}catch(Exception e) {
			e.printStackTrace();
		}
		String user1 = "sk";
		uDao.selectByUserName(user1);
		System.out.println(uDao.selectByUserName(user1));
		
		HibernateUtil hibernate = new HibernateUtil();

	

		
		ReimbursementTypeDao reimbTypeDao = new ReimbursementTypeDao();

		
		ReimbursementDao rDao = new ReimbursementDao();
		
//		ReimbursementStatus rs1 = new ReimbursementStatus(1, "Pending");
//		
//		List<ReimbursementStatus> rList = new ArrayList<ReimbursementStatus>();
//		
//		rList.add(rs1);
//		
//		ReimbursementType rt1 = new ReimbursementType(1, "food");
//		
//		List<ReimbursementType> tList = new ArrayList<ReimbursementType>();
//		
//		tList.add(rt1);
	}
		public static void initDB() {
			List<UserRole> uRole = new ArrayList<>();
			UserRole employee = new UserRole("Employee");
			UserRole manager = new UserRole("Manager");
			uRole.add(employee);
			uRole.add(manager);
			for(UserRole role : uRole) {
				uRoleDao.insertUserRole(role);
			}
		
		List<User> uList = new ArrayList<>();
		User mahmood = new User(1, "mahmood", "password", "Mahmood", "Hussan", "m@mail.com", manager);
		}
		
//		Reimbursement r1 = new Reimbursement(100, "2021-01-01", "2021-01-01", "Ate too much, Sorry!", 1, 1, rList, rList);
		
		
//		rDao.insert(r1);
		
//		List<ReimbursementType> reimbTypeList = new ArrayList<>();
//		ReimbursementType typeLodging = new ReimbursementType("Lodging");
//		ReimbursementType typeTravel = new ReimbursementType("Travel");
//		ReimbursementType typeFood = new ReimbursementType("Food");
//		ReimbursementType typeOther = new ReimbursementType("Other");
//		reimbTypeList.add(typeLodging);
//		reimbTypeList.add(typeTravel);
//		reimbTypeList.add(typeFood);
//		reimbTypeList.add(typeOther);
//		for(ReimbursementType reimbType : reimbTypeList) {
//			reimbTypeDao.insertReimbursementType(reimbType);
//		}
		
	
	
}	
