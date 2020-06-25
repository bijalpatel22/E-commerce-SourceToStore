package store.servlet;

import store.dal.PersonDao;
import store.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/personcreate")
public class PersonCreate extends HttpServlet {
	
	protected PersonDao personDao;
	
	@Override
	public void init() throws ServletException {
		personDao = PersonDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String FirstName = req.getParameter("firstname");
        if (FirstName == null || FirstName.trim().isEmpty()) {
            messages.put("success", "Enter all the fields.");
        } else {
        	String LastName = req.getParameter("lastname");
        	String Address = req.getParameter("address");
        	String Phone = req.getParameter("phone");
        	String Email = req.getParameter("email");
	        try {
	        	
	        	Person person = new Person(FirstName, LastName, Address, Email,Phone);
	        	person = personDao.create(person);
	        	messages.put("success", "Successfully created " + FirstName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
    }
}
