package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import dao.CreateEmployeeDao;
import dao.VerifyEmployeeEmailForRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.Status;


@WebServlet("/create/employee")
public class CreateEmployeeController extends HttpServlet { 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getRequestDispatcher("/WEB-INF/pages/html/createEmployee.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String name = String.format("%s %s", firstName, lastName);
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String contactNumber = req.getParameter("contactNumber");
        String recoveryPhrase =  req.getParameter("recoveryPhrase");
        
        
        boolean emailIsAvailable = VerifyEmployeeEmailForRegistration.execute(email);
        if(emailIsAvailable){
        	Employee employee = new Employee(name, address, email, password, contactNumber,recoveryPhrase);

            boolean employeeCreationSuccess = CreateEmployeeDao.execute(employee);

            if (employeeCreationSuccess) {
                
                req.setAttribute("employee", employee);
                req.getRequestDispatcher("/WEB-INF/pages/jsp/employeeCreationSuccessAck.jsp").forward(req, resp);
            } else {
                req.setAttribute("status", new Status("An error occurred", true));
                req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
            }
        }
        else{
            Status status = new Status("Invalid email or email is already in use!",true);
            req.setAttribute("status", status);
        	req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        }
    }
}
