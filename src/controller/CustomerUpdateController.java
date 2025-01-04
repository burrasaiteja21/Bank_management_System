package controller;

import java.io.IOException;
import dao.CustomerUpdateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 * Servlet implementation class CustomerUpdateController
 * 
 * This servlet handles the updating of customer details, which is performed when
 * the employee submits the customer update form. It retrieves customer details
 * from the form, calls the CustomerUpdateDao to perform the update in the database,
 * and forwards the result to the status.jsp page.
 */
@WebServlet("/employee/customer-update")
public class CustomerUpdateController extends HttpServlet {
    
    /**
     * Handles the HTTP POST request to update customer details.
     * This method retrieves the customer data from the request, including:
     * - accountNumber
     * - name
     * - address
     * - contact number
     * - email
     * - marital status
     * 
     * The data is then passed to the CustomerUpdateDao to update the customer information
     * in the database. After processing, the status of the operation is forwarded
     * to the status.jsp page for display.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Retrieving the customer details from the request parameters.
         * These values are submitted through the customer update form.
         */
        String accountNumber = req.getParameter("accountNumber");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String contact = req.getParameter("contactNumber");
        String email = req.getParameter("email");
        String maritalStatus = req.getParameter("maritalStatus");
        
        /**
         * Calling the CustomerUpdateDao to update the customer details in the database.
         * The status returned will indicate whether the update was successful or not.
         */
        Status status = CustomerUpdateDao.execute(accountNumber, name, address, contact, email, maritalStatus);
        
        /**
         * Setting the status object in the request attributes. This status will indicate the outcome
         * of the update operation, such as success or failure, and will be displayed on the status.jsp page.
         */
        req.setAttribute("status", status);
        
        /**
         * Forwarding the request and status to the status.jsp page, which will display
         * the result of the update operation to the employee.
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
