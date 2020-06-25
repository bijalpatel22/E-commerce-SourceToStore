package store.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.dal.AccountDao;
import store.model.Account;

@WebServlet("/findaccounts")
public class FindAccounts extends HttpServlet {
	
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

        Account account = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String accountNumber = req.getParameter("AccountNumber");
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int temp = Integer.parseInt(accountNumber);
        		account = accountDao.getAccountFromAccountNumber(temp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + accountNumber);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAccountNumber", accountNumber);
        }
        req.setAttribute("Account", account);
        
        req.getRequestDispatcher("/FindAccounts.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Account account = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String accountNumber = req.getParameter("AccountNumber");
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int temp = Integer.parseInt(accountNumber);
        		account = accountDao.getAccountFromAccountNumber(temp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + accountNumber);
        }
        req.setAttribute("blogUsers", account);
        
        req.getRequestDispatcher("/FindAccounts.jsp").forward(req, resp);
    }
}
