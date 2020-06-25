package store.servlet;

import store.dal.AccountDao;
import store.dal.PersonDao;
import store.dal.RetailersDao;
import store.dal.UsersDao;
import store.model.Account;
import store.model.Person;
import store.model.Retailers;
import store.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/accountcreate")
public class AccountCreate extends HttpServlet {
	
	protected AccountDao accountDao;
	protected PersonDao personDao;
	protected UsersDao userDao;
	protected RetailersDao retailerDao;
	
	@Override
	public void init() throws ServletException {
		accountDao = AccountDao.getInstance();
		personDao = PersonDao.getInstance();
		userDao = UsersDao.getInstance();
		retailerDao = RetailersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AccountCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String FirstName = req.getParameter("firstname");
        if (FirstName == null || FirstName.trim().isEmpty()) {
            messages.put("success", "Enter all the fields.");
        } else {
        	String LastName = req.getParameter("lastname");
        	String Address = req.getParameter("address");
        	String Phone = req.getParameter("phone");
        	String Email = req.getParameter("email");
        	String CardDetails = req.getParameter("carddetails");
        	Random rand = new Random();
        	int accno = Math.abs(rand.nextInt() + 101020121);
        	long millis = System.currentTimeMillis();  
        	java.sql.Date date=new java.sql.Date(millis);
	        try {
	        	Person person = new Person(FirstName, LastName, Address, Email,Phone);
	        	Account account = new Account(accno, Address, CardDetails, date);
	        	person = personDao.create(person);
	        	account = accountDao.create(account);
//	        	if( value.compareTo("User") == 0) {
//	        		Users user = new Users(person.getId(), password, Users.AccountState.New, person, account);
//	        		user = userDao.create(user);
//	        	}else {
//	        		Retailers retailer = new Retailers(person.getId(), password, Retailers.AccountState.New, account.getAccountNumber());
//	        		retailer = retailerDao.create(retailer);
//	        	}
	        	
	        	messages.put("success", "Account created Successfully. Your account number is "+ account.getAccountNumber());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccountCreate.jsp").forward(req, resp);
    }
}
