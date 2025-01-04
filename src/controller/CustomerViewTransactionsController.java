package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerViewTransactionsController
 * 
 * This servlet handles the process of displaying the customer's transaction enquiry page.
 * It forwards the request to the customerTransactionEnquiry.html page, where the customer
 * can view their transaction history or search for specific transactions.
 */
@WebServlet("/customer/view-transactions")
public class CustomerViewTransactionsController extends HttpServlet {
    
    /**
     * Handles the HTTP GET request. When the customer navigates to the "/customer/view-transactions"
     * URL, this method forwards the request to the customerTransactionEnquiry.html page, which
     * allows the customer to view or inquire about their transactions.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Forwarding the request to the customerTransactionEnquiry.html page. 
         * This page will provide the interface for the customer to view their transactions.
         */
        request.getRequestDispatcher("/WEB-INF/pages/html/customerTransactionEnquiry.html").forward(request, response);
    }
}
