package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;
import dao.TransferDao;

/**
 * Servlet implementation class CustomerTransferController
 * 
 * This servlet handles requests related to transferring funds between customer accounts.
 * It includes methods to handle both GET and POST requests:
 * - GET request displays the transfer form.
 * - POST request processes the transfer logic and handles errors.
 */
@WebServlet("/customer/transfer")
public class CustomerTransferController extends HttpServlet {
    
    /**
     * Handles the HTTP GET request. This method is invoked when the customer
     * navigates to the "/customer/transfer" URL. It forwards the request to
     * the transferAmount.html page to allow the user to input transfer details.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object for the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Forwarding the request to the transferAmount.html page for user input.
         * This is where the customer can input the transfer details.
         */
        request.getRequestDispatcher("/WEB-INF/pages/html/transferAmount.html").forward(request, response);
    }
    
    /**
     * Handles the HTTP POST request for processing the transfer of funds between
     * two customer accounts. This method validates that the "from" and "to" account
     * numbers are not the same, then performs the transfer if valid.
     *
     * If the account numbers are the same, a failure status is returned.
     * If the transfer is successful, the status is updated accordingly.
     * If any error occurs (e.g., database issue), an error message is returned.
     *
     * @param request  the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object for the response
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input/output error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Retrieving the account number from the request attribute.
         * The "fromAccount" is assumed to be set earlier in the request, typically
         * when the user is authenticated or authorized.
         */
        String fromAccount = (String) request.getAttribute("accountNumber");

        /**
         * Retrieving the "to" account number and the transfer amount from the request parameters.
         * These are input by the user through the form.
         */
        String toAccount = request.getParameter("toAccount");

        /**
         * Checking if the "from" and "to" account numbers are the same, which is an invalid transfer.
         * If the accounts are the same, a failure status is created, and the user is notified.
         */
        if (fromAccount.equals(toAccount)) {
            /**
             * Creating a status message indicating that the transfer failed because the accounts are the same.
             */
            Status status = new Status("From account and To account are same!, transaction failed", true);

            /**
             * Setting the failure status in the request attributes and forwarding to the status page.
             * This will display the error message to the user.
             */
            request.setAttribute("status", status);
            request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
            return;
        }

        /**
         * Parsing the transfer amount from the request parameters. The amount is assumed to be input
         * as an integer (the value entered in the form).
         */
        int amount = Integer.parseInt(request.getParameter("amount"));

        try {
            /**
             * Attempting to execute the transfer using the TransferDao class, which interacts with the
             * database to perform the transaction.
             */
            Status transferStatus = TransferDao.execute(fromAccount, toAccount, amount);

            /**
             * Setting the transfer status in the request attributes. This status will contain
             * the success or failure message of the transfer.
             */
            request.setAttribute("status", transferStatus);

            /**
             * Forwarding the status to the status.jsp page to display the result to the user.
             */
            request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            /**
             * Handling any exception that occurs during the transfer process, such as database errors.
             * The stack trace is printed for debugging purposes.
             */
            e.printStackTrace();

            /**
             * Creating a status message for the error scenario where the transfer could not be completed.
             * A general error message is shown to the user.
             */
            request.setAttribute("status", new Status("Some error occurred, please try again later", true));

            /**
             * Forwarding the error status to the status.jsp page to notify the user about the failure.
             */
            request.getRequestDispatcher("/WEB-INF/pages/jsp/status.jsp").forward(request, response);
        }
    }
}
