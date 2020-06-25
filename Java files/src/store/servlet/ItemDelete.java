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

@WebServlet("/itemdelete")
public class ItemDelete extends HttpServlet{
	
	protected ItemsBroughtDao itemsBroughtDao ;
	
	@Override
	public void init() throws ServletException {
		itemsBroughtDao = ItemsBroughtDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Item from the order");        
        req.getRequestDispatcher("/ItemDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String orderNumber = req.getParameter("ordernumber");
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            messages.put("title", "Invalid UserName");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	String productId = req.getParameter("productid");
        	int orderno = Integer.parseInt(orderNumber);
	        ItemsBrought deleteItem = new ItemsBrought(orderno,productId);
	        try {
	        	deleteItem = itemsBroughtDao.delete(deleteItem);
	        	// Update the message.
		        if (deleteItem == null) {
		            messages.put("title", "Successfully deleted " + productId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + productId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ItemDelete.jsp").forward(req, resp);
    }
}
