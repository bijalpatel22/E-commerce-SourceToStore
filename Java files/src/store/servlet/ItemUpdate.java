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

import store.dal.ItemsBroughtDao;
import store.model.ItemsBrought;

@WebServlet("/itemupdate")
public class ItemUpdate extends HttpServlet{

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

        // Retrieve user and validate.
        String ordernumber = req.getParameter("ordernumber");
        if (ordernumber == null || ordernumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Order Number.");
        } else {
        	try {
        		int orderno = Integer.parseInt(ordernumber);
        		ItemsBrought item = itemsbroughtDao.getProductFromOrderNumber(orderno);
        		if(item == null) {
        			messages.put("success", "OrderNumber does not exist.");
        		}
        		req.setAttribute("item", item);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ItemUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String ordernumber = req.getParameter("ordernumber");
        if (ordernumber == null || ordernumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid order number.");
        } else {
        	try {
        		String productid = req.getParameter("product");
        		int orderno = Integer.parseInt(ordernumber);
        		ItemsBrought item = new ItemsBrought(orderno,productid);
        			if (productid == null || productid.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid productid.");
        	        } else {
        	        	String productreplace = req.getParameter("productreplace");
        	        	item = itemsbroughtDao.updateProduct(item, productid, productreplace);   
        	        	messages.put("success", "Successfully updated " + orderno);
        	        }
        		req.setAttribute("item", item);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/ItemUpdate.jsp").forward(req, resp);
    }
}
