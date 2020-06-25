package store.servlet;

import store.dal.AccountDao;
import store.model.Account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/accountupdate")
public class AccountUpdate extends HttpServlet {
	
	protected AccountDao accountDao;
	
	@Override
	public void init() throws ServletException {
		accountDao = AccountDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String accountnumber = req.getParameter("accountnumber");
      
        if (accountnumber == null || accountnumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Account Number.");
        } else {
        	try {
        		
        		int temp = Integer.parseInt(accountnumber);
        		Account account = accountDao.getAccountFromAccountNumber(temp);
        		if(account == null) {
        			messages.put("success", "Account does not exist.");
        		}
        		req.setAttribute("account", account);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccountUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String accountnumber = req.getParameter("accountnumber");
        if (accountnumber == null || accountnumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Account Number.");
        } else {
        	try {
        		int temp = Integer.parseInt(accountnumber);
        		Account account = accountDao.getAccountFromAccountNumber(temp);
        		if(account == null) {
        			messages.put("success", "Account does not exist. No update to perform.");
        		} else {
        			String carddetails = req.getParameter("carddetails");
        			if (carddetails == null || carddetails.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Card Number.");
        	        } else {
        	        	account = accountDao.updateCardDetails(account, carddetails);
        	        	messages.put("success", "Successfully updated " + temp);
        	        }
        		}
        		req.setAttribute("account", account);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccountUpdate.jsp").forward(req, resp);
    }
}
