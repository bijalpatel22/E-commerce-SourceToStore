
package store.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.dal.DiscountsDao;
import store.model.Discounts;

@WebServlet("/finddiscount")
public class FindDiscount extends HttpServlet{
	
	protected DiscountsDao discountDao;
	
	@Override
	public void init() throws ServletException {
		discountDao = DiscountsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Discounts discounts = null;
        
        String accno = req.getParameter("accno");
        if (accno == null || accno.trim().isEmpty()) {
            messages.put("success", "Please enter a valid account number.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int accountNumber = Integer.parseInt(accno);
        		discounts = discountDao.getDiscountFromAccountNumber(accountNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + Integer.parseInt(accno));
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousCategoryType", accno);
        }
        req.setAttribute("discounts", discounts);
        
        req.getRequestDispatcher("/FindDiscount.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Discounts discounts = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String accno = req.getParameter("accno");
        if (accno == null || accno.trim().isEmpty()) {
            messages.put("success", "Please enter a valid account number.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		int accountNumber = Integer.parseInt(accno);
        		discounts = discountDao.getDiscountFromAccountNumber(accountNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + Integer.parseInt(accno));
        }
        req.setAttribute("discounts", discounts);
        
        req.getRequestDispatcher("/FindDiscount.jsp").forward(req, resp);
    }
}
