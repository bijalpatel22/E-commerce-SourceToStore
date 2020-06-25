package store.servlet;


import store.dal.ItemsBroughtDao;
import store.model.ItemsBrought;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addproduct")
public class AddProduct extends HttpServlet {
	
	protected ItemsBroughtDao itemsBroughtDao;
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/AddProduct.jsp").forward(req, resp);
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
        	String OrderNumber = req.getParameter("ordernumber");
  
	        try {
	        	int temp = Integer.parseInt(OrderNumber);
	        	ItemsBrought item = new ItemsBrought(temp, ProductId);
	        	item = itemsBroughtDao.create(item);
	        	
//	        	if( value.compareTo("User") == 0) {
//	        		Users user = new Users(person.getId(), password, Users.AccountState.New, person, account);
//	        		user = userDao.create(user);
//	        	}else {
//	        		Retailers retailer = new Retailers(person.getId(), password, Retailers.AccountState.New, account.getAccountNumber());
//	        		retailer = retailerDao.create(retailer);
//	        	}
	        	
	        	messages.put("success", "Item added successfully");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddProduct.jsp").forward(req, resp);
    }
}
