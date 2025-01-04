package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;

@WebServlet("/admin/add-employee-email")
public class AddEmployeeEmail extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/html/addEmployeeEmail.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		boolean emailAddStatusSuccess = dao.AddEmployeeEmailDao.execute(email);
		Status status = null;
		if(emailAddStatusSuccess){
			status  = new Status("Email added successfully",false);
		}
		else{
			status  = new Status("An error occured! Email may already exist",true);
		}
		request.setAttribute("status", status);
		request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
	}

}
