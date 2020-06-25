package store.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.dal.AccountDao;
import store.dal.PersonDao;
import store.dal.UsersDao;
import store.model.Account;
import store.model.Person;
import store.model.Users;

@WebServlet("/setpassworduser")
public class SetPasswordUser extends HttpServlet{

	protected AccountDao accountDao;
	protected PersonDao personDao;
	protected UsersDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/SetPasswordUser.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String Personid = req.getParameter("personid");
        if (Personid == null || Personid.trim().isEmpty()) {
            messages.put("success", "Enter all the fields.");
        } else {
        	Random rand = new Random();
        	int userid = Math.abs(rand.nextInt() + 39079);
	        try {
	        	int personid = Integer.parseInt(Personid);
	        	Person person = personDao.getPersonFromId(personid);
	        	String accountno = req.getParameter("accountno");
	        	int accno = Integer.parseInt(accountno);
	        	Account account = accountDao.getAccountFromAccountNumber(accno);
	        	String password = req.getParameter("password");
	        	Users user = new Users(userid, password, Users.AccountState.New, person , account);
	        	user = userDao.create(user);
	        	messages.put("success", "Password created Successfully.");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/SetPasswordUser.jsp").forward(req, resp);
    }
}
