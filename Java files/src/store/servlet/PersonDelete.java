package store.servlet;

import store.dal.PersonDao;
import store.model.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/persondelete")
public class PersonDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Person");        
        req.getRequestDispatcher("/PersonDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid ID");
            messages.put("disableSubmit", "true");
        } else {
        	
        	int temp = Integer.parseInt(id);
	        Person person = new Person(temp);
	        try {
	        	person = personDao.delete(person);
	        	// Update the message.
		        if (person == null) {
		            messages.put("title", "Successfully deleted " + id);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + id);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PersonDelete.jsp").forward(req, resp);
    }
}
