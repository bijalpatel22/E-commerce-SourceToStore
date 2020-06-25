package store.servlet;

import store.dal.ItemsBroughtDao;

import store.model.ItemsBrought;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/orderlist")
public class OrderList extends HttpServlet {
	
	protected ItemsBroughtDao itemsbroughtDao;
	
	@Override
	public void init() throws ServletException {
		itemsbroughtDao = ItemsBroughtDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<ItemsBrought> orders = new ArrayList<ItemsBrought>();
        
        String orderid = req.getParameter("orderid");
        if (orderid == null || orderid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid order id.");
        } else {
        	int temp = Integer.parseInt(orderid);
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		
        		orders = itemsbroughtDao.getItemsFromOrderNumber(temp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + temp);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousOrder", orderid);
        }
        req.setAttribute("items", orders);
        
        req.getRequestDispatcher("/OrderList.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<ItemsBrought> orders = new ArrayList<ItemsBrought>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String orderid = req.getParameter("orderid");
        if (orderid == null || orderid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid OrderId.");
        } else {
        	int temp = Integer.parseInt(orderid);
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	orders = itemsbroughtDao.getItemsFromOrderNumber(temp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + temp);
        }
        req.setAttribute("products", orders);
        
        req.getRequestDispatcher("/OrderList.jsp").forward(req, resp);
    }
}
