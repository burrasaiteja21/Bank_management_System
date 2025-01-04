package controller;

import java.io.IOException;

import dao.CreateCustomerDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Status;

@WebServlet("/employee/create-customer")
public class CreateCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/html/createCustomer.html").forward(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String contactNumber = req.getParameter("contactNumber");
        String aadhar = req.getParameter("aadhar");
        String pan = req.getParameter("pan");
        String ssn = req.getParameter("ssn");
        String gender = req.getParameter("gender");
        String maritalStatus = req.getParameter("maritalStatus");
        String dob = req.getParameter("dob");
        int balance = Integer.parseInt(req.getParameter("balance"));
        int overdraft = 0;
        int accountType = Integer.parseInt(req.getParameter("accountType"));
        
        boolean customerDetailsAreValid = dao.CustomerVerificationDao.execute(ssn, aadhar, pan);
        if(!customerDetailsAreValid){
        	Status status = new Status("Invalid ssn/aadhar/pan",true);
        	req.setAttribute("status", status);
        	req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        	return;
        }

        Customer customer = new Customer(name, address, email, contactNumber, aadhar, pan, ssn, balance, overdraft, accountType, gender, dob, maritalStatus);

        boolean customerCreationSuccess = CreateCustomerDao.execute(customer);

        if (customerCreationSuccess) {
           
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/WEB-INF/pages/jsp/customerCreationSuccessAck.jsp").forward(req, resp);
        } else {
            
            req.setAttribute("status", new Status("An error occurred. The person might already have a concerned (savings/current) account created.", true));
            req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
        }
    }
}
