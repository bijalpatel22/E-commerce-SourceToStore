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


@WebServlet("/accountdelete")
public class AccountDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Account");        
        req.getRequestDispatcher("/AccountDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String accountnumber = req.getParameter("accountnumber");
        if (accountnumber == null || accountnumber.trim().isEmpty()) {
            messages.put("title", "Invalid AccountNumber");
            messages.put("disableSubmit", "true");
        } else {
        	
        	int temp = Integer.parseInt(accountnumber);
	        Account account = new Account(temp);
	        System.out.println("number :"+temp);
	        try {
	        	account = accountDao.delete(account);
	        	// Update the message.
		        if (account == null) {
		            messages.put("title", "Successfully deleted " + temp);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + temp);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
	        	System.out.println("catch");
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AccountDelete.jsp").forward(req, resp);
    }
}
