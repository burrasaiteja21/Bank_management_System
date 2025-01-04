package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import dao.CustomerDeleteDao;

/**
 * 
 * The CustomerDeleteController handles requests for deleting a customer account.
 * It receives the account number as input, processes the deletion through the DAO layer,
 * and forwards the status of the operation to a JSP page for display.
 * 
 */
@WebServlet("/employee/customer-delete")
public class CustomerDeleteController extends HttpServlet {

    /**
     * 
     * Handles HTTP POST requests for customer deletion.
     * 
     * Steps:
     * 1. Retrieve the account number from the request parameters.
     * 2. Call the DAO layer to delete the customer associated with the account number.
     * 3. Set the deletion status as a request attribute.
     * 4. Forward the request to the status JSP for rendering.
     * 
     * @param req  HttpServletRequest object containing client request data.
     * @param resp HttpServletResponse object for sending the response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     * 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 
         * Retrieve the account number from the request parameters.
         * 
         */
        String accountNumber = req.getParameter("accountNumber");

        /**
         * 
         * Call the DAO layer to delete the customer and obtain the status.
         * 
         */
        Status status = CustomerDeleteDao.deletecustomer(accountNumber);

        /**
         * 
         * Set the deletion status as a request attribute.
         * 
         */
        req.setAttribute("status", status);

        /**
         * 
         * Forward the request to the status JSP for rendering.
         * 
         */
        req.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(req, resp);
    }
}
