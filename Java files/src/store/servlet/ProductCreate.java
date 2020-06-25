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

@WebServlet("/productcreate")
public class ProductCreate extends HttpServlet {

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
        //Just render the JSP.   
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String ProductId = req.getParameter("productid");
        if (ProductId == null || ProductId.trim().isEmpty()) {
            messages.put("success", "Enter all the fields.");
        } else {
        	String ProductName = req.getParameter("productname");
        	String Quantity = req.getParameter("quantity");
        	String Price = req.getParameter("price");
        	String CategoryType = req.getParameter("type");
	        try {
	        	int quant = Integer.parseInt(Quantity);
	        	int cost = Integer.parseInt(Price);
	        	Product product = new Product(ProductId,ProductName,quant,cost,CategoryType);
	            product = productDao.create(product);
	        	messages.put("success", "Successfully created " + ProductId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
    }
	
}
