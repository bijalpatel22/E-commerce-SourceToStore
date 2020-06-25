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

import store.dal.ProductDao;
import store.model.Person;
import store.model.Product;

@WebServlet("/productdelete")
public class ProductDelete extends HttpServlet {

	protected ProductDao productDao;
	
	@Override
	public void init() throws ServletException {
		productDao = ProductDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Product");        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String productId = req.getParameter("productid");
        if (productId == null || productId.trim().isEmpty()) {
            messages.put("title", "Invalid ID");
            messages.put("disableSubmit", "true");
        } else {
			try {
				Product product = productDao.getProductFromProductId(productId);
				product = productDao.delete(product);
	        	// Update the message.
		        if (product == null) {
		            messages.put("title", "Successfully deleted " + productId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + productId);
		        	messages.put("disableSubmit", "false");
		        }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
    }
}
