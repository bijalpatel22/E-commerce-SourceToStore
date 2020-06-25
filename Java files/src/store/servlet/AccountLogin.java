package store.servlet;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import store.dal.UsersDao;
import store.model.Account;
import store.model.Users;

@WebServlet("/accountlogin")
public class AccountLogin extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;
	    
	    protected UsersDao userdao;
	    
	    @Override
		public void init() throws ServletException {
			userdao = UsersDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);       
	        req.getRequestDispatcher("/AccountLogin.jsp").forward(req, resp);
		}
	 
//	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	            throws ServletException, IOException {
//	    	Map<String, String> messages = new HashMap<String, String>();
//	        request.setAttribute("messages", messages);
//	        
//	        String accountnumber = request.getParameter("accountnumber");
//	        int temp = Integer.parseInt(accountnumber);
//	        String password = request.getParameter("password");
//	         
//	        try {
//	            Users user = userdao.checkLogin(temp, password);
//	            if (user != null) {
//	                HttpSession session = request.getSession();
//	                session.setAttribute("user", user);
//	                request.getRequestDispatcher("/FindProducts.jsp").forward(request, response);
//	            } if(user == null) {
//	            	messages.put("success", "Please enter a valid Account Number/Password.");
//	            	System.out.println("Hello");
//	            }
//	             
//	        } catch (SQLException | ClassNotFoundException ex) {
//	            throw new ServletException(ex);
//	        }
//	    }
		
		@Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve user and validate.
	        String accountnumber = req.getParameter("accountnumber");
	        if (accountnumber == null || accountnumber.trim().isEmpty()) {
	            messages.put("success", "Please enter a valid Account Number.");
	        } else {
	        	try {
	        		int temp = Integer.parseInt(accountnumber);
	        		String password = req.getParameter("password");
	        		Users user;
					try {
						user = userdao.checkLogin(temp, password);
						if(user == null) {
		        			messages.put("success", "Account does not exist. No update to perform.");
		        			System.out.println("Hello");
		        		} else {
		        			req.getRequestDispatcher("indexmid.html").forward(req, resp);
		        			return;
		        		}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
//	        		req.setAttribute("account", account);
	        	} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/AccountLogin.jsp").forward(req, resp);
	    }
}

