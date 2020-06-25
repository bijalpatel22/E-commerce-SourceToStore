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
import store.model.Product;

@WebServlet("/productupdate")
public class ProductUpdate extends HttpServlet{
	
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

        // Retrieve user and validate.
        String productId = req.getParameter("productid");
        if (productId == null || productId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Product Id.");
        } else {
        	try {
        		Product product = productDao.getProductFromProductId(productId);
        		if(product == null) {
        			messages.put("success", "OrderNumber does not exist.");
        		}
        		req.setAttribute("product", product);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String productId = req.getParameter("productid");
        if (productId == null || productId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid order number.");
        } else {
        	try {
        		Product product = productDao.getProductFromProductId(productId);
        		String price = req.getParameter("price");
        			if (price == null || price.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid productid.");
        	        } else {
        	        	int cost = Integer.parseInt(price);
        	        	product = productDao.updatePrice(product, cost);   
        	        	messages.put("success", "Successfully updated " + productId);
        	        }
        		req.setAttribute("product", product);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
    }
}
