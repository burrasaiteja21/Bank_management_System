package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeViewTransactionsController
 * 
 * This servlet handles the request to view employee transactions. It forwards
 * the request to the appropriate HTML page that displays the transaction enquiry form.
 */
@WebServlet("/employee/view-transactions")
public class EmployeeViewTransactionsController extends HttpServlet {

    /**
     * Handles the HTTP GET request to view transactions.
     * This method forwards the request to the employeeTransactionEnquiry.html page
     * where the user can inquire about the transactions.
     *
     * @param req  the HttpServletRequest object containing the request data
     * @param resp the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /**
         * Forwarding the request to the employee transaction enquiry page (HTML).
         * The employeeTransactionEnquiry.html page will be rendered for the user.
         */
        req.getRequestDispatcher("/WEB-INF/pages/html/employeeTransactionEnquiry.html").forward(req, resp);
    }
}
